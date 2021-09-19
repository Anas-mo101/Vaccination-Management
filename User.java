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

    public User() {
    }

    User(String usertype) {
        login(usertype);
    }

    public void login(String usertype) {
        System.out.println("---------------\n  LOGIN \n---------------");
        while (true) {
            System.out.print("Name (0 to exit): ");
            String Username_in = input.nextLine();
            if(Username_in.equals("0")) {AllMenus.RoleMenu();} // to exit to main menu

            System.out.print("Password (0 to exit): ");
            String Pass_in = input.nextLine();
            if(Pass_in.equals("0")) {AllMenus.RoleMenu();}  // to exit to main menu

            if (UsersData.CheckLoginDetails(Username_in, Pass_in, usertype)) {
                ID = UsersData.GetUserData(ID_INDEX);
                UserType = UsersData.GetUserData(USERTYPE_INDEX);
                Username = UsersData.GetUserData(USERNAME_INDEX);
                Password = UsersData.GetUserData(PASSWORD_INDEX);
                UserLocatedInLine = UsersData.getUserLineLocation();
                System.out.println("SUCCESSFULLY LOGIN!!");
                break;
            } else {
                System.out.println("Invalid user ID or Password");
            }
        }
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
