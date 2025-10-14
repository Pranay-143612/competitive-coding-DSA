package trees_da;
import java.util.Scanner;
import java.util.ArrayList;

public class BST {

    private static class Node {
        int value;
        Node left;
        Node right;
        int height;
        Node(int value) {
            this.value = value;
        }
    }

    static Scanner sc = new Scanner(System.in);
    static private Node root;

    public static int height(Node node) {
        if(node==null) {
            return -1;
        }
        return node.height;
    }

    public static void insert(int value) {
        root = insert(value,root);
    }

    public static Node insert(int value,Node node) {
        if(node==null) {
            node = new Node(value);
            return node;
        } 
        if(value<node.value) {
            node.left = insert(value,node.left);
        }
        if(value>node.value) {
            node.right = insert(value,node.right);
        }
        node.height = Math.max(height(node.left),height(node.right))+1;
        return node;
    }

    public static boolean balanced() {
        return balanced(root);
    }

    public static boolean balanced(Node node) {
        if(node==null) {
            return true;
        }
        return Math.abs(height(node.left)-height(node.right))<=1 && balanced(node.left) && balanced(node.right);
    }

    public static void display() {
        display(root, "Root Element: ");
    }

    public static void display(Node node, String msg) {
        if(node==null) return;
        System.out.println(msg+node.value);
        display(node.left,"Left child of "+node.value+": ");
        display(node.right, "Right child of "+node.value+": ");
    }

    //traversals
    public static ArrayList<Integer> inorder() {
        ArrayList<Integer> ls = new ArrayList<>();
        inorder(root,ls);
        return ls;
    }

    public static void inorder(Node node,ArrayList<Integer> ls) {
        if(node==null) {
            return;
        }
        inorder(node.left,ls);
        ls.add(node.value);
        inorder(node.right,ls);
    }

     public static ArrayList<Integer> preorder() {
        ArrayList<Integer> ls = new ArrayList<>();
        preorder(root,ls);
        return ls;
    }

    public static void preorder(Node node,ArrayList<Integer> ls) {
        if(node==null) {
            return;
        }
        ls.add(node.value);
        preorder(node.left,ls);
        preorder(node.right,ls);
    }

     public static ArrayList<Integer> postorder() {
        ArrayList<Integer> ls = new ArrayList<>();
        postorder(root,ls);
        return ls;
    }

    public static void postorder(Node node,ArrayList<Integer> ls) {
        if(node==null) {
            return;
        }
        postorder(node.left,ls);
        postorder(node.right,ls);
        ls.add(node.value);
    }

    public static void main(String[] args) {
        System.out.println("Enter number of elements in the tree:");
        int n = sc.nextInt();
        System.out.println("Enter tree elements");

        for(int i=0;i<n;i++) {
            insert(sc.nextInt());
        }
        display();
        System.out.println("Is the tree balanced? "+balanced());
        System.out.println("Inorder Traversal: "+inorder());
        System.out.println("Preorder Traversal: "+preorder());
        System.out.println("Postorder Traversal: "+postorder());
    }
}
