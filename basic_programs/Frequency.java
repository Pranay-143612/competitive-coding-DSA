package basic_programs;
import java.util.*;

//count the frequency of each element in an array

public class Frequency {

    public static HashMap<Integer,Integer> frequencyEle(int[] arr) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i:arr) {
            hm.put(i,hm.getOrDefault(i, 0)+1);
        }
        return hm;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = sc.nextInt();  
        }
        HashMap<Integer,Integer> hm = frequencyEle(arr);
        int max = Integer.MIN_VALUE;
        int secMax = Integer.MIN_VALUE;
        int ele = -1;
        int prev = -1;
        for(int i:hm.keySet()) {
            if(hm.get(i)>max) {
                secMax = max;
                ele = prev;
                max = hm.get(i);
                prev = i;
            }
            else if(hm.get(i)>secMax&&hm.get(i)<max) {
                secMax = hm.get(i);
                ele = i;
            }
        }
        System.out.println(hm);
        System.out.println("Second largest: "+ele);
    }
}
