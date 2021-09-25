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
    private final int VCASSIGNED_INDEX = 9;
    
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

    public void CheckLoginDetails(String Username, String Password) {    // check login details and return usertype
        String UserType = "none";

        for (int i = 0; i < UsersInfo.size(); i++) {
            String[] items = UsersInfo.get(i).split(","); // split a line by comma
            String password = items[1];                    // items[0] is id, items[1] is password, items[2] is usertype,
            String username = items[3];                       // items[3] is username , items[4] is 1st vac status, items[5] is 2nd vac status
            if (Username.equals(username)) {
                if (Password.equals(password)) {
                    UserLocatedInLine = i;
                    UserType = items[2];
                }
            }
        }

        switch(UserType){  
            case "admin": AllMenus.MOH();
                          break;

            case "vcadmin": AllMenus.VC(UserLocatedInLine);
                            break;

           case "recipient": AllMenus.CustomerMenu(UserLocatedInLine);
                            break;

           default: System.out.println("Invalid user ID or Password");
                    System.out.println("Thank you. Bye!"); 
                    System.exit(0);
                    break;
          }   
    }

    public void addUser(String Password, String Usertype, String Username, Boolean FstVac, Boolean ScndVac,
            String Phone, String CapPerHr) { // adds
        // new
        // user
        int Last_ID = Integer.parseInt(GetUserData(0, UsersInfo.size() - 1));
        ++Last_ID; // get the last ID in csv and increaments it to next ID
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.csv", true));
            writer.append("\n" + Last_ID + "," + Password + "," + Usertype + "," + Username + "," + FstVac + "," + ScndVac
                    + ",none,none," + Phone + ",none," + CapPerHr);
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
                UsersInfo.remove(LineToBeEdited);
                UsersInfo.add(LineToBeEdited, newLine);
                break;
            }
        }

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
                                                        // searches and sets multiple users data by ID
     public void setMultipleUserData(String Start, String End, String Data, int index) { 
        int LineToBeEdited = 0;
        String[] items;
        String currentID = Start;

        for (int i = 0; i < UsersInfo.size(); i++) {
            String newLine = "";
            items = UsersInfo.get(i).split(",");
            String id = items[0];

            if (currentID.equals(id)) {
                LineToBeEdited = i;
                items[index] = Data;
                for (int x = 0; x < items.length; x++) {
                    if (x == 0) {
                        newLine += items[x];
                    } else {
                        newLine += "," + items[x];
                    }
                }
                UsersInfo.remove(LineToBeEdited);
                UsersInfo.add(LineToBeEdited, newLine);
                currentID = Integer.toString(Integer.parseInt(currentID) + 1);
                if(currentID.equals(Integer.toString(Integer.parseInt(End) + 1))) break;
            }
        }

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
    
    public int ComparenCountFieldByVC(int index, String Compare, String Location) { // finds and counts user data from speicifed data fields according to the VC assigned                                                      
        int c = 0;
        for (int i = 0; i < UsersInfo.size(); i++) {
            String[] items = UsersInfo.get(i).split(",");
            String Comparedto = items[index];
            String vcAssigned = items[VCASSIGNED_INDEX];
            if(Location.equals(vcAssigned)){
                if (Comparedto.equals(Compare) && Comparedto != null) {
                    c++;
                }
            }
        }
        return c;
    }

    public String center(String text, int len){                             // to set the format for "viewData" function
        String out = String.format("%"+len+"s%s%"+len+"s", "",text,"");
        float mid = (out.length()/2);
        float start = mid - (len/2);
        float end = start + len; 
        return out.substring((int)start, (int)end);
    }

    public void printList(int i) {                                  // print list function
        String HOR_LINE = "==================================================================================================================================================================================================================";
        System.out.println(HOR_LINE);
        System.out.print("| ");
        int COL=11;
        for (int j = 0; j<COL; j++){
            System.out.printf(center(GetUserData(j, i), 16));
            System.out.print(" | ");
        }
        System.out.print("\n");
        System.out.println(HOR_LINE);
    }

    public void viewDataByVC(String Location) {        // To view user's data which from the same VC only          // for Vaccination Center use  
        int ROW = getUserInfo().size();
        int count=0;      // "count" is to check there is recipient assigned to the VC or not
        for (int i = 0; i<1; i++) {
                printList(i);
        }
        for (int i = 4; i<ROW; i++) {
            if(GetUserData(VCASSIGNED_INDEX,i).equals(Location)) {
                printList(i);
                count++;
            }
        }
        if(count ==0)                     // If no recipient , "No recipient is assigned to this Vaccination Center!!" will be print out
        System.out.println("No recipient is assigned to this Vaccination Center!!");
    }

    public  void viewData() {              // To view every user's data in a list form                  // for MOH use
        int ROW = getUserInfo().size();
        for (int i = 0; i<ROW; i++) {
            printList(i);
        }
    }
    
}
