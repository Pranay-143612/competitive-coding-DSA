package Segment_tres;
import java.util.Arrays;

public class BuildTree {

    //building min-segment tree
    static void build(int[] arr,int[] seg,int low,int high,int ind) {
        if(low==high) {
            seg[ind] = arr[low];
            return;
        }
        int mid = (low+high)/2;
        build(arr, seg, low, mid, 2*ind+1);
        build(arr, seg, mid+1, high, 2*ind+2);
        seg[ind] = Math.min(seg[2*ind+1],seg[2*ind+2]);
    }
    public static void main(String[] args) {
        int[] arr = {2,1,0,4,3,7};
        int n = arr.length;
        int[] seg = new int[4*n];
        build(arr,seg,0,arr.length-1,0);
        System.out.println(Arrays.toString(seg));
    }
}
