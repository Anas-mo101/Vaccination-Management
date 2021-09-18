
import java.util.Scanner;

public class Customer extends User{
    Csvreader csv = new Csvreader();  // to handle all csv actions
    private final int NAME_INDEX = 3;
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;     
    private final int FIRSTVAC_INDEX = 6;      
    private final int SCNDVAC_INDEX = 7;


    Customer(String usertype){
        super(usertype);
    }
    

    public void saveCustomertoFile() {              // recipient regiesteration 
        Scanner input = new Scanner(System.in);
        System.out.println("Register your details !");

        System.out.println("Enter your name:  ");
        String custName = input.nextLine();

        System.out.println("Enter your phone number: ");
        String custPhone = input.nextLine();

        System.out.println("Enter a password: ");
        String password = input.nextLine();

        // writes to the file "customer.csv"
        csv.addUser(password, "Recipient", custName, false, false, custPhone);
    }




    public void ViewCustomerStatus() {
        System.out.println("Name: " + csv.GetUserData(NAME_INDEX,getUserLine()));
        System.out.println("First Vaccine Status -> " +  csv.GetUserData(FSTSTATUS_INDEX,getUserLine()));
        System.out.println("Second Vaccine Status -> " +  csv.GetUserData(SCNDSTATUS_INDEX,getUserLine()));
    }

    public void ViewAppointmentDate(){
        System.out.println("Name: " + csv.GetUserData(NAME_INDEX,getUserLine()));
        System.out.println("First Vaccine Appointment -> " +  csv.GetUserData(FIRSTVAC_INDEX,getUserLine()));
        System.out.println("Second Vaccine Appointment -> " +  csv.GetUserData(SCNDVAC_INDEX,getUserLine()));
    }

}

