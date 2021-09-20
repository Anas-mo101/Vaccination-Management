import java.util.List;

public class VaccinationCenter extends User {
    Csvreader csv = new Csvreader();
    private final int CAPACITY_INDEX = 10;
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    private final int FSTVACDATE_INDEX = 6;
    private final int SCNDVACDATE_INDEX = 7;
    private final int VCASSIGNED_INDEX = 9;
    private int CapacityPerHour;
    List<String> userInfo = csv.getUserInfo();

    VaccinationCenter(String usertype){
        super(usertype);
        CapacityPerHour = Integer.parseInt(csv.GetUserDataByID(getID(),CAPACITY_INDEX));
    }


    public void PrintRecipientList() {                  // This function is to print the recipient list
        userInfo = csv.getUserInfo();   // to update list everytime function is called
        for(int i = 4; i < userInfo.size(); i++) {
           System.out.println(userInfo.get(i));
        }
    }

    public void setAppointmentDate() {      // set the appoinment date for recipient
        System.out.println("Enter Recipient ID: ");
        String ID = input.nextLine();

        System.out.println("Enter either first or second vaccination (1/2): ");
        int WhichVac = Integer.parseInt(input.nextLine());
        if(WhichVac == 1){
            WhichVac = FSTVACDATE_INDEX;
        }if(WhichVac == 2){
            WhichVac = SCNDVACDATE_INDEX;
        }
        
        System.out.println("Enter Vaccination Date(DD/MM/YYYY) & Time (08:00-18:00): ");
        String Date = input.nextLine();
        String vcAssigned = getUsername();

        while(true){
            if(checkCapacityDay(Date) && checkCapacityHour(Date)){
                csv.setUserData(ID,Date,WhichVac);                       //set date & time
                csv.setUserData(ID,vcAssigned,VCASSIGNED_INDEX);         //set VC assigned
                break;
            }else{
                System.out.println("Max Capacity Reached");
                break;
            }
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
    
    public void viewTotalVaccination() {                     // calculate the Total Vaccination taken
        String vcAssigned = getUsername();
        System.out.println("\tTotal Vaccination Stats:\n"+ (csv.ComparenCountField(VCASSIGNED_INDEX, vcAssigned)));

    }

}












