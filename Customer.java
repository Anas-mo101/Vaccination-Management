import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Customer extends User {
    Csvreader csv = new Csvreader(); // to handle all csv actions
    private final int NAME_INDEX = 3;
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    private final int FIRSTVAC_INDEX = 6;
    private final int SCNDVAC_INDEX = 7;
    private final int PHONE_INDEX = 8;

    public Customer() {
    }

    Customer(String usertype) {
        super(usertype);
    }

    public void saveCustomertoFile() { // recipient regiesteration

        System.out.println("---------------\n REGISTRATION \n---------------");

        String[] status = { "Pending", "1st dose complete", "2nd dose complete" };

        StringBuilder sb = new StringBuilder(); // create random string builder
        Random random = new Random();
        int length = 3;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(status.length); // generate random index number

            // add Character one by one in end of sb
            sb.append(status.length);
        }

        String randomString = sb.toString();
        String custFstVac = randomString;
        String custScndVac = randomString;

        Scanner input = new Scanner(System.in);

        System.out.println("Enter your name:  ");
        String custName = input.nextLine();

        System.out.println("Enter your phone number: ");
        String custPhone = input.nextLine();

        System.out.println("Enter a password: ");
        String password = input.nextLine();

        System.out.println("SUCCESSFULLY REGISTERED!!");

        // writes to the file "customer.csv"
        csv.addUser(password, "recipient", custName, custFstVac, custScndVac, custPhone);
        AllMenus.RoleMenu();
    }

    public void ViewCustomerStatus() {
        System.out.println("Name: " + csv.GetUserData(NAME_INDEX, getUserLine()));
        System.out.println("Phone number: " + csv.GetUserData(PHONE_INDEX, getUserLine()));
        System.out.println("First Vaccine Status -> " + csv.GetUserData(FSTSTATUS_INDEX, getUserLine()));
        System.out.println("Second Vaccine Status -> " + csv.GetUserData(SCNDSTATUS_INDEX, getUserLine()));
    }

    public void ViewAppointmentDate() {
        System.out.println("Name: " + csv.GetUserData(NAME_INDEX, getUserLine()));
        System.out.println("Phone number: " + csv.GetUserData(PHONE_INDEX, getUserLine()));
        System.out.println("First Vaccine Appointment -> " + csv.GetUserData(FIRSTVAC_INDEX, getUserLine()));
        System.out.println("Second Vaccine Appointment -> " + csv.GetUserData(SCNDVAC_INDEX, getUserLine()));
    }

}
