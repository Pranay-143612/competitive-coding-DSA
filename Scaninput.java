import java.util.*;
public class Scaninput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = sc.next();
        System.out.println("Your name is: "+name);
        sc.close();
    } 
}
