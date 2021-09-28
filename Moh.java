import java.util.List;
import java.util.*;

/**
 *MOH is the government agency who has the data of all registered
recipient and vaccination centers (VC).
 */
public class Moh extends User{
    Csvreader csv = new Csvreader();
    Scanner input = new Scanner(System.in);
    List<String> userInfo = csv.getUserInfo();
    private final int ID_INDEX = 0;
    private final int USERTYPE_INDEX = 2;
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    private final int VCASSINGED_INDEX = 9;

    Moh() {
        super();
    }
/**
 * Add User Data to register by name phone number and password to csv 
 * checking for user to input correctly 
 */
    public void addUser() { 
        System.out.println("Enter name:  ");
        String Name = input.nextLine();

        System.out.println("Enter your phone number: ");
        String Phone = input.nextLine();

        System.out.println("Enter a password: ");
        String password = input.nextLine();

        String usertype;
        while(true){ 
            System.out.println("Enter a UserType (VC, Recipient): ");           // check if usertype is enter correctlty
            usertype = input.nextLine();
            if(usertype.equals("Recipient")){
                csv.addUser(password, usertype, Name, "Pending", "Pending", Phone,"none");
                break;
            }if(usertype.equals("VC")){
                System.out.println("Enter a Capacity per hour: ");
                String Capa = input.nextLine();
                csv.addUser(password, "vcadmin", Name, "none", "none", Phone, Capa);
                break;
            }else{
                System.out.println("Invalid Entery");
            }
            
        }
    }
/**
 * b) Search a recipient and view their details.
 */
    public void searchRecipientData() {  // Unique Primary Key
        System.out.print("Enter User ID: ");
        String ID_in = input.nextLine();
        if(csv.GetUserDataByID(ID_in,USERTYPE_INDEX).equals("recipient")){
            csv.GetUserInfoByID(ID_in);
        }else{
            System.out.print("User is not Recipient");
        }
    }
/**
 * a) View all recipient data and vaccination status from all VCs (multi-column format)
 */   
   public void viewAllData() {
        userInfo = csv.getUserInfo();
        csv.viewData();
    }

/**
 * d) View various statistics combining all VCs such as total vaccination, total by day, etc.
 */
    public void viewStatistic() { 
    System.out.println("\tVaccination Stats:\n");
    System.out.println("\tReceive 1st Dose Date of vaccination! \n\t\t ==> "+ (csv.ComparenCountField(FSTSTATUS_INDEX, "Appointment made")) );
    System.out.println("\tComplete 1st Dose of Vaccination! \n\t\t ==> "+ csv.ComparenCountField(FSTSTATUS_INDEX, "Done"));
    System.out.println("\tReceive 2nd Dose Date of vaccination! \n\t\t ==> "+ (csv.ComparenCountField(SCNDSTATUS_INDEX, "Appointment made")));
    System.out.println("\tComplete 2nd Dose of Vaccination! \n\t\t ==> "+ csv.ComparenCountField(SCNDSTATUS_INDEX, "Done"));
    System.out.println("\tComplete Both Dose of Vaccination! \n\t\t ==> "+ (csv.ComparenCountField(FSTSTATUS_INDEX, "Done")+ csv.ComparenCountField(SCNDSTATUS_INDEX, "Done")));
    }
/**
 * c) Distribute vaccines and recipients to VCs based on the capacity of the VCs.
 * allow user to re-enter if they input wrong User ID to and from
 */
    public void distributeVaccine() {
        String StartID,EndID,AssignedVC;

        while(true){
            System.out.print("Enter User ID from (0 to exit): ");
            StartID = input.next();

            if(!csv.GetUserDataByID(StartID,ID_INDEX).equals("NOT FOUND") || StartID.equals("0")){
                break;
            }
            System.out.println("User ID Not Found");
        }

        while(true){
            System.out.print("Enter User ID to (0 to exit): ");
            EndID = input.next();

            if(!csv.GetUserDataByID(EndID,ID_INDEX).equals("NOT FOUND") || EndID.equals("0")){
                break;
            }
            System.out.println("User ID Not Found");
        }

        while( !StartID.equals("0") && !EndID.equals("0") ){
            System.out.print("Enter Assigned Vaccination center: ");
            AssignedVC = input.next();
            if(csv.GetUserDataByUsername(AssignedVC, USERTYPE_INDEX).equals("vcadmin")){
                csv.setMultipleUserData(StartID,EndID,AssignedVC,VCASSINGED_INDEX); 
                System.out.println("Data Vaccination Updated!");
                break;
            }else{
                System.out.println("Vaccination center does not exist (0 to exit)");
                csv.viewDataByIndex("vcadmin", USERTYPE_INDEX);
            }
            if(AssignedVC.equals("0")){
                break;
            }
        }
    }
}


