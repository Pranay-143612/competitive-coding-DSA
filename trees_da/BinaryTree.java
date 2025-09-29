package trees_da;
import java.util.Scanner;

class BinaryTree {

    private static class Node {
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
        }
    } 

    static Scanner sc = new Scanner(System.in);
    static private Node root;

    static void populate() {
        System.out.println("Enter the root:");
        root = new Node(sc.nextInt());
        populate(root);
    }

    private static void populate(Node node) {
        System.out.println("Do you want to create a left child to the node "+node.value);
        int left = sc.nextInt();
        if(left==1) {
            System.out.println("Enter the value of left child:");
            int value = sc.nextInt();
            node.left = new Node(value);
            populate(node.left);
        }
        System.out.println("Do you want to create a right child to the node "+node.value);
        int right = sc.nextInt();
        if(right==1) {
            System.out.println("Enter the value of right child:");
            int value = sc.nextInt();
            node.right = new Node(value);
            populate(node.right);
        }
    }

    static void display(Node node) {
        if(node==null) {
            return;
        }
        System.out.print(node.value+" ");
        display(node.left);
        display(node.right);
    }

    public static void main(String[] args) {
        System.out.println("Wohoo! Lets built the Binary Tree....");
        populate();
        System.out.println("The tree is:");
        display(root);
    }
}