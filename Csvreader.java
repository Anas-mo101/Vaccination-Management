import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class Csvreader {
    private List<String> UsersInfo;
    private int UserLocatedInLine;
    private final int NAME_INDEX = 3;
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    private final int FIRSTVAC_INDEX = 6;
    private final int SCNDVAC_INDEX = 7;
    private final int PHONE_INDEX = 8;

    Csvreader() {
        ReadFileLine();
    }

    public void ReadFileLine() {
        try {
            String currentPath = System.getProperty("user.dir"); // to get path of current directory
            UsersInfo = Files.readAllLines(Paths.get(currentPath + "/users.csv")); // read users.csv into a list of
                                                                                   // lines.
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public Boolean CheckLoginDetails(String Username, String Password, String Usertype) {
        for (int i = 0; i < UsersInfo.size(); i++) {
            String[] items = UsersInfo.get(i).split(","); // split a line by comma
            String password = items[1]; // items[0] is id, items[1] is password, items[2] is usertype,
            String usertype = items[2];
            String username = items[3]; // items[3] is username , items[4] is 1st vac status, items[5] is 2nd vac status
            if (Username.equals(username)) {
                if (Password.equals(password)) {
                    if (Usertype.equals(usertype)) {
                        UserLocatedInLine = i;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void addUser(String Password, String Usertype, String Username, Boolean FstVac, Boolean ScndVac,
            String Phone) { // adds
        // new
        // user
        int Last_ID = Integer.parseInt(GetUserData(0, UsersInfo.size() - 1));
        ++Last_ID; // get the last ID in csv and increaments it to next ID
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.csv", true));
            writer.append("\n" + Last_ID + "," + Pass + "," + Usertype + "," + Username + "," + FstVac + "," + ScndVac
                    + ",none,none" + Phone + ",none");
            writer.close();
        } catch (IOException ex) {
            System.out.println("No file found.");
        }
    }

    public String GetUserData(int index) { // gets user data by index
        String[] items = UsersInfo.get(UserLocatedInLine).split(",");
        return items[index];
    }

    public String GetUserData(int index, int userline) { // finds user by line number and gets user data by index
        String[] items = UsersInfo.get(userline).split(",");
        return items[index];
    }

    public void GetUserInfoByID(String ID) { // gets all user info by ID
        for (int i = 0; i < UsersInfo.size(); i++) {
            String[] items = UsersInfo.get(i).split(",");
            String id = items[0];
            if (ID.equals(id)) {
                System.out.println("User ID: " + ID + ", Name: " + GetUserData(NAME_INDEX, i)
                        + ", First Vaccine Status: " + GetUserData(FSTSTATUS_INDEX, i) + ", Second Vaccine Status: "
                        + GetUserData(SCNDSTATUS_INDEX, i) + ", First Appoinment: " + GetUserData(FIRSTVAC_INDEX, i)
                        + ", Second Appoinment: " + GetUserData(SCNDVAC_INDEX, i) + ", Phone: "
                        + GetUserData(PHONE_INDEX, i));
            }
        }
    }

    public String GetUserDataByID(String ID, int index) { // gets specific user data by ID
        for (int i = 0; i < UsersInfo.size(); i++) {
            String[] items = UsersInfo.get(i).split(",");
            String id = items[0];
            if (ID.equals(id)) {
                return items[index];
            }
        }
        return "NOT FOUND";
    }

    public void setUserData(String ID, String Data, int index) { // searches for user by ID then sets specific data by
                                                                 // index
        int LineToBeEdited = 0;
        String newLine = "";
        String[] items;

        for (int i = 0; i < UsersInfo.size(); i++) {
            items = UsersInfo.get(i).split(",");
            String id = items[0];
            if (ID.equals(id)) {
                LineToBeEdited = i;
                items[index] = Data;
                for (int x = 0; x < items.length; x++) {
                    if (x == 0) {
                        newLine += items[x];
                    } else {
                        newLine += "," + items[x];
                    }
                }
            }
        }

        UsersInfo.remove(LineToBeEdited);
        UsersInfo.add(LineToBeEdited, newLine);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.csv", false));
            for (int i = 0; i < UsersInfo.size(); i++) {
                if (i == 0) {
                    writer.write(UsersInfo.get(i));
                } else {
                    writer.write('\n' + UsersInfo.get(i));
                }
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("No file found.");
        }
    }

    public List<String> getUserInfo() { // return list containing all user data
        return UsersInfo;
    }

    public int getUserLineLocation() { // return line number where user info located at
        return UserLocatedInLine;
    }

    public int ComparenCountField(int index, String Compare) { // finds and counts user data from speicifed data fields
                                                               // (can be used for Statistic)
        int c = 0;
        for (int i = 0; i < UsersInfo.size(); i++) {
            String[] items = UsersInfo.get(i).split(",");
            String Comparedto = items[index];
            if (Comparedto.equals(Compare) && Comparedto != null) {
                c++;
            }
        }
        return c;
    }

}
