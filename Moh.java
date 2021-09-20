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
            if(usertype.equals("VC") || usertype.equals("Recipient")){
                break;
            }else{
                System.out.println("Invalid Entery");
            }
            
        }
        csv.addUser(password, usertype, Name, false, false, Phone);
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
    
    public static String center(String text, int len){
        String out = String.format("%"+len+"s%s%"+len+"s", "",text,"");
        float mid = (out.length()/2);
        float start = mid - (len/2);
        float end = start + len; 
        return out.substring((int)start, (int)end);
    }


    public static void viewData() {
        Csvreader csv = new Csvreader();  // to handle all csv actions
        int ROW = csv.getUserInfo().size(), COL = 11;
        String HOR_LINE = "========================================================================================================================================================================================================================================";
        for (int i = 0; i<ROW; i++) {
            System.out.println(HOR_LINE);
            System.out.print("| ");
            for (int j = 0; j<COL; j++){
                System.out.printf(center(csv.GetUserData(j, i), 18));
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
        System.out.println(HOR_LINE);
    }

    // public void viewData(){
    //     userInfo = csv.getUserInfo();   // to update list
    //     for(int i = 4; i < userInfo.size(); i++) {
    //        System.out.println(userInfo.get(i));
    //     }
    // }

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


