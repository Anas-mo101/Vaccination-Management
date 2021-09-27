import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;


/**
 * Used to all handle action done in csv file 
 */
public class Csvreader {
    private List<String> UsersInfo;
    private int UserLocatedInLine;
    private final int PASS_INDEX = 1;
    private final int USERTYPE_INDEX = 2;
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

    /**
     * Opens and loads data from csv file to list
     */
    public void ReadFileLine() {
        try {
            String currentPath = System.getProperty("user.dir"); // to get path of current directory
            UsersInfo = Files.readAllLines(Paths.get(currentPath + "/users.csv")); // read users.csv into a list 
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }


    /**
     * Checks if username and password are correct and runs user according to it 
     * @param Username Username to login 
     * @param Password Password to login
     */
    public void CheckLoginDetails(String Username, String Password) {    // check login details and return usertype
        String UserType = "none";
        for (int i = 0; i < UsersInfo.size(); i++) {
            String[] items = UsersInfo.get(i).split(","); 
            String password = items[PASS_INDEX];                    
            String username = items[NAME_INDEX];                    
            if (Username.equals(username)) {
                if (Password.equals(password)) {
                    UserLocatedInLine = i;
                    UserType = items[USERTYPE_INDEX];
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

    /**
     * Creates and adds new user to csv  
     * @param Password Password for new to login in
     * @param Usertype Type of new user
     * @param Username Username for new to login in
     * @param FstVac First vaccination status (for recipients)
     * @param ScndVac Second vaccination status (for recipients)
     * @param Phone Phone number
     * @param CapPerHr Capacity per hour (for vaccination centers)
     */
    public void addUser(String Password, String Usertype, String Username, String FstVac, String ScndVac,String Phone, String CapPerHr) { // adds

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

    /**
     * Retrives user data by the its line number
     * @param index Data field (attribute) number 
     * @param userline Line (index) where user is located in list 
     * @return String
     */
    public String GetUserData(int index, int userline) { // finds user by line number and gets user data by index
        String[] items = UsersInfo.get(userline).split(",");
        return items[index];
    }

    /**
     * Finds user by ID and prints user's details 
     * @param ID User ID
     */
    public void GetUserInfoByID(String ID) { // gets all user info by ID
        for (int i = 0; i < UsersInfo.size(); i++) {
            String[] items = UsersInfo.get(i).split(",");
            String id = items[0];
            if (ID.equals(id)) {
                System.out.println("User ID: " + ID);
                System.out.println("Name: " + GetUserData(NAME_INDEX, i));
                System.out.println("First Vaccine Status: " + GetUserData(FSTSTATUS_INDEX, i));
                System.out.println("Second Vaccine Status: "+ GetUserData(SCNDSTATUS_INDEX, i));
                System.out.println("First Appoinment: " + GetUserData(FIRSTVAC_INDEX, i));
                System.out.println("Second Appoinment: " + GetUserData(SCNDVAC_INDEX, i));
                System.out.println("Phone: " + GetUserData(PHONE_INDEX, i));
            }
        }
    }


    /**
     * Finds user by ID then retrive data from user info by index
     * @param ID User ID
     * @param index Data field (attribute) number 
     * @return String
     */
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
    
    /**
     * Finds user by Username then retrive data from user info by index
     * @param Username Username
     * @param index Data field (attribute) number
     * @return String
     */
    public String GetUserDataByUsername(String Username, int index) { // gets specific user data by username
        for (int i = 0; i < UsersInfo.size(); i++) {
            String[] items = UsersInfo.get(i).split(",");
            String username = items[NAME_INDEX];
            if (Username.equals(username)) {
                return items[index];
            }
        }
        return "NOT FOUND";
    }

    /**
     * Finds user by ID then edits/replace user's data 
     * @param ID User ID
     * @param Data New data to be added
     * @param index Data field (attribute) number 
     */
    public void setUserData(String ID, String Data, int index) { // searches for user by ID then sets specific data by index
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
    
    /**
     * Edits/Replace data of multiple users 
     * @param Start ID to start from 
     * @param End ID to end from
     * @param Data New data to be added 
     * @param index Data field (attribute) number 
     */
     public void setMultipleUserData(String Start, String End, String Data, int index) {   // searches and sets multiple users data by ID
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


    /**
     * Gets list containing all users
     * @return List<String>
     */
    public List<String> getUserInfo() { // return list containing all user data
        return UsersInfo;
    }

    /**
     * Gets line number belonging to user
     * @return int
     */
    public int getUserLineLocation() { // return line number where user info located at
        return UserLocatedInLine;
    }

    /**
     * Gets the Count of users with speicified matching data field
     * @param index Data field (attribute) number 
     * @param Compare Data to match with
     * @return int
     */
    public int ComparenCountField(int index, String Compare) { // finds and counts user data from speicifed data fields (can be used for Statistic)
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
    
    /**
     * Gets the Counts of users with speicified matching data field according to Vaccination center
     * @param index Data field (attribute) number 
     * @param Compare Data to match with
     * @param Location Vaccination center username
     * @return int
     */
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

    /**
     * Centers user data in middle of cell
     * @param text Userdata
     * @param len Cell length
     * @return String
     */
    public String center(String text, int len){                             // to set the format for "viewData" function
        String out = String.format("%"+len+"s%s%"+len+"s", "",text,"");
        float mid = (out.length()/2);
        float start = mid - (len/2);
        float end = start + len; 
        return out.substring((int)start, (int)end);
    }

    /**
     * Prints and puts use data in a table format
     * @param i User line 
     */
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

    /**
     * Prints and puts use data in a table format according to Vaccination center
     * @param Location Vaccination center location
     */
    public void viewDataByVC(String Location) {        // To view user's data which from the same VC only          // for Vaccination Center use  
        int ROW = getUserInfo().size();
        int count=0;      // "count" is to check there is recipient assigned to the VC or not
        printList(0);    // to print table headings
        for (int i = 4; i<ROW; i++) {
            if(GetUserData(VCASSIGNED_INDEX,i).equals(Location)) {
                printList(i);
                count++;
            }
        }
        if(count==0)                     // If no recipient , "No recipient is assigned to this Vaccination Center!!" will be print out
        System.out.println("No recipient is assigned to this Vaccination Center!!");
    }

    /**
     * Print list of all users
     */
    public  void viewData() {              // To view every user's data in a list form                  // for MOH use
        int ROW = getUserInfo().size();
        for (int i = 0; i<ROW; i++) {
            printList(i);
        }
    }
    
}
