import java.util.*;

import org.w3c.dom.Node;
public class AVL {

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

    public void insert(int value) {
        root = insert(value,root);
    }

    public Node insert(int value,Node node) {
        if(node == null) {
            return new Node(value);
        }
        if(value<node.value) {
            node.left = insert(value, node.left);
        }
        if(value>node.value) {
            node.left = insert(value, node.right);
        }
        node.height = Math.max(height(node.left),height(node.right))+1;
        return node;
    }

    public void rotate(Node node) {
        if(height(node.right)-height(node.right)<-1) {
            //left side is heavy
            if(height(node.left.right)-height(node.left.left)<0) {
                //left-left
                rotateRight(node);
            }
            if(height(node.left.right)-height(node.left.left)>0) {
                //left-right
                rotateLeft(node.left);
                rotateRight(node);
            }
        }
    }

    public void rotateLeft(Node node) {
        if()
    }

    public int height(Node node) {
        if(node==null) {
            return -1;
        }
        return node.height;
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

    public static void main(String[] args) {
        System.out.println("Enter number of elements in the tree:");
        int n = sc.nextInt();
        System.out.println("Enter tree elements");

        for(int i=0;i<n;i++) {
            insert(sc.nextInt());
        }
        display();
    }
}
