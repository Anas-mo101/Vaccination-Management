import java.util.Scanner;
import java.util.InputMismatchException;

public class AllMenus {

    AllMenus() {
        RoleMenu();
    }

    
    public static void RoleMenu() {
        int choice;
        try {
            do {
                System.out.println("\n+----------------------------------------------------------------------------+");
                System.out.println("|               WELCOME TO JAVA COVID-19 VACCINATION PROGRAM                 |");
                System.out.println("|----------------------------------------------------------------------------|");
                System.out.println("|                            - MAIN MENU -                                   |");
                System.out.println("|----------------------------------------------------------------------------|");
                System.out.println("|                            [1] Register                                    |");
                System.out.println("|                            [2] Login                                       |");                               
                System.out.println("|                            [0] Exit                                        |");
                System.out.println("+----------------------------------------------------------------------------+");
                System.out.print("  Enter a choice:  ");

                Scanner input = new Scanner(System.in);
                choice = input.nextInt();

                switch (choice) {
                    case 0: System.out.println("Thank you. Bye!"); 
                            System.exit(0);
                            break;
                    case 1: Customer obj = new Customer();
                            obj.saveCustomertoFile();
                            break;
                    case 2: Login.main(args);
                            break;    
                    default:
                        System.out.println("Wrong input! Please enter again: \n");
                        break;
                }

            } while ((choice != 1) || (choice != 2));
        } catch (InputMismatchException ex) {
            System.out.println("Only numbers 0 to 4 are permitted. Try Again. \n");
            RoleMenu();
        }

    }

    public static void CustomerMenu(int i) {
        Customer customer = new Customer(i);
        Scanner input = new Scanner(System.in);
        String choice;

        do {
            System.out.println();
             System.out.println("-----> ID: " + customer.getID() + " -  Username: "+ customer.getUsername() + " <-----");
            System.out.println("+----------------------------------------------------------------------------+");
            System.out.println("|                           - RECIPIENT MENU -                               |");
            System.out.println("|----------------------------------------------------------------------------|");
            System.out.println("|                            [1] View Recipient Status                       |");
            System.out.println("|                            [2] View Appointment Date                       |");
            System.out.println("|                            [0] Exit                                        |");
            System.out.println("+----------------------------------------------------------------------------+");
            System.out.print(" Enter a choice:  ");

            choice = input.nextLine();

            switch (choice) {
                case case "0": System.out.println("Thank you. Bye!"); 
                    System.exit(0);
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

    
    public static void VC(int i) {
        VaccinationCenter vc = new VaccinationCenter(i);
        Scanner input = new Scanner(System.in);
        String choice;

            do {
                System.out.println();
            System.out.println("-----> ID: " + customer.getID() + " -  Username: "+ customer.getUsername() + " <-----");
            System.out.println("+----------------------------------------------------------------------------+");
            System.out.println("|                           - VACCINATION CENTER MENU -                      |");
            System.out.println("|----------------------------------------------------------------------------|");
            System.out.println("|                            [1] Print Recipient List                        |");
            System.out.println("|                            [2] Set Appointment Date                        |");
            System.out.println("|                            [3] Set Vaccine Status                          |");
            System.out.println("|                            [4] View Vaccination Static                     |");    
            System.out.println("|                            [0] Exit                                        |");
            System.out.println("+----------------------------------------------------------------------------+");
            System.out.print(" Enter a choice:  ");

                choice = input.nextLine();

                switch (choice) {
                    case "0": System.out.println("Thank you. Bye!"); 
                            System.exit(0);
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
                    case "4":
                        vc.viewTotalVaccination();
                        break;
                    default:
                        System.out.println("Invalid input. Please enter again: ");
                        break;
                }
            } while (!choice.equals("0"));
    }

    public static void MOH() {
        Moh moh = new Moh();
        Scanner input = new Scanner(System.in);
        String choice;

            do {
                System.out.println();
            System.out.println("+----------------------------------------------------------------------------+");
            System.out.println("|                               - MOH MENU -                           	     |");
            System.out.println("|----------------------------------------------------------------------------|");
            System.out.println("|                            [1] Add User                      	             |");
            System.out.println("|                            [2] Search Recipient                            |");
            System.out.println("|                            [3] View Recipient List                         |");
            System.out.println("|                            [4] View Vaccination Static                     |");
            System.out.println("|                            [5] Distribute Vaccine                          |");
            System.out.println("|                            [0] Exit                                        |");
            System.out.println("+----------------------------------------------------------------------------+");
            System.out.print(" Enter a choice:  ");

                choice = input.nextLine();

                switch (choice) {
                    case "0": System.out.println("Thank you. Bye!"); 
                              System.exit(0);
                              break;
                    case "1":
                        moh.addUser();
                        break;
                    case "2":
                        moh.searchRecipientData();
                        break;
                    case "3":
                        moh.viewAllData();
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
