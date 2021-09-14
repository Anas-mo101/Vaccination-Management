
import java.util.Scanner;
import java.util.InputMismatchException;

public class AllMenus extends Customer {
    public static void main(String[] args) {
        RoleMenu();
    }

    // Displays the selection menu for roles to choose a role. (Customer, MOH, VC)
    public static void RoleMenu() {
        int choice;
        try {
            do {
                System.out.println("\n+==========================================================================+");
                System.out.println("|               WELCOME TO JAVA COVID-19 VACCINATION PROGRAM                 |");
                System.out.println("+============================================================================+");

                System.out.println("Receipient view");
                System.out.println("1. Receipient");
                // System.out.println("2. MOH");
                // System.out.println("3. VC");
                System.out.println("0. Exit");
                System.out.print("Enter a choice: ");

                Scanner input = new Scanner(System.in);
                choice = input.nextInt();

                switch (choice) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        CustomerMenu();
                        break;
                    // case 2:
                    // MOH();
                    // break;
                    // case 3:
                    // VC();
                    // break;
                    default:
                        System.out.println("Wrong input! Please enter again: \n");

                }
            } while ((choice != 1) || (choice != 2));
        } catch (InputMismatchException ex) {
            System.out.println("Only numbers 0 to 3 are permitted. Try Again. \n");
            RoleMenu();
        }

    }

    public static void CustomerMenu() {

        Scanner input = new Scanner(System.in);
        boolean signInValue = false;
        int choice;

        try {
            do {
                System.out.println();
                System.out.println("Customer Menu\n");
                System.out.println("1.Register");
                System.out.println("2.Sign-In");
                System.out.println("0. Exit");
                System.out.print("Enter a choice: ");

                choice = input.nextInt();

                switch (choice) {
                    case 0:
                        RoleMenu();
                        break;
                    case 1:
                        AllMenus obj = new AllMenus();
                        obj.saveCustomertoFile();
                        break;
                    case 2:
                        AllMenus obj1 = new AllMenus();
                        obj1.CustomerSignIn();
                        // signInValue = CustomerSignIn();
                        break;
                    default:
                        System.out.println("Invalid input. Please enter again: ");
                        break;
                }

            } while (choice != 0);
        } catch (InputMismatchException ex) {
            System.out.println("Enter a number. Please try Again. \n");
            CustomerMenu();
        }
    }
}