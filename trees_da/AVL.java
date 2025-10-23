import java.util.*;

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

    public static void insert(int value) {
        root = insert(value,root);
    }

    public static Node insert(int value,Node node) {
        if(node == null) {
            return new Node(value);
        }
        if(value<node.value) {
            node.left = insert(value, node.left);
        }
        if(value>node.value) {
            node.right = insert(value, node.right);
        }
        node.height = Math.max(height(node.left),height(node.right))+1;
        return rotate(node);
    }

    public static Node rotate(Node node) {
        if(height(node.left)-height(node.right)>1) {
            //left side is heavy
            if(height(node.left.left)-height(node.left.right)<0) {
                //left-left
                return rotateRight(node);
            }
            if(height(node.left.left)-height(node.left.right)>0) {
                //left-right
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }
        if(height(node.left)-height(node.right)<-1) {
            //right side is heavy
            if(height(node.right.left)-height(node.right.right)<0) {
                //right-right
                return rotateLeft(node);
            }
            if(height(node.right.left)-height(node.right.right)>0) {
                //right-left
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }
        return node;
    }

    public static Node rotateLeft(Node p) {
        if (p == null || p.right == null) return p;
        Node c = p.right;
        Node t = c.left;
        c.left  = p;
        p.right = t;
        p.height = Math.max(height(p.left),height(p.right))+1;
        c.height = Math.max(height(c.left),height(c.right))+1;
        return c;
    }

    public static Node rotateRight(Node p) {
        if (p == null || p.left == null) return p;
        Node c = p.left;
        Node t = c.right;
        c.right = p;
        p.left = t;
        p.height = Math.max(height(p.left),height(p.right))+1;
        c.height = Math.max(height(c.left),height(c.right))+1;
        return c;
    }

    public static int height(Node node) {
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

        int[] arr = {7,4,2,1,9,8,6,5};
        for(int i:arr) {
            insert(i);
        }
        System.out.println("Is tree balanced: "+balanced());
        display();
    }
}
