import java.util.List;
import java.util.*;

public class Moh extends User{
    Csvreader csv = new Csvreader();
    Scanner input = new Scanner(System.in);
    List<String> userInfo = csv.getUserInfo();
    private final int USERTYPE_INDEX = 2;

    Moh(String usertype) {
        super(usertype);
    }


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
                csv.addUser(password, usertype, Name, false, false, Phone,"none");
                break;
            }if(usertype.equals("VC")){
                System.out.println("Enter a Capacity per hour: ");
                String Capa = input.nextLine();
                csv.addUser(password, "vcadmin", Name, false, false, Phone, Capa);
                break;
            }else{
                System.out.println("Invalid Entery");
            }
            
        }
    }

    public void searchRecipientData() {  // Unique Primary Key
        System.out.print("Enter User ID: ");
        String ID_in = input.nextLine();
        if(csv.GetUserDataByID(ID_in,USERTYPE_INDEX).equals("recipient")){
            csv.GetUserInfoByID(ID_in);
        }else{
            System.out.print("User is not Recipient");
        }
    }
    
   public void viewAllData() {
        userInfo = csv.getUserInfo();
        csv.viewData();
    }

    public void viewStatistic() { 
        System.out.println("\tVaccination Stats:\n");
        System.out.println("\tReceive 1st Dose Date of vaccination! \n\t\t ==> "+ (csv.ComparenCountField(4, "Appointment made")) );
        System.out.println("\tComplete 1st Dose of Vaccination! \n\t\t ==> "+ csv.ComparenCountField(4, "Done"));
        System.out.println("\tReceive 2nd Dose Date of vaccination! \n\t\t ==> "+ (csv.ComparenCountField(5, "Appointment made")));
        System.out.println("\tComplete 2nd Dose of Vaccination! \n\t\t ==> "+ csv.ComparenCountField(5, "Done"));
        System.out.println("\tComplete Both Dose of Vaccination! \n\t\t ==> "+ (csv.ComparenCountField(4, "Done")+ csv.ComparenCountField(5, "Done")));
        
    }

    public void distributeVaccine() {
        System.out.print("Vaccination center doesn't have the vaccine count");
    }

    public void setRecipientVC(String UserID , String VaccinationID) {
        csv.setUserData(UserID,csv.GetUserDataByID(VaccinationID,4),9);
    }

}


