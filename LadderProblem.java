import java.util.*;

public class LadderProblem {
    static int m, n;
    static char[][] grid;
    static boolean[][][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static class State {
        int r, c, dir, dist;
        State(int r, int c, int dir, int dist) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            String row = sc.next();
            for (int j = 0; j < n; j++) {
                grid[i][j] = row.charAt(j);
            }
        }

        List<int[]> src = new ArrayList<>();
        List<int[]> dest = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'l') src.add(new int[]{i, j});
                if (grid[i][j] == 'L') dest.add(new int[]{i, j});
            }
        }

        int len = src.size();
        int sdir = (src.get(0)[0] == src.get(1)[0]) ? 0 : 1;
        int edir = (dest.get(0)[0] == dest.get(1)[0]) ? 0 : 1;
        int[] scen = findCenter(src);
        int[] ecen = findCenter(dest);

        visited = new boolean[m][n][2];
        int res = bfs(scen, ecen, len, sdir, edir);
        System.out.println(res == -1 ? "Impossible" : res);
    }

    static int[] findCenter(List<int[]> cells) {
        cells.sort((a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        return cells.get(cells.size() / 2);
    }

    static int bfs(int[] start, int[] end, int len, int sdir, int edir) {
        Queue<State> q = new LinkedList<>();
        q.add(new State(start[0], start[1], sdir, 0));
        visited[start[0]][start[1]][sdir] = true;

        while (!q.isEmpty()) {
            State s = q.poll();
            if (s.r == end[0] && s.c == end[1] && s.dir == edir)
                return s.dist;

            // Move
            for (int i = 0; i < 4; i++) {
                int nr = s.r + dr[i];
                int nc = s.c + dc[i];
                if (canMove(nr, nc, s.dir, len) && !visited[nr][nc][s.dir]) {
                    visited[nr][nc][s.dir] = true;
                    q.add(new State(nr, nc, s.dir, s.dist + 1));
                }
            }

            // Rotate
            int ndir = 1 - s.dir;
            if (canRotate(s.r, s.c, len) && !visited[s.r][s.c][ndir]) {
                visited[s.r][s.c][ndir] = true;
                q.add(new State(s.r, s.c, ndir, s.dist + 1));
            }
        }
        return -1;
    }

    static boolean canMove(int r, int c, int dir, int len) {
        int half = len / 2;
        if (dir == 0) {
            int left = c - half;
            int right = c + (len % 2 == 0 ? half - 1 : half);
            if (r < 0 || r >= m || left < 0 || right >= n) return false;
            for (int j = left; j <= right; j++)
                if (grid[r][j] == 'B') return false;
        } else {
            int top = r - half;
            int bottom = r + (len % 2 == 0 ? half - 1 : half);
            if (c < 0 || c >= n || top < 0 || bottom >= m) return false;
            for (int i = top; i <= bottom; i++)
                if (grid[i][c] == 'B') return false;
        }
        return true;
    }

    static boolean canRotate(int r, int c, int len) {
        int half = len / 2;
        int top = r - half;
        int bottom = r + (len % 2 == 0 ? half - 1 : half);
        int left = c - half;
        int right = c + (len % 2 == 0 ? half - 1 : half);
        for (int i = top; i <= bottom; i++) {
            for (int j = left; j <= right; j++) {
                if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 'B')
                    return false;
            }
        }
        return true;
    }
}
