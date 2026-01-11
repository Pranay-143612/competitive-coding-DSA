import java.util.*;

public class LevelOrderTraversal {

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

    public static List<List<Integer>> levelOrdering(Node root) {
        List<List<Integer>> outer = new ArrayList<>();
        if(root==null) return outer;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int no_of_nodes = q.size();  //tells number of nodes in current level
            List<Integer> inner = new ArrayList<>();
            for(int i=0;i<no_of_nodes;i++) {
                Node cur = q.poll();
                inner.add(cur.value);
                if(cur.left!=null) q.offer(cur.left);
                if(cur.right!=null) q.offer(cur.right);
            }
            outer.add(inner);
        }
        return outer;
    }

    public static void main(String[] args) {
        System.out.println("Wohoo! Lets built the Binary Tree....");
        populate();
        System.out.println("Level by level traversing:" +levelOrdering(root));
    }
}
