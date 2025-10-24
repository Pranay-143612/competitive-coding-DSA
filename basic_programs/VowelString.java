package basic_programs;

import java.util.HashSet;
import java.util.Set;

public class VowelString {
    public static void main(String[] args) {
        String str = "i saw a hippopotamus near the marriage hall";
        String[] inp = str.split(" ");
        Set<Character> hs = new HashSet<>();
        hs.add('a');
        hs.add('e');
        hs.add('i');
        hs.add('o');
        hs.add('u');
        String ret = "";
        int max = Integer.MIN_VALUE;
        for(String s:inp) {
            int count = 0;
            for(int i=0;i<s.length();i++) {
                if(hs.contains(s.charAt(i))) {
                    count++;
                }
            }
            if(count>max) {
                max = count;
                ret = s;
            }
        }
        System.out.println(ret);
    }
}
