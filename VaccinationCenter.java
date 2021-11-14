import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provide functions that could only be done at Vaccination Center level
 */
public class VaccinationCenter extends User {
    Csvreader csv = new Csvreader();
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    private final int FSTVACDATE_INDEX = 6;
    private final int SCNDVACDATE_INDEX = 7;
    private final int VCASSINGED_INDEX = 9;
    private final int CAPACITY_INDEX = 10;
    private int CapacityPerHour;
    List<String> userInfo = csv.getUserInfo();
    ArrayList<String> dateList = new ArrayList<String>();

    VaccinationCenter(int i){
        super(i);
        CapacityPerHour = Integer.parseInt(csv.GetUserDataByID(getID(),CAPACITY_INDEX));
    }

    /**
     * Print recipient's details which belongs to the same Vaccination Center
     */
    public void PrintRecipientList() {                  // This function is to print the recipient list which all the recipients are from the same VC
        userInfo = csv.getUserInfo();                // to update list everytime function is called
        csv.viewDataByIndex(getUsername(), VCASSINGED_INDEX);
    }

    public int whichVac(String ID) {                                       // To check which Vac the recipients is taking (1st vac or 2nd vac)
        if(csv.GetUserDataByID(ID, FSTVACDATE_INDEX).equals("none"))        
            return FSTVACDATE_INDEX;
        else 
            return SCNDVACDATE_INDEX;        
    }

    public int whichStatus(String ID) {                                   
        if(csv.GetUserDataByID(ID, FSTSTATUS_INDEX).equals("Pending"))        
            return FSTSTATUS_INDEX;
        else 
            return SCNDSTATUS_INDEX;        
    }

    /**
     * Set appointment date for recipient from the same Vaccination Center
     */
    public void setAppointmentDate() {                 // set the appoinment date for recipient
        System.out.println("Enter Recipient ID: ");
        String ID = input.nextLine();                                             
        while(csv.GetUserDataByID(ID, VCASSINGED_INDEX).equals(getUsername())){                      // Checks if Recipient is assigned to current vaccination center 
            System.out.println("Enter Appointment Date(DD/MM/YYYY): ");
            String Date = input.nextLine();
            System.out.println("Enter Appointment Time (08:00-18:00): ");
            String Time = input.nextLine();
            
                if(!timeChecking(Time) || !isDate(Date)){                   // check appointment time is valid or not
                    System.out.println("This appointment time is not valid!! Please try again!!");
                    break;
                }else {
                    if(checkCapacityDay(Date)){
                        csv.setUserData(ID,Date + "-" + Time,whichVac(ID));                       //set date & time
                        csv.setUserData(ID,"AppointmentMade",whichStatus(ID));                     //automatically set the vaccination status
                        dateList.add(Date);                                         // add the appointment date that made successfully to array list
                        System.out.println("Appointment made Successfully!!"); 
                        break;
                    }else{
                        System.out.println("Max Capacity Reached!!");
                        break;
                    }
                }
        }
        if(!csv.GetUserDataByID(ID, VCASSINGED_INDEX).equals(getUsername())){
            System.out.println("This recipient is not assgined to this Vaccination Center!! Please try again!!");
        }      
    }
    
    /**
     * Set appointment date for many recipient from the same Vaccination Center at the same time
     */
    public void setAppointmentDate_Many() {                 // set the appoinment date for recipient
        System.out.println("Enter Recipient ID from: ");
        String ID_start = input.nextLine();
        System.out.println("Enter Recipient ID to: ");
        String ID_end = input.nextLine(); 
        String current_ID = ID_start;   
        
        System.out.println("Enter Appointment Date(DD/MM/YYYY): ");
        String Date = input.nextLine();
        System.out.println("Enter Appointment Time (08:00-18:00): ");
        String Time = input.nextLine();

        while(!current_ID.equals(Integer.toString(Integer.parseInt(ID_end) + 1))) 
        {       
            while(csv.GetUserDataByID(current_ID, VCASSINGED_INDEX).equals(getUsername())) {                      // Checks if Recipient is assigned to current vaccination center 
                    if(!timeChecking(Time) || !isDate(Date)){                   // check appointment time is valid or not
                        System.out.println("This appointment time is not valid!! Please try again!!");
                        break;
                    }else {
                        if(checkCapacityDay(Date)){
                            csv.setUserData(current_ID,Date + "-" + Time,whichVac(current_ID));                       //set date & time
                            csv.setUserData(current_ID,"AppointmentMade",whichStatus(current_ID));                     //automatically set the vaccination status
                            dateList.add(Date);                                         // add the appointment date that made successfully to array list 
                            break;
                        }else{
                            System.out.println("(" + current_ID + ")" + "Max Capacity Reached!!");
                            break;
                        }
                    }
            }
            if(!csv.GetUserDataByID(current_ID, VCASSINGED_INDEX).equals(getUsername())){
                System.out.println("This recipient " + "("+ current_ID +")"+ " is not assgined to this Vaccination Center!! Please try again!!");
            }
            current_ID = Integer.toString(Integer.parseInt(current_ID) + 1);
        }
        System.out.println("All Appointment made Successfully!!");
    }

    /**
     * Set Vaccination Status for recipient from the same Vaccination Center
     */
    public void setVaccineStatus() {           // to change vaccination status
        System.out.println("Enter Recipient ID: ");
        String ID = input.nextLine();
        while(csv.GetUserDataByID(ID, VCASSINGED_INDEX).equals(getUsername())) {

            int WhichVac = 0;
            if(csv.GetUserDataByID(ID, SCNDSTATUS_INDEX).equals("Pending"))        
                WhichVac = FSTSTATUS_INDEX;
            else 
                WhichVac = SCNDSTATUS_INDEX;

            System.out.println("Set Vaccine Status (AppointmentMade/Done): ");
            String status = input.nextLine();
            if(status.equals("AppointmentMade") || status.equals("Done")) {
                csv.setUserData(ID,status,WhichVac);
                System.out.println("Vaccine Status is set Successfully!!"); 
                break; 
            }else {
                System.out.println("Invalid input!! Please try again!!");
                break;
            }
        }
        if(!csv.GetUserDataByID(ID, 9).equals(getUsername())){
            System.out.println("This recipient is not assgined to this Vaccination Center!! Please try again!!");
        }
    }
    
    /**
     * Check there is any slot available throughout the whole day(8am-6pm) for the recipient to take the vaccine
     * @param Date Appointment Date entered by User
     * @return Boolean
     */
    public Boolean checkCapacityDay(String Date) {                          // to check capacity reached or not 
        int maxCapacity = CapacityPerHour * 10;                    // calculate max capacity through whole day (From 8am - 6pm, total 10 hours)
        int CurrentCapaicty = csv.ComparenCountField(FSTVACDATE_INDEX, Date) + csv.ComparenCountField(SCNDVACDATE_INDEX, Date);
        if(CurrentCapaicty < maxCapacity){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Check the time selected is in the Vaccination Center operating hours or not
     * @param time Appointment Time entered by User
     * @return Boolean
     */
    public Boolean timeChecking(String time) {                      // check the time of appointment is in the VC operating hours or not 
        Boolean TargetInZone = true;                                // check the input is valid or not
        try{
            LocalTime TargetTime = LocalTime.parse(time);
            TargetInZone = (
                TargetTime.isAfter(LocalTime.parse("08:00"))
                &&
                TargetTime.isBefore(LocalTime.parse("18:00"))
            );
        } catch (DateTimeParseException e){
            System.out.println("Invalid Input for time!!");
            TargetInZone = false;
        }
        return TargetInZone;
    }

    /**
     * Check the input is a valid date or not
     * @param dateString Appointment Date entered by User
     * @return Boolean
     */
    public Boolean isDate(String dateString) {                       // check the input is valid or not
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(dateString) != null;
        } catch (ParseException e) {
            System.out.println("Invalid Input for date!!");
            return false;
        }
    }

    /**
     * Count the Appointment that made through the program
     * @param list List that contains the date which appointment have been made
     */
    public void countVaccinationRegistered(ArrayList<String> list) {           // count the appointment date 
        Map<String,Integer> table = new HashMap<String, Integer>();
        for (String i : list) {
            Integer j = table.get(i);
            table.put(i, (j == null) ? 1 : j + 1);
        }
        for (Map.Entry<String, Integer> val : table.entrySet()) {
            System.out.println( val.getKey() + " ==> " + val.getValue());
        }
    }

    /**
     * Print the number of Total Vaccination taken at the same Vaccination Center and also
     * the number of appointment date that have been registered
     */
    public void viewTotalVaccination() {                    // calculate the Total Vaccination
        System.out.println("\tTotal Vaccination taken at " + getUsername() + ": "+ 
                            (csv.ComparenCountFieldByVC(FSTSTATUS_INDEX, "Done", getUsername()) + 
                             csv.ComparenCountFieldByVC(SCNDSTATUS_INDEX, "Done", getUsername())));
        //System.out.println("\tTotal Vaccine Available at " + getUsername() + ": ");
        System.out.println("\tNumber of Vaccination that registered (by day):");
        System.out.println();
        countVaccinationRegistered(dateList);
    }

}
