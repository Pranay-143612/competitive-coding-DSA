package programs;

import java.util.*;

public class RemoveDuplicates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of elements:");
        int n = sc.nextInt();
        System.out.println("Enter elements:");
        int[] arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = sc.nextInt();
        }
        ArrayList<Integer> result = new ArrayList<>();
        for(int i:arr) {
            if(!result.contains(i)) {
                result.add(i);
            } 
        }
        System.out.println(result);
    }
}
