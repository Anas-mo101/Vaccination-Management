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
    private final int USERTYPE_INDEX = 2;

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
        System.out.println("\tReceive 1st Dose Date of vaccination! \n\t\t ==> "+ (csv.ComparenCountField(4, "Appointment made")) );
        System.out.println("\tComplete 1st Dose of Vaccination! \n\t\t ==> "+ csv.ComparenCountField(4, "Done"));
        System.out.println("\tReceive 2nd Dose Date of vaccination! \n\t\t ==> "+ (csv.ComparenCountField(5, "Appointment made")));
        System.out.println("\tComplete 2nd Dose of Vaccination! \n\t\t ==> "+ csv.ComparenCountField(5, "Done"));
        System.out.println("\tComplete Both Dose of Vaccination! \n\t\t ==> "+ (csv.ComparenCountField(4, "Done")+ csv.ComparenCountField(5, "Done")));
        
    }
/**
 * c) Distribute vaccines and recipients to VCs based on the capacity of the VCs.
 * allow user to re-enter if they input wrong User ID to and from
 * non-case sensitive for assigned vc if the user input vcselangor/vckl in lower/uppercase
 */
    public void distributeVaccine() {
        String v,q,r;
        
        System.out.print("Enter User ID from: ");
        v = input.next();
            
        while(csv.GetUserDataByID(v,0).equals("NOT FOUND")){
            System.out.println("User ID Not Found");
            System.out.print("Re-enter User ID from: ");
            v = input.next();
        }

        System.out.print("Enter User ID to: ");
        q = input.next();
        while(csv.GetUserDataByID(q,0).equals("NOT FOUND")){
            System.out.println("User ID Not Found");
            System.out.print("Re-enter User ID to: ");
            q = input.next();

        }
        do {
            System.out.print("Enter Assigned VC: ");
            r = input.next();
        }while(!r.toLowerCase().equals("vcselangor")&&!r.toLowerCase().equals("vckl"));

        csv.setMultipleUserData(v,q,r,9); 
        System.out.print("Data Vaccination Updated!");
    }

    //public void setRecipientVC(String UserID , String VaccinationID) {
    //    csv.setUserData(UserID,csv.GetUserDataByID(VaccinationID,4),9);
    //}

}


