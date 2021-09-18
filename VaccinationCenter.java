import java.util.List;

// Remaider:        
// need to add "vaccination location" into the csv file
// don't have the save file function

public class VaccinationCenter extends User {
    Csvreader csv = new Csvreader();
    private final int CAPACITY_INDEX = 10;
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    private final int FSTVACDATE_INDEX = 6;
    private final int SCNDVACDATE_INDEX = 7;
    private int CapacityPerHour;
    List<String> userInfo = UsersData.getUserInfo();

    VaccinationCenter(String usertype){
        super(usertype);
        CapacityPerHour = Integer.parseInt(csv.GetUserDataByID(getID(),CAPACITY_INDEX));
    }


    public void PrintRecipientList() {                  // This function is to print the recipient list
        for(int i = 4; i < userInfo.size(); i++) {
           System.out.println(userInfo.get(i));
        }
    }

    public void setAppointmentDate() {      // set the 1st appoinment date for recipient
        System.out.println("Enter Recipient ID: ");
        String ID = input.nextLine();

        System.out.println("Enter either first or second vaccination (1/2): ");
        int WhichVac = Integer.parseInt(input.nextLine());
        if(WhichVac == 1){
            WhichVac = FSTVACDATE_INDEX;
        }if(WhichVac == 2){
            WhichVac = SCNDVACDATE_INDEX;
        }
        
        System.out.println("Enter Vaccination Date (DD/MM/YYYY): ");
        String Date = input.nextLine();

        while(true){
            if(checkCapacity(Date)){
                break;
            }else{
                System.out.println("Max Capacity Reached");
            }
        }
        
        csv.setUserData(ID,Date,WhichVac);
    }
    

    public void setVaccineStatus() {           // to change status for 1st vaccination
        System.out.println("Enter Recipient ID: ");
        String ID = input.nextLine();

        System.out.println("Enter either first or second vaccination (1/2): ");
        int WhichVac = Integer.parseInt(input.nextLine());
        if(WhichVac == 1){
            WhichVac = FSTSTATUS_INDEX;
        }if(WhichVac == 2){
            WhichVac = SCNDSTATUS_INDEX;
        }

        System.out.println("Set Vaccine Status (true/false): ");
        String status = input.nextLine();
        
        csv.setUserData(ID,status,WhichVac);
    }

    
    public Boolean checkCapacity(String Date) {                          // to check capacity reached or not 
        int maxCapacity = CapacityPerHour * 24;                    // calculate max capacity through whole day
        int CurrentCapaicty = csv.ComparenCountField(FSTVACDATE_INDEX, Date) + csv.ComparenCountField(SCNDVACDATE_INDEX, Date);
        if(CurrentCapaicty < maxCapacity){
            return true;
        }else{
            return false;
        }
    }


}













