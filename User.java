import java.util.*;

public class User {
    Csvreader UsersData = new Csvreader();
    Scanner input = new Scanner(System.in);
    private String ID;
    private String Password;
    private String Username;
    private String UserType;
    private int UserLocatedInLine;
    private final int USERTYPE_INDEX = 2;
    private final int USERNAME_INDEX = 3; 
    private final int PASSWORD_INDEX = 4;

    public User() {
    }

    User(String usertype) {
        login(usertype);
    }

    public void login(String usertype) {
        System.out.println("---------------\n  LOGIN \n---------------");
        while (true) {
            System.out.print("Name: ");
            String Username_in = input.nextLine();
            System.out.print("Password: ");
            String Pass_in = input.nextLine();

            if (UsersData.CheckLoginDetails(Username_in, Pass_in, usertype)) {
                Username = Username_in;
                Password = Pass_in;
                UserType = UsersData.GetUserData(USERTYPE_INDEX);
                Username = UsersData.GetUserData(USERNAME_INDEX);
                UserLocatedInLine = UsersData.getUserLineLocation();
                Password = UsersData.GetUserData(PASSWORD_INDEX);
                UserLocatedInLine = UsersData.getUserLineLocation();
                System.out.println("SUCCESSFULLY LOGIN!!");
                break;
            } else {
                System.out.println("Invalid user ID or Password");
            }
        }
    }

    public void resigter() {

    }

    public String getID() {
        return ID;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getUserType() {
        return UserType;
    }

    public int getUserLine() {
        return UserLocatedInLine;
    }
}
