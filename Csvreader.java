import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Csvreader {
    private List<String> UsersInfo;
    private int UserLocatedInLine;

    Csvreader(){
        ReadFileLine();
    }

    public void ReadFileLine(){
        try{
            String currentPath = System.getProperty("user.dir");  // to get path of current directory 
            UsersInfo = Files.readAllLines(Paths.get(currentPath + "/users.csv"));     // read users.csv into a list of lines.
        }catch(IOException ie) {
            ie.printStackTrace();
        }   
    }

    public Boolean CheckLoginDetails(String ID, String Pass){
        for (int i = 0; i < UsersInfo.size(); i++) {
            String[] items = UsersInfo.get(i).split(",");           // split a line by comma
            String id = items[0];                                  // items[0] is id, items[1] is password, items[2] is usertype,
            String pass = items[1];                               //  items[3] is username , items[4] is 1st vac status, items[5] is 2nd vac status 
            if(ID.equals(id)){
                if(Pass.equals(pass)){
                    System.out.println("Success !");
                    UserLocatedInLine = i;
                    return true;
                }
            }
        }
        System.out.println("Failed !");
        return false;
    }

    public String GetUserData(int index){
        String[] items = UsersInfo.get(UserLocatedInLine).split(","); 
        return  items[index];
    }

}

