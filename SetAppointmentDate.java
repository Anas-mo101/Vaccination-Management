import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SetAppointmentDate extends Application {
    Csvreader csv = new Csvreader();
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    private final int FSTVACDATE_INDEX = 6;
    private final int SCNDVACDATE_INDEX = 7;
    private final int VCASSINGED_INDEX = 9;
    private final int CAPACITY_INDEX = 10;
    private final int TOTALVACAVAILABLE_INDEX = 12;
    private int maxCapacity = Integer.parseInt(csv.GetUserDataByID(String.valueOf(10002), CAPACITY_INDEX));       // for Vaccination center VCSelangor
    private int totalVacAvailable = 100;
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Set Appointment Date");
        Label lblID = new Label("Enter Recipient ID: ");
        Label lblDate = new Label("Enter Appointment Date(YYYY-MM-DD): ");
        Label lblTime = new Label("Enter Appointment Time (08:00-18:00): ");
        TextField tfID = new TextField();
        TextField tfDate = new TextField();
        TextField tfTime = new TextField();
        Button btnSubmit = new Button("Submit");

        btnSubmit.setOnAction(action -> {
            System.out.println(tfID.getText());
            System.out.println(tfDate.getText());
            System.out.println(tfTime.getText());
            setAppointmentDate(tfID.getText(), tfDate.getText(), tfTime.getText());
            primaryStage.hide();  
        });

        GridPane gridPane = new GridPane();
        gridPane.add(lblID, 0, 1);
        gridPane.add(tfID, 0, 2);
        gridPane.add(lblDate, 0, 3);
        gridPane.add(tfDate, 0, 4);
        gridPane.add(lblTime, 0, 5);
        gridPane.add(tfTime, 0, 6);
        gridPane.add(btnSubmit, 0, 7);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5);
        Scene scene = new Scene(gridPane, 370, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    
    }

    public void setAppointmentDate(String ID, String Date, String Time) {                 // set the appoinment date for recipient
                                               
        while(csv.GetUserDataByID(ID, VCASSINGED_INDEX).equals("VCSelangor")){                      // Checks if Recipient is assigned to current vaccination center 
                if(!timeChecking(Time) || !isDate(Date)){                   // check appointment time is valid or not
                    System.out.println("This appointment time is not valid!! Please try again!!");
                    break;
                }else {
                    if(checkCapacityDay(Date) || checkTotalVacAvailable()){
                        csv.setUserData(ID,Date + "/" + Time,whichVac(ID));                       //set date & time
                        csv.setUserData(ID,"AppointmentMade",whichStatus(ID));                     //automatically set the vaccination status
                        //dateList.add(Date);                                         // add the appointment date that made successfully to array list
                        System.out.println("Appointment made Successfully!!"); 
                        totalVacAvailable--;
                        csv.setUserData(String.valueOf(10002), String.valueOf(totalVacAvailable), TOTALVACAVAILABLE_INDEX);
                        break;
                    }else{
                        System.out.println("Max Capacity Reached!!");
                        break;
                    }
                }
        }
        if(!csv.GetUserDataByID(ID, VCASSINGED_INDEX).equals("VCSelangor")){
            System.out.println("This recipient is not assgined to this Vaccination Center!! Please try again!!");
        }      
    }

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

    public Boolean isDate(String dateString) {                       // check the input is valid or not
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
            return dateFormat.parse(dateString) != null;
        } catch (ParseException e) {
            System.out.println("Invalid Input for date!!");
            return false;
        }
    }

    public Boolean checkCapacityDay(String Date) {                          // to check capacity reached or not 
                                                                            // calculate max capacity through whole day (From 8am - 6pm, total 10 hours)
        int CurrentCapaicty = csv.ComparenCountField(FSTVACDATE_INDEX, Date) + csv.ComparenCountField(SCNDVACDATE_INDEX, Date);
        if(CurrentCapaicty < maxCapacity){
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkTotalVacAvailable() {          // to check there is vaccine available or not
        if(totalVacAvailable <= 0)
            return false;
        else
            return true;
    }

    public int whichVac(String ID) {                                       // To check which Vac the recipients is taking (1st vac or 2nd vac)
        if(csv.GetUserDataByID(ID, FSTVACDATE_INDEX).equals("none"))        
            return FSTVACDATE_INDEX;
        else 
            return SCNDVACDATE_INDEX;        
    }

    /**
     * To check which Vaccination status that the system need to set for the recipient
     * @param ID Recipients' ID
     * @return return which Vaccination status the system need to set for the recipient
     */
    public int whichStatus(String ID) {                                   
        if(csv.GetUserDataByID(ID, FSTSTATUS_INDEX).equals("Pending"))        
            return FSTSTATUS_INDEX;
        else 
            return SCNDSTATUS_INDEX;        
    }

    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
