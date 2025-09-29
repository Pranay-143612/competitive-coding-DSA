package trees_da;
import java.util.Scanner;

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

    public int height(Node node) {
        if(node==null) {
            return -1;
        }
        return node.height;
    }

    public void insert(int value) {

    }

    public Node insert(int value,Node node) {
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

    public static void main(String[] args) {
        
    }
}
