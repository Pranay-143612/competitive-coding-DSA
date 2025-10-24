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
        System.out.println(frequencyEle(arr));
    }
}
