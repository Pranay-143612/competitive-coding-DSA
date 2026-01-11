import java.io.*;
import java.util.*;

public class BoxSim {

    private static char[][] gravity(char[][] b) {
        int rows = b.length, cols = b[0].length;
        for (int col = 0; col < cols; col++) {
            int fill = rows - 1;
            for (int r = rows - 1; r >= 0; r--) {
                if (b[r][col] == '*') {
                    b[fill][col] = '*';
                    if (fill != r) b[r][col] = '.';
                    fill--;
                }
            }
            for (int r = fill; r >= 0; r--) b[r][col] = '.';
        }
        return b;
    }

    private static char[][] rotR(char[][] b) {
        int r = b.length, c = b[0].length;
        char[][] out = new char[c][r];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                out[j][r - 1 - i] = b[i][j];
        return out;
    }

    private static char[][] rotL(char[][] b) {
        int r = b.length, c = b[0].length;
        char[][] out = new char[c][r];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                out[c - 1 - j][i] = b[i][j];
        return out;
    }

    private static String readLine(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null) {
            if (!s.trim().isEmpty()) return s;
        }
        return null;
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String first = readLine(br);
            if (first == null) return;
            StringTokenizer st = new StringTokenizer(first);
            int rows = Integer.parseInt(st.nextToken());
            int cols = Integer.parseInt(st.nextToken());

            char[][] b = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                String row = readLine(br);
                if (row == null) row = rep('.', cols);
                row = row.replaceAll("\\s+", "");
                if (row.length() < cols) row = (row + rep('.', cols)).substring(0, cols);
                b[i] = row.toCharArray();
            }

            String kln = readLine(br);
            int k = 0;
            if (kln != null) k = Integer.parseInt(kln.trim());

            String[] ops = new String[k];
            for (int i = 0; i < k; i++) {
                String op = readLine(br);
                if (op == null) op = "";
                ops[i] = op.trim().toLowerCase();
            }

            b = gravity(b);
            for (String op : ops) {
                if ("right".equals(op)) b = rotR(b);
                else if ("left".equals(op)) b = rotL(b);
                b = gravity(b);
            }

            StringBuilder out = new StringBuilder();
            for (int i = 0; i < b.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    out.append(b[i][j]);
                    if (j < b[0].length - 1) out.append(' ');
                }
                if (i < b.length - 1) out.append('\n');
            }
            System.out.print(out);
        } catch (Exception e) {
            System.out.println("Runtime Error: " + e.getMessage());
        }
    }

    private static String rep(char ch, int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) sb.append(ch);
        return sb.toString();
    }

    private static String rep(String s, int count) {
        StringBuilder sb = new StringBuilder(s.length() * count);
        for (int i = 0; i < count; i++) sb.append(s);
        return sb.toString();
    }
}
