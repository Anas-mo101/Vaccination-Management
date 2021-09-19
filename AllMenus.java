import java.util.Scanner;
import java.util.InputMismatchException;

public class AllMenus {

    AllMenus() {
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

                System.out.println("1. Register");
                System.out.println("2. Receipient");
                System.out.println("3. MOH");
                System.out.println("4. VC");
                System.out.println("0. Exit");
                System.out.print("Enter a choice: ");

                Scanner input = new Scanner(System.in);
                choice = input.nextInt();

                switch (choice) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        // customer.saveCustomertoFile();
                        Customer obj = new Customer();
                        obj.saveCustomertoFile();
                        break;
                    case 2:
                        CustomerMenu();
                        break;
                    case 3:
                        MOH();
                        break;
                    case 4:
                        VC();
                        break;
                    default:
                        System.out.println("Wrong input! Please enter again: \n");

                }
            } while ((choice != 1) || (choice != 2));
        } catch (InputMismatchException ex) {
            System.out.println("Only numbers 0 to 4 are permitted. Try Again. \n");
            RoleMenu();
        }

    }

    public static void CustomerMenu() {
        Customer customer = new Customer("recipient");
        Scanner input = new Scanner(System.in);
        String choice;

        do {
            System.out.println();
            System.out.println("------------------------------\n  CUSTOMER MENU \n------------------------------");

            System.out.println("1. View Customer Status");
            System.out.println("2. View Appointment Date");
            System.out.println("0. Exit");
            System.out.print("Enter a choice: ");

            choice = input.nextLine();

            switch (choice) {
                case "0":
                    RoleMenu();
                    break;
                case "1":
                    customer.ViewCustomerStatus();
                    break;
                case "2":
                    customer.ViewAppointmentDate();
                    break;
                default:
                    System.out.println("Invalid input. Please enter again: ");
                    break;
            }

        } while (!choice.equals("0"));
    }

    public static void VC() {
        VaccinationCenter vc = new VaccinationCenter("vcadmin");
        Scanner input = new Scanner(System.in);
        String choice;


            do {
                System.out.println();
                System.out.println(vc.getID());
                System.out.println(vc.getUsername());
                System.out.println("------------------------------\n  VACCINATION CENTER MENU \n------------------------------");

                System.out.println("1. Print Recipient List");
                System.out.println("2. Set Appointment Date");
                System.out.println("3. Vaccine Status");
                System.out.println("0. Exit");
                System.out.print("Enter a choice: ");

                choice = input.nextLine();

                switch (choice) {
                    case "0":
                        RoleMenu();
                        break;
                    case "1":
                        vc.PrintRecipientList();
                        break;
                    case "2":
                        vc.setAppointmentDate();
                        break;
                    case "3":
                        vc.setVaccineStatus();
                        break;
                    default:
                        System.out.println("Invalid input. Please enter again: ");
                        break;
                }
            } while (!choice.equals("0"));
    }

    public static void MOH() {
        Moh moh = new Moh("admin");
        Scanner input = new Scanner(System.in);
        String choice;

            do {
                System.out.println();
                System.out.println("------------------------------\n  MOH MENU \n------------------------------");

                System.out.println("1. Add User");
                System.out.println("2. Search Recipient");
                System.out.println("3. View Recipient List");
                System.out.println("4. Set Vaccine Status");
                System.out.println("0. Exit");
                System.out.print("Enter a choice: ");

                choice = input.nextLine();

                switch (choice) {
                    case "0":
                        RoleMenu();
                        break;
                    case "1":
                        moh.addUser();
                        break;
                    case "2":
                        moh.searchRecipientData();
                        break;
                    case "3":
                        moh.viewData();
                        break;
                    case "4":
                        moh.viewStatistic();
                        break;
                    case "5":
                        moh.distributeVaccine(); 
                        break;
                    default:
                        System.out.println("Invalid input. Please enter again: ");
                        break;
                }
            } while (!choice.equals("0"));
    }
    
    
}
