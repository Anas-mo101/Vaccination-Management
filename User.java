import java.util.*;

public class User {
    Csvreader UsersData = new Csvreader();
    Scanner input = new Scanner(System.in);
    private String ID;
    private String Password;
    private String Username;
    private String UserType;
    private int UserLocatedInLine;
    private final int ID_INDEX = 0;
    private final int USERTYPE_INDEX = 2;
    private final int USERNAME_INDEX = 3; // items[0] is id, items[1] is password, items[2] is usertype
    private final int PASSWORD_INDEX = 4;

    User(){

    }

    User(int i) {
        UserLocatedInLine = i;
        ID = UsersData.GetUserData(ID_INDEX, getUserLine());
        UserType = UsersData.GetUserData(USERTYPE_INDEX, getUserLine());
        Username = UsersData.GetUserData(USERNAME_INDEX, getUserLine());
        Password = UsersData.GetUserData(PASSWORD_INDEX, getUserLine());
    }
    
    User(String Username) {
        this.Username = Username;
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
