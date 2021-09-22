import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VaccinationCenter extends User {
    Csvreader csv = new Csvreader();
    private final int CAPACITY_INDEX = 10;
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    private final int FSTVACDATE_INDEX = 6;
    private final int SCNDVACDATE_INDEX = 7;
    private int CapacityPerHour;
    List<String> userInfo = csv.getUserInfo();
    ArrayList<String> dateList = new ArrayList<String>();

    VaccinationCenter(String usertype){
        super(usertype);
        CapacityPerHour = Integer.parseInt(csv.GetUserDataByID(getID(),CAPACITY_INDEX));
    }


    public void PrintRecipientList() {                  // This function is to print the recipient list which all the recipients are from the same VC
        userInfo = csv.getUserInfo();                // to update list everytime function is called
        csv.viewDataByVC(getUsername());
    }

   
    public void setAppointmentDate() {                 // set the appoinment date for recipient
        
        System.out.println("Enter Recipient ID: ");
        String ID = input.nextLine();                                             
        while(csv.GetUserDataByID(ID, 9).equals(getUsername())){                      // Checks if Recipient is assigned to current vaccination center 
            System.out.println("Enter either first or second vaccination (1/2): ");
            int WhichVac = Integer.parseInt(input.nextLine());
            int WhichStatus = 0;
            if(WhichVac == 1){
                WhichVac = FSTVACDATE_INDEX;
                WhichStatus = FSTSTATUS_INDEX;
            }if(WhichVac == 2){
                WhichVac = SCNDVACDATE_INDEX;
                WhichStatus = SCNDSTATUS_INDEX;
            }
        
            System.out.println("Enter Appointment Date(DD/MM/YYYY): ");
            String Date = input.nextLine();
            System.out.println("Enter Appointment Time (08:00-18:00): ");
            String Time = input.nextLine();
    
            if(checkCapacityDay(Date) && checkCapacityHour(Date)){
                csv.setUserData(ID,Date + " - " + Time,WhichVac);                       //set date & time
                csv.setUserData(ID,"Appointment made",WhichStatus);                     //automatically set the vaccination status
                dateList.add(Date);                                         // add the appointment date that made successfully to array list
                System.out.println("Appointment made Successfully!!"); 
                break;
            }else{
                System.out.println("Max Capacity Reached!!");
                break;
            }
        }
        if(!csv.GetUserDataByID(ID, 9).equals(getUsername())){
            System.out.println("This recipient is not assgined to this Vaccination Center!! Please try again!!");
        }      
    }
    

    public void setVaccineStatus() {           // to change vaccination status
        System.out.println("Enter Recipient ID: ");
        String ID = input.nextLine();

        System.out.println("Enter either first or second vaccination (1/2): ");
        int WhichVac = Integer.parseInt(input.nextLine());
        if(WhichVac == 1){
            WhichVac = FSTSTATUS_INDEX;
        }if(WhichVac == 2){
            WhichVac = SCNDSTATUS_INDEX;
        }

        System.out.println("Set Vaccine Status (Pending/Appointment made/Done): ");
        String status = input.nextLine();
        
        csv.setUserData(ID,status,WhichVac);
    }

    
    public Boolean checkCapacityDay(String Date) {                          // to check capacity reached or not 
        int maxCapacity = CapacityPerHour * 10;                    // calculate max capacity through whole day (From 8am - 6pm, total 10 hours)
        int CurrentCapaicty = csv.ComparenCountField(FSTVACDATE_INDEX, Date) + csv.ComparenCountField(SCNDVACDATE_INDEX, Date);
        if(CurrentCapaicty < maxCapacity){
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkCapacityHour(String Date) {                          // to check capacity reached or not 
        int maxCapacity = CapacityPerHour;                    // calculate max capacity per hour
        int CurrentCapaicty = csv.ComparenCountField(FSTVACDATE_INDEX, Date) + csv.ComparenCountField(SCNDVACDATE_INDEX, Date);
        if(CurrentCapaicty < maxCapacity){
            return true;
        }else{
            return false;
        }
    }

    public static void countVaccinationTaken(ArrayList<String> list) {           // count the appointment date 
        Map<String,Integer> table = new HashMap<String, Integer>();
        for (String i : list) {
            Integer j = table.get(i);
            table.put(i, (j == null) ? 1 : j + 1);
        }
        for (Map.Entry<String, Integer> val : table.entrySet()) {
            System.out.println( val.getKey() + " ==> " + val.getValue());
        }
    }

    public void viewTotalVaccination() {                    // calculate the Total Vaccination
        System.out.println("\tTotal Vaccination taken: "+ (csv.ComparenCountField(FSTSTATUS_INDEX, "Done") + csv.ComparenCountField(SCNDSTATUS_INDEX, "Done")));
        System.out.println("\tTotal Vaccination by day:");
        System.out.println();
        countVaccinationTaken(dateList);
    }

}










