package Assignment_one;
import java.util.Scanner;

public class test {
    public static void main (String[] args) { 
        Scanner input = new Scanner(System.in);
        System.out.print("ID: ");
        String User_in = input.nextLine();
        System.out.print("Password: ");
        String Pass_in = input.nextLine();
        User user = new User(User_in,Pass_in);
    }
}
