import java.util.ArrayList;
import java.util.*;

public class Moh extends User{
    Csvreader csv = new Csvreader();
    Scanner input = new Scanner(System.in);
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

    public void viewData(){
        for(int i = 4; i < csv.getUserInfo().size(); i++) {
            System.out.println(csv.getUserInfo().get(i));
        }
    }

    public void viewStatistic() { 
        System.out.print("To Be Continued");
    }

    public void distributeVaccine(int Vac) {
        System.out.print("Vaccination center doesn't have the vaccine count");
    }

    public void distributeRecipient(){
        System.out.print("Don't know how to get specific VC");
        // while (!checkCapacity("Some Date")) {
        //     ; // keep assigning recipient to that vc while its not full
        // }
    }

}


