
import java.util.*;

public class User{
    Csvreader UsersData = new Csvreader();
    Scanner input = new Scanner(System.in);
    private String ID;
    private String Password;
    private String Username;
    private String UserType;
    private int UserLocatedInLine;
    private final int USERTYPE_INDEX = 2;
    private final int USERNAME_INDEX = 3;       // items[0] is id, items[1] is password, items[2] is usertype

    User(String usertype){                                             
        login(usertype);
    }

    public void login(String usertype){
        
        while(true){
            System.out.print("ID: ");
            String ID_in = input.nextLine();
            System.out.print("Password: ");
            String Pass_in = input.nextLine();

            if(UsersData.CheckLoginDetails(ID_in, Pass_in, usertype)){
                ID = ID_in;
                UserType = UsersData.GetUserData(USERTYPE_INDEX); 
                Username = UsersData.GetUserData(USERNAME_INDEX); 
                UserLocatedInLine = UsersData.getUserLineLocation();
                System.out.println("Loged in");
                break;
            }else{
                System.out.println("Invalid user ID or Passwod");
            }
        }
    }
    
    public void resigter(){

    }

    public String getID() {
        return ID;
    }

    public String getUsername() {
        return Username;
    }

    public int getUserLine(){
        return UserLocatedInLine;
    }
}

