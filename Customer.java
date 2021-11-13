import java.util.Scanner;
import java.util.*;

/**
 * A Customer class representing the details of the customer. 1) custName is the
 * customer's name, 2) custPhone is the customer's phone number, 3) password is
 * the customer's password, 4) custStatus is the customer's status and vaccine
 * appointment
 *
 * @author Sharifah Farah Sofea
 */

public class Customer extends User {
    Csvreader csv = new Csvreader(); // to handle all csv actions
    private final int NAME_INDEX = 3;
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    private final int FIRSTVAC_INDEX = 6;
    private final int SCNDVAC_INDEX = 7;
    private final int PHONE_INDEX = 8;
    private String custPhone;
    private String vacBatchNo;
    private String age;

    Customer() {
    }

    Customer(int i) {
        super(i);
    }
    
    Customer(String Name, String age) { 
        super(Name);
        this.age = age;
    }

    /**
     * Saves the Customer's name, phone number, password, status and vaccine
     * appointment into the "users.csv" file once customer successfully registered
     */
    public void saveCustomertoFile() { // recipient regiesteration
        Scanner input = new Scanner(System.in);

        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                           - REGISTRATION -                                 |");
        System.out.println("+----------------------------------------------------------------------------+");

        System.out.println("Enter your name:  ");
        String custName = input.nextLine();

        while (true) {
            System.out.print("Enter your phone number: ");
            String custPhone = input.nextLine();
            if (valPhone(custPhone) == false)
                System.out.println("Invalid number phone. Please enter back");
            else {
                System.out.println("Valid number phone");
                break;
            }
        }

        System.out.println("Enter a password: ");
        String password = input.nextLine();

        System.out.println(">>>>>>>>>>>>>>>>>>>>> ACCOUNT SUCCESSFULLY REGISTERED!! <<<<<<<<<<<<<<<<<<<<<");

        // writes to the file "customer.csv"
        csv.addUser(password, "recipient", custName, "Pending", "Pending", custPhone, "none");
        AllMenus.RoleMenu();
    }
    
    /**
     * Display Customer details like name, phone number and status of their first
     * dose and second dose vaccine. 1) Pending means still waiting for appointment
     * date, 2) 1st dose complete means the customer has received the first dose, 3)
     * 2nd dose complete means the customer has received the second dose.
     */
    public void ViewCustomerStatus() {
        System.out.println("Name: " + csv.GetUserData(NAME_INDEX, getUserLine()));
        System.out.println("Phone number: " + csv.GetUserData(PHONE_INDEX, getUserLine()));
        System.out.println("First Vaccine Status -> " + csv.GetUserData(FSTSTATUS_INDEX, getUserLine()));
        System.out.println("Second Vaccine Status -> " + csv.GetUserData(SCNDSTATUS_INDEX, getUserLine()));
    }

    /**
     * Display Customer details like name, phone number and appointment date of
     * their vaccine.
     */
    public void ViewAppointmentDate() {
        System.out.println("Name: " + csv.GetUserData(NAME_INDEX, getUserLine()));
        System.out.println("Phone number: " + csv.GetUserData(PHONE_INDEX, getUserLine()));
        System.out.println("First Vaccine Appointment -> " + csv.GetUserData(FIRSTVAC_INDEX, getUserLine()));
        System.out.println("Second Vaccine Appointment -> " + csv.GetUserData(SCNDVAC_INDEX, getUserLine()));
    }
    
    /**
     * Checking a valid phone number of customer whether it meet the requirement
     * such as first character is 0, second character is 1 and the length of phone
     * number is 10 digit.
     * 
     * @param phone number of customer in String
     * @return boolean true or false based on requirements
     */
    public boolean valPhone(String custPhone) {
        return custPhone.charAt(0) == '0' && custPhone.charAt(1) == '1' && custPhone.length() == 10;

    }
    
     public String getAge() {
        return age;
    }
    
    public String getVacBatchNo(){
        return vacBatchNo;
    }
    
    public void setvacBatchNo(String vacBatchNo){
        this.vacBatchNo = vacBatchNo;
    }
}
