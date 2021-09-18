import java.util.List;

// Remaider:        
// need to add "vaccination location" into the csv file
// don't have the save file function

public class VaccinationCenter extends User {
    
    List<String> userInfo = UsersData.getUserInfo();

    VaccinationCenter() {}

    /*    Not sure this overloaded constructor is needed or not

    VaccinationCenter(String ID, String Username, String Password, int capacityOfVCPerDay) {
        super(ID, Password);
        this.capacityOfVCPerDay = capacityOfVCPerDay;
        //userInfo.add(new user(ID, Username, Password, capacityOfVCPerDay));           
    }
    */

    public void PrintRecipientList() {                  // This function is to print the recipient list
        for(int i = 4; i < userInfo.size(); i++) {
           System.out.println(userInfo.get(i));
        }
    }

    public void set1stAppointmentDate(String Username, String  date, String VCLocation) {      // set the 1st appoinment date for recipient
        for (int i = 4; i<userInfo.size(); i++) {                                            
            String[] items = userInfo.get(i).split(",");                    
            String name = items[3];
            if(getCapacityNow(VCLocation,date) < getCapacity(VCLocation)){       // check the capacity of VC is full or not
                if(Username.equals(name)){
                    items[7] = date;
                    items[5] = "Appointment Made";
                    System.out.println("An appointment has been made successfuly!!!");
                }
            }
            else
                System.out.println("This Vaccination Center has reached the max capacity at this date. Please select another Vaccination Center.");
        }
    }
    
    public void set2ndAppointmentDate(String Username, String  date, String VCLocation) {    // set the 2nd appoinment date for recipient
        for (int i = 4; i<userInfo.size(); i++) {
            String[] items = userInfo.get(i).split(",");
            String name = items[3];
            if(getCapacityNow(VCLocation,date) < getCapacity(VCLocation)){      // check the capacity of VC is full or not
                if(Username.equals(name)){
                    items[8] = date;
                    items[5] = "Completed";
                    items[6] = "Appointment Made";
                    System.out.println("An appointment has been made successfuly!!!");
                }
            }
            else
                System.out.println("This Vaccination Center has reached the max capacity at this date. Please select another Vaccination Center.");
        }
    }

    public void set1stVaccineStatus(String Username, String Status) {           // to change status for 1st vaccination
        for (int i = 4; i<userInfo.size(); i++) {
            String[] items = userInfo.get(i).split(",");
            String name = items[3];
            if(Username.equals(name)){
                items[5] = Status;               
                System.out.println("1st appointment status has been updated successfuly!!!");
            }
        }
    }

    public void set2ndVaccineStatus(String Username, String Status) {           // to change status for 2nd vaccination
        for (int i = 4; i<userInfo.size(); i++) {
            String[] items = userInfo.get(i).split(",");
            String name = items[3];
            if(Username.equals(name)){
                items[6] = Status;               
                System.out.println("2nd appointment status has been updated successfuly!!!");
            }
        }
    }
    
    public int getCapacityNow(String  VCLocation, String date) {                          // to get the capacity of date 
                                                                                          // (not sure this function works or not)
        int CapacityNow = 0;
        for (int i = 4; i<userInfo.size(); i++) {
            String[] items = userInfo.get(i).split(",");
            String F_Location = items[7];
            String F_AppointDate = items [6];
            String S_Location = items[9];
            String S_AppointDate = items [8];
            if(F_Location.equals(VCLocation)) {
                if(F_AppointDate.equals(date))
                    CapacityNow++;
            }else if(S_Location.equals(VCLocation)) {
                if(S_AppointDate.equals(date))
                    CapacityNow++;
            }  
        }
        return CapacityNow;
    }

    public int getCapacity(String VCLocation) {             // Checked, this function worked
        int capacity=0;
        for (int i = 2; i<4; i++) {
            String[] items = userInfo.get(i).split(",");
            String vclocation = items[3];
            if(VCLocation.equals(vclocation)){              // get the capacity of VC from the CSV file
                capacity= Integer.parseInt(items[10]);
                return capacity;
            }
        }
        return capacity;
    }

    
}





