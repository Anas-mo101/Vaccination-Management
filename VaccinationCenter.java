import java.util.List;

public class VaccinationCenter extends User {
    private int capacityOfVCPerHour = 10;
    private int capacityOfVCPerHourNow = 0;
    private int capacityOfVCPerDay = 100;
    private int capacityOfVCPerDayNow = 0;
    List<String> userInfo = UsersData.getUserInfo();

    VaccinationCenter() {}

    VaccinationCenter(String ID, String Username, String Password, int capacityOfVCPerHour, int capacityOfVCPerDay) {
        super(ID, Password);
        this.capacityOfVCPerHour = capacityOfVCPerHour;
        this.capacityOfVCPerDay = capacityOfVCPerDay;
        //userInfo.add(new user(ID, Username, Password, capacityOfVCPerHour, capacityOfVCPerDay));           
    }

    public void PrintRecipientList() {
        for(int i = 4; i < userInfo.size(); i++) {
           System.out.println(userInfo.get(i));
        }
    }

    public void set1stAppointmentDate(String Username, String  date, String Location) {      // need to add "vaccination location" into the csv file
        for (int i = 0; i<userInfo.size(); i++) {                                            // don't have the save file function
            String[] items = userInfo.get(i).split(",");                    
            String name = items[3];
            if(checkCapacity(Location)){
                if(Username.equals(name)){
                    if(capacityOfVCPerDayNow < capacityOfVCPerDay){
                    items[7] = date;
                    items[5] = "Appointment Made";
                    capacityOfVCPerHourNow = capacityOfVCPerHourNow + 1;
                    capacityOfVCPerDayNow  = capacityOfVCPerDayNow  + 1;
                    System.out.println("An appointment has been made successfuly!!!");
                    }
                    else
                        System.out.println("The date that you choose is already full. Please try again.");
                }
                else
                    System.out.println("There is no such person in the list. Please try again.");
            }
            else
                System.out.println("This Vaccination Center has reached the max capacity at this date. Please select another Vaccination Center.");
        }
    }
    
    public void set2ndAppointmentDate(String Username, String  date, String Location) {
        for (int i = 0; i<userInfo.size(); i++) {
            String[] items = userInfo.get(i).split(",");
            String name = items[3];
            if(checkCapacity(Location)){
                if(Username.equals(name)){
                    if(capacityOfVCPerDayNow < capacityOfVCPerDay){
                    items[8] = date;
                    items[5] = "Completed";
                    items[6] = "Appointment Made";
                    capacityOfVCPerHourNow = capacityOfVCPerHourNow + 1;
                    capacityOfVCPerDayNow  = capacityOfVCPerDayNow  + 1;
                    System.out.println("An appointment has been made successfuly!!!");
                    }
                    else
                        System.out.println("The date that you choose is already full. Please try again.");
                }
                else
                    System.out.println("There is no such person in the list. Please try again.");
            }
            else
                System.out.println("This Vaccination Center has reached the max capacity at this date. Please select another Vaccination Center.");
        }
    }

    public void set1stVaccineStatus(String Username, String Status) {
        for (int i = 0; i<userInfo.size(); i++) {
            String[] items = userInfo.get(i).split(",");
            String name = items[3];
            if(Username.equals(name)){
                items[5] = Status;               
                System.out.println("1st appointment status has been updated successfuly!!!");
            }
            else
                System.out.println("There is no such person in the list. Please try again.");
        }
    }

    public void set2ndVaccineStatus(String Username, String Status) {
        for (int i = 0; i<userInfo.size(); i++) {
            String[] items = userInfo.get(i).split(",");
            String name = items[3];
            if(Username.equals(name)){
                items[6] = Status;               
                System.out.println("2nd appointment status has been updated successfuly!!!");
            }
            else
                System.out.println("There is no such person in the list. Please try again.");
        }
    }

    public boolean checkCapacity(String Location) {
        for (int i = 0; i<userInfo.size(); i++) {
            String[] items = userInfo.get(i).split(",");
            String location = items[3];
            String capcityNow = items[11];
            String capacityDay = items[12];
            if(Location.equals(location)){
                if(capcityNow.equals(capacityDay)){               
                return false;
                }
            }
        }
        return true;
    }


}








