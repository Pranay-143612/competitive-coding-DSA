package basic_programs;

import java.util.*;

public class RemoveDuplicates {

    public static HashSet<Integer> method2(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for(int i:arr) {
            set.add(i);
        }
        return set;
    }

    public static ArrayList<Integer> method1(int[] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        for(int i:arr) {
            if(!result.contains(i)) {
                result.add(i);
            } 
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of elements:");
        int n = sc.nextInt();
        System.out.println("Enter elements:");
        int[] arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("After removing duplicates:");
        System.out.println(method2(arr));
    }
}
