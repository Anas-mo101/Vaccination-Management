

public class User {
    Csvreader UsersData = new Csvreader();
    private String ID;
    private String Password;
    private String Username;
    private String UserType;
    private final int ID_INDEX = 0;
    private final int PASS_INDEX = 1;
    private final int USERTYPE_INDEX = 2;
    private final int USERNAME_INDEX = 3;       // items[0] is id, items[1] is password, items[2] is usertype,
    private final int FIRSTVAC_INDEX = 4;      //  items[3] is username , items[4] is 1st vac status, items[5] is 2nd vac status 
    private final int SCNDVAC_INDEX = 5;

    User() {}

    User(String ID_in, String Pass_in)                
    {                                             
        if(login(ID_in, Password_in)){
            System.out.println(toString());
        }
    }

    public Boolean login(String ID_in, String Pass_in){
        if(UsersData.CheckLoginDetails(ID_in, Pass_in)){
            ID = ID_in;
            UserType = UsersData.GetUserData(USERTYPE_INDEX); 
            Username = UsersData.GetUserData(USERNAME_INDEX); 
            return true;
        }else{
            return false;
        }
    }
    
    public void resigter(){

    }

    public String toString(){
        if(UserType.equals("recipient")){
            return "ID: " + ID + ", Username: " + Username + ", User type: " + UserType +
                        ", First Dose: " + UsersData.GetUserData(FIRSTVAC_INDEX) + 
                            ", Second Dose: " + UsersData.GetUserData(SCNDVAC_INDEX);
        }else{
            return "ID: " + ID + ", Username: " + Username + ", User type: " + UserType;
        }
    }
}
