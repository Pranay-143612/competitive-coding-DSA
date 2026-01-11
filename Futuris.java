import java.util.*;
import java.io.*;

public class Futuris {

    private static boolean checkCond(String c, Map<String, Integer> v) {
        String[] ops = { "==", "!=", "<", ">" };
        for (String op : ops) {
            if (c.contains(op)) {
                String[] p = c.split(op);
                String l = p[0].trim();
                String r = p[1].trim();
                int lv = v.containsKey(l) ? v.get(l) : Integer.parseInt(l);
                int rv = v.containsKey(r) ? v.get(r) : Integer.parseInt(r);
                switch (op) {
                    case "==":
                        return lv == rv;
                    case "!=":
                        return lv != rv;
                    case "<":
                        return lv < rv;
                    case ">":
                        return lv > rv;
                }
            }
        }
        return false;
    }

    private static class Block {
        List<String> lines;
        int idx;

        Block(List<String> l, int i) {
            this.lines = l;
            this.idx = i;
        }
    }

    private static Block getBlock(List<String> code, int start, Set<String> stop) {
        List<String> blk = new ArrayList<>();
        int d = 0;
        int i = start;
        while (i < code.size()) {
            String ln = code.get(i).trim();
            if (ln.startsWith("for") || ln.startsWith("if"))
                d++;
            else if (ln.equals("end")) {
                if (d == 0 && stop.contains("end"))
                    break;
                d--;
            } else if (stop.contains(ln) && d == 0)
                break;
            blk.add(code.get(i));
            i++;
        }
        return new Block(blk, i);
    }

    private static List<String> run(List<String> code, Map<String, Integer> vars) {
        List<String> out = new ArrayList<>();
        int i = 0;
        while (i < code.size()) {
            String ln = code.get(i).trim();
            if (ln.isEmpty()) {
                i++;
                continue;
            }
            String[] p = ln.split("\\s+");
            String cmd = p[0];
            if (cmd.equals("print")) {
                String val = p[1];
                out.add(vars.containsKey(val) ? String.valueOf(vars.get(val)) : val);
                i++;
            } else if (cmd.equals("if")) {
                String cond = ln.substring(3).trim();
                boolean ok = checkCond(cond, vars);
                i++;
                Block yb = getBlock(code, i, new HashSet<>(Arrays.asList("No", "end")));
                List<String> yes = yb.lines;
                i = yb.idx;
                List<String> no = new ArrayList<>();
                if (i < code.size() && code.get(i).trim().equals("No")) {
                    Block nb = getBlock(code, i + 1, new HashSet<>(Collections.singletonList("end")));
                    no = nb.lines;
                    i = nb.idx;
                }
                if (i < code.size() && code.get(i).trim().equals("end"))
                    i++;
                if (ok)
                    out.addAll(run(yes, new HashMap<>(vars)));
                else
                    out.addAll(run(no, new HashMap<>(vars)));
            } else if (cmd.equals("for")) {
                String var = p[1];
                String s = p[2];
                String e = p[3];
                int sv = vars.containsKey(s) ? vars.get(s) : Integer.parseInt(s);
                int ev = vars.containsKey(e) ? vars.get(e) : Integer.parseInt(e);
                Block b = getBlock(code, i + 1, new HashSet<>(Collections.singletonList("end")));
                List<String> blk = b.lines;
                i = b.idx;
                if (i < code.size() && code.get(i).trim().equals("end"))
                    i++;
                for (int val = sv; val <= ev; val++) {
                    Map<String, Integer> tmp = new HashMap<>(vars);
                    tmp.put(var, val);
                    out.addAll(run(blk, tmp));
                }
            } else
                i++;
        }
        return out;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> code = new ArrayList<>();
        String l;
        while ((l = br.readLine()) != null && !l.trim().isEmpty())
            code.add(l.trim());
        String[] names = code.get(code.size() - 2).split("\\s+");
        String[] vals = code.get(code.size() - 1).split("\\s+");
        Map<String, Integer> vars = new HashMap<>();
        for (int j = 0; j < names.length; j++)
            vars.put(names[j], Integer.parseInt(vals[j]));
        List<String> futCode = code.subList(0, code.size() - 2);
        List<String> res = run(futCode, vars);
        for (int j = 0; j < res.size(); j++) {
            System.out.print(res.get(j));
            if (j != res.size() - 1)
                System.out.println();
        }

    }
}
