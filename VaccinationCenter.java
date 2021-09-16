import java.util.List;

public class VaccinationCenter extends User {
    private int capacityOfVCPerHour;
    private int capacityOfVCPerDay;
    List<String> userInfo = UsersData.getUserInfo();

    VaccinationCenter() {}

    VaccinationCenter(String ID, String Username, String Password, int capacityOfVCPerHour, int capacityOfVCPerDay) {
        super(ID, Password);
        this.capacityOfVCPerHour = capacityOfVCPerHour;
        this.capacityOfVCPerDay = capacityOfVCPerDay;
        //userInfo.add(new user(ID, Username, Password, capacityOfVCPerHour, capacityOfVCPerDay));           
    }


    public void PrintRecipientList() {
        for( int i = 4; i < userInfo.size() ; i++) {
            
           System.out.println(userInfo.get(i));

        }
    }

    public void set1stAppointmentDate(String Username, String  date) {
        for (int i = 0; i<userInfo.size(); i++) {
            String[] items = userInfo.get(i).split(",");
            String name = items[3];
            if(Username.equals(name)){
                items[6] = date;
                items[4] = "Appointment Made";
            }
        }
    }
    
    public void set2ndAppointmentDate(String Username, String  date) {
        for (int i = 0; i<userInfo.size(); i++) {
            String[] items = userInfo.get(i).split(",");
            String name = items[3];
            if(Username.equals(name)){
                items[7] = date;
                items[4] = "Done";
                items[5] = "Appointment Made";
            }
        }
    }










}








