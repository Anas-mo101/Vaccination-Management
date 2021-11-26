import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/**
 * Provide functions that could only be done at Vaccination Center level in the form of JavaFX.
 */
public class MainMenuVaccinationCenter{
    Csvreader csv = new Csvreader();
    List<String> userInfo = csv.getUserInfo();
    private final int ID_INDEX = 0;
    private final int PASSWORD_INDEX = 1;
    private final int USERTYPE_INDEX = 2;
    private final int USERNAME_INDEX = 3;
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    private final int FSTVACDATE_INDEX = 6;
    private final int SCNDVACDATE_INDEX = 7;
    private final int PHONE_INDEX = 8;
    private final int VCASSINGED_INDEX = 9;
    private final int CAPACITY_INDEX = 10;
    private final int AGE_INDEX = 11;
    private final int TOTALVACAVAILABLE_INDEX = 12;
    private int maxCapacity;                                    // assign from userData array
    private int totalVac_Available;
    private String[] userData;
    private final TableView<Record> tableView = new TableView<>();
    private final ObservableList<Record> dataList = FXCollections.observableArrayList();
    ArrayList<String> dateList_registered = new ArrayList<String>();
    ArrayList<String> dateList_taken = new ArrayList<String>();

    Stage mainStage = new Stage();
   
    /**
     * An overloaded constructor for MainMenuVaccinationCenter class by passing the user's data to it.
     * @param data The user's data that logged in
     */
    MainMenuVaccinationCenter(String[] data) {
        
        userData = data;       // <-- Saves user data into an array to used later
        maxCapacity = Integer.parseInt(userData[CAPACITY_INDEX]);
        totalVac_Available = Integer.parseInt(userData[TOTALVACAVAILABLE_INDEX]);

        mainStage.setTitle(userData[USERNAME_INDEX] + " Menu");
        Text menuTitle = new Text("Welcome back, " + userData[USERNAME_INDEX]);
        menuTitle.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        menuTitle.setStroke(Color.RED);

        Button buttonRecipientList = new Button();
        buttonRecipientList.setText("Print Recipient List for " + userData[USERNAME_INDEX]);
        buttonRecipientList.setOnAction(e->{
            printRecipientsList();
        });
        buttonRecipientList.setMinWidth(250);
        
        Button buttonSetAD= new Button();
        buttonSetAD.setText("Set Appointment Date");
        buttonSetAD.setOnAction(e->{
            setAppointmentDate();
        });
        buttonSetAD.setMinWidth(250);

        Button buttonSetAD_Many = new Button();
        buttonSetAD_Many.setText("Set Appointment Date for Many");
        buttonSetAD_Many.setOnAction(e->{
            setAppointmentDate_Many();
        });
        buttonSetAD_Many.setMinWidth(250);

        Button buttonSetVCStatus = new Button();
        buttonSetVCStatus.setText("Set Vaccination Status");
        buttonSetVCStatus.setOnAction(e->{
            setVaccineStatus();
        });
        buttonSetVCStatus.setMinWidth(250);

        Button buttonViewVCStatic = new Button();
        buttonViewVCStatic.setText("View " + userData[USERNAME_INDEX] + " Static");
        buttonViewVCStatic.setOnAction(e->{
            viewVaccinationCenterStatic();
        });
        buttonViewVCStatic.setMinWidth(250);

        Button buttonHallSimulator = new Button();
        buttonHallSimulator.setText(userData[USERNAME_INDEX] + " Hall Simulator");
        buttonHallSimulator.setOnAction(e->{
            HallSimulator hall = new HallSimulator(userData[USERNAME_INDEX]);
        });
        buttonHallSimulator.setMinWidth(250);

        Button buttonExit = new Button();
        buttonExit.setText("Exit");
        buttonExit.setOnAction(e->{
            mainStage.close();
        });
        buttonExit.setMinWidth(175);

        Label clock_lb = new Label();         // create a live clock
        Thread timerThread = new Thread(() -> {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while (true) {
            try {
                Thread.sleep(1000); //1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final String time = simpleDateFormat.format(new Date());
            Platform.runLater(() -> {
                clock_lb.setText(time);
            });
        }
        });   
        timerThread.start(); //start the thread and its ok

        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(90, 5, 5, 5));
        vBoxMenu.getChildren().addAll(menuTitle, clock_lb, buttonRecipientList, buttonSetAD, buttonSetAD_Many, 
                                      buttonSetVCStatus, buttonViewVCStatic, buttonHallSimulator, buttonExit);
        Scene scene = new Scene (vBoxMenu,620,610);
        scene.getStylesheets().add("style.css");
        mainStage.setResizable(false);
        mainStage.initModality(Modality.APPLICATION_MODAL);
        mainStage.setScene(scene);
        mainStage.show();
    }

    //////////////////////////Print Recipient List/////////////////////////////////////////////////////////////////////////////
    /**
     * A class that contains all the attributes that needed in the table of recipient list.
     */
    public class Record {
        private SimpleStringProperty f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11;

        public String getF1() {
            return f1.get();
        }

        public String getF2() {
            return f2.get();
        }

        public String getF3() {
            return f3.get();
        }

        public String getF4() {
            return f4.get();
        }

        public String getF5() {
            return f5.get();
        }

        public String getF6() {
            return f6.get();
        }

        public String getF7() {
            return f7.get();
        }

        public String getF8() {
            return f8.get();
        }

        public String getF9() {
            return f9.get();
        }

        public String getF10() {
            return f10.get();
        }

        public String getF11() {
            return f11.get();
        }

        Record(String f1, String f2,String f3,String f4,String f5,String f6,
                String f7,String f8,String f9,String f10,String f11) {
                    this.f1 = new SimpleStringProperty(f1);
                    this.f2 = new SimpleStringProperty(f2);
                    this.f3 = new SimpleStringProperty(f3);
                    this.f4 = new SimpleStringProperty(f4);
                    this.f5 = new SimpleStringProperty(f5);
                    this.f6 = new SimpleStringProperty(f6);
                    this.f7 = new SimpleStringProperty(f7);
                    this.f8 = new SimpleStringProperty(f8);
                    this.f9 = new SimpleStringProperty(f9);
                    this.f10 = new SimpleStringProperty(f10);
                    this.f11 = new SimpleStringProperty(f11);
        }

    }
    
    /**
     * Create a window to show the table view of recipient list.
     */
    public void printRecipientsList() {

        Stage stage = new Stage();
        stage.setTitle("Recipient List for " + userData[USERNAME_INDEX]);

        Group root = new Group();

        TableColumn<Record, String> idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("f1"));;

        TableColumn<Record, String> passCol = new TableColumn("Password");
        passCol.setCellValueFactory(new PropertyValueFactory<>("f2"));

        TableColumn<Record, String> userTypeCol = new TableColumn("User type");
        userTypeCol.setCellValueFactory(new PropertyValueFactory<>("f3"));

        TableColumn<Record, String> userNameCol = new TableColumn("Username");
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("f4"));

        TableColumn<Record, String> fstVacStatusCol = new TableColumn("1st Vaccination Status");
        fstVacStatusCol.setCellValueFactory(new PropertyValueFactory<>("f5"));

        TableColumn<Record, String> scdVacStatusCol = new TableColumn("2nd Vaccination Status");
        scdVacStatusCol.setCellValueFactory(new PropertyValueFactory<>("f6"));

        TableColumn<Record, String> fstVacDateCol = new TableColumn("1st Vaccination Date");
        fstVacDateCol.setCellValueFactory(new PropertyValueFactory<>("f7"));

        TableColumn<Record, String> scdVacDateCol = new TableColumn("2nd Vaccination Date");
        scdVacDateCol.setCellValueFactory(new PropertyValueFactory<>("f8"));

        TableColumn<Record, String> phoneCol = new TableColumn("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("f9"));

        TableColumn<Record, String> vcAssignedCol = new TableColumn("VC Assigned");
        vcAssignedCol.setCellValueFactory(new PropertyValueFactory<>("f10"));

        TableColumn<Record, String> ageCol = new TableColumn("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("f11"));

        tableView.setItems(dataList);
        tableView.getColumns().addAll(idCol,passCol,userTypeCol,userNameCol,fstVacStatusCol,scdVacStatusCol,
                                   fstVacDateCol,scdVacDateCol,phoneCol,vcAssignedCol,ageCol);

        VBox vBox = new VBox();
        readCSV();
        vBox.setSpacing(20);
        vBox.getChildren().add(tableView);
        root.getChildren().add(vBox);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 881, 400));
        stage.showAndWait();             
        
    }

    /**
     * Read User.csv and add the recipient's data into the Observable list 
     * which the recipient's vaccination center that assigned to him/her 
     * is same as the user's name that logged in.
     */
    private void readCSV() {
 
        String currentPath = System.getProperty("user.dir");
        String FieldDelimiter = ",";
 
        BufferedReader br;
 
        try {
            br = new BufferedReader(new FileReader(currentPath + "/users.csv"));
 
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                if(fields[VCASSINGED_INDEX].equals(userData[USERNAME_INDEX])) {
                    Record record = new Record(fields[ID_INDEX], fields[PASSWORD_INDEX], fields[USERTYPE_INDEX],
                        fields[USERNAME_INDEX], fields[FSTSTATUS_INDEX], fields[SCNDSTATUS_INDEX],fields[FSTVACDATE_INDEX],fields[SCNDVACDATE_INDEX],
                        fields[PHONE_INDEX],fields[VCASSINGED_INDEX],fields[AGE_INDEX]);
                    dataList.add(record);
                }
            }
 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainMenuVaccinationCenter.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainMenuVaccinationCenter.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
 
    }

    //////////////////////////Set Appointment Date/////////////////////////////////////////////////////////////////////////////
    /**
     * Create a window to let the user to set an appointment date for a recipient 
     * that belongs to the user by submitting the required details 
     * in the text field that provided.
     */
    public void setAppointmentDate() {
        
        Stage stage = new Stage();
        stage.setTitle("Set Appointment Date");
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
            if(setAppointmentDate(tfID.getText(), tfDate.getText(), tfTime.getText()))
                stage.hide();  
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
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(gridPane, 265, 235);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.showAndWait();
    
    }

    /**
     * Set an appointment date for a recipient by passing the parameters and 
     * it will return true is the appointment date is set successfully.
     * @param ID Recipient's ID
     * @param Date Recipient's appointment date
     * @param Time Recipient's appointment time
     * @return boolean
     */
    public boolean setAppointmentDate(String ID, String Date, String Time) {                 // set the appoinment date for recipient
        if(ID.equals("")) {
            apearWindow.display("Notification", "Please enter an ID!!");
            return false;
        }                                       
        while(csv.GetUserDataByID(ID, VCASSINGED_INDEX).equals(userData[USERNAME_INDEX])){                      // Checks if Recipient is assigned to current vaccination center 
                if(!timeChecking(Time) || !isDate(Date)){                   // check appointment time is valid or not
                    return false;
                }else {
                    if(checkCapacityDay(Date) || checkTotalVacAvailable()){
                        csv.setUserData(ID,Date + "/" + Time,whichVac(ID));                       //set date & time
                        csv.setUserData(ID,"AppointmentMade",whichStatus(ID));                     //automatically set the vaccination status                                                                // add the appointment date that made successfully to array list
                        apearWindow.display("Notification", "Appointment made Successfully!!"); 
                        totalVac_Available--;
                        csv.setUserData(userData[ID_INDEX], String.valueOf(totalVac_Available), TOTALVACAVAILABLE_INDEX);
                        break;
                    }else{
                        apearWindow.display("Notification", "Max Capacity Reached!!");
                        return false;
                    }
                }
        }
        if(!csv.GetUserDataByID(ID, VCASSINGED_INDEX).equals(userData[USERNAME_INDEX])){
            apearWindow.display("Notification", "This recipient is not assgined to this Vaccination Center!! Please try again!!");
            return false;
        } 
        return true;     
    }

    /**
     * Check the appointment time is valid or not, which means that if the appointment time 
     * given is not in between the Vaccination Center's operating hours then it will return false.
     * @param time Recipient's appointment time
     * @return boolean
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
            apearWindow.display("Notification", "Invalid Input for time!!");
            TargetInZone = false;
        }
        return TargetInZone;
    }

    /**
     * Check the appointment date is valid or not, which means that if the appointment time given
     * is not in a correct format then it will return false. 
     * @param dateString Recipient's appointment date
     * @return boolean
     */
    public Boolean isDate(String dateString) {                       // check the input is valid or not
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString) != null;
        } catch (ParseException e) {
            apearWindow.display("Notification", "Invalid Input for date!!");
            return false;
        }
    }

    /**
     * Check the capacity per day of the Vaccination Center is reached or not,
     * if yes then it will return false else it will return true.
     * @param Date Recipient's appointment date
     * @return boolean
     */
    public Boolean checkCapacityDay(String Date) {                          // to check capacity reached or not 
                                                                            // calculate max capacity through whole day (From 8am - 6pm, total 10 hours)
        int CurrentCapaicty = csv.ComparenCountField(FSTVACDATE_INDEX, Date) + csv.ComparenCountField(SCNDVACDATE_INDEX, Date);
        if(CurrentCapaicty < maxCapacity){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Check the number of vaccine of the Vaccination center is still available or not, 
     * if not then it will return false.
     * @return boolean
     */
    public Boolean checkTotalVacAvailable() {          // to check there is vaccine available or not
        if(totalVac_Available <= 0)
            return false;
        else
            return true;
    }

    /**
     * Check which dose appointment date the program should set for the recipient.
     * @param ID Recipient's ID
     * @return integer
     */
    public int whichVac(String ID) {                                       // To check which Vac the recipients is taking (1st vac or 2nd vac)
        if(csv.GetUserDataByID(ID, FSTVACDATE_INDEX).equals("none"))        
            return FSTVACDATE_INDEX;
        else 
            return SCNDVACDATE_INDEX;        
    }

    /**
     * Check which dose appointment status the program should set for the recipient.
     * @param ID Recipient's ID
     * @return integer
     */
    public int whichStatus(String ID) {                                   
        if(csv.GetUserDataByID(ID, FSTSTATUS_INDEX).equals("Pending"))        
            return FSTSTATUS_INDEX;
        else 
            return SCNDSTATUS_INDEX;        
    }

    //////////////////////////Set Appointment Date for Many///////////////////////////////////////////////////////////////////
    /**
     * Create a window to let the user to set an appointment date for many recipients  
     * that belongs to the user by submitting the required details at one time
     * in the text field that provided.
     */
    public void setAppointmentDate_Many() {
        
        Stage stage = new Stage();
        stage.setTitle("Set Appointment Date Many");
        Label lblID_Start = new Label("Enter Recipient ID from: ");
        Label lblID_End = new Label("Enter Recipient ID to: ");
        Label lblDate = new Label("Enter Appointment Date(YYYY-MM-DD): ");
        Label lblTime = new Label("Enter Appointment Time (08:00-18:00): ");
        TextField tfID_Start = new TextField();
        TextField tfID_End = new TextField();
        TextField tfDate = new TextField();
        TextField tfTime = new TextField();
        Button btnSubmit = new Button("Submit");

        btnSubmit.setOnAction(action -> {
            System.out.println(tfID_Start.getText());
            System.out.println(tfID_End.getText());
            System.out.println(tfDate.getText());
            System.out.println(tfTime.getText());
            if(setAppointmentDate_Many(tfID_Start.getText(), tfID_End.getText(), tfDate.getText(), tfTime.getText()))
                stage.hide();  
        });

        GridPane gridPane = new GridPane();
        gridPane.add(lblID_Start, 0, 1);
        gridPane.add(tfID_Start, 0, 2);
        gridPane.add(lblID_End, 0, 3);
        gridPane.add(tfID_End, 0, 4);
        gridPane.add(lblDate, 0, 5);
        gridPane.add(tfDate, 0, 6);
        gridPane.add(lblTime, 0, 7);
        gridPane.add(tfTime, 0, 8);
        gridPane.add(btnSubmit, 0, 9);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(gridPane, 295, 275);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.showAndWait();
    
    }

    /**
     * Set an appointment date for many recipients by passing the parameters and 
     * it will return true is the appointment date is set successfully.
     * @param ID_start Recipient's ID that the user want to set from
     * @param ID_end Recipient's ID that the user want to set to
     * @param Date Recipient's appointment date
     * @param Time Recipient's appointmnet time
     * @return boolean
     */
    public boolean setAppointmentDate_Many(String ID_start, String ID_end, String Date, String Time) {                 // set the appoinment date for recipient
        if(ID_start.equals("") || ID_end.equals("")) {
            apearWindow.display("Notification", "Please enter ID!!");
            return false;
        }            
        String current_ID = ID_start;   
        while(!current_ID.equals(Integer.toString(Integer.parseInt(ID_end) + 1))) 
        {       
            while(csv.GetUserDataByID(current_ID, VCASSINGED_INDEX).equals(userData[USERNAME_INDEX])) {                      // Checks if Recipient is assigned to current vaccination center 
                    if(!timeChecking(Time) || !isDate(Date)){                   // check appointment time is valid or not
                        return false;
                    }else {
                        if(checkCapacityDay(Date) || checkTotalVacAvailable()){
                            csv.setUserData(current_ID,Date + "/" + Time,whichVac(current_ID));                       //set date & time
                            csv.setUserData(current_ID,"AppointmentMade",whichStatus(current_ID));                     //automatically set the vaccination status                                       // add the appointment date that made successfully to array list 
                            totalVac_Available--;
                            csv.setUserData(userData[ID_INDEX], String.valueOf(totalVac_Available) , TOTALVACAVAILABLE_INDEX);
                            break;
                        }else{
                            apearWindow.display("Notification", "(" + current_ID + ")" + "Max Capacity Reached!!");
                            return false;
                        }
                    }
            }
            if(!csv.GetUserDataByID(current_ID, VCASSINGED_INDEX).equals(userData[USERNAME_INDEX])){
                apearWindow.display("Notification", "This recipient " + "("+ current_ID +")"+ " is not assgined to this Vaccination Center!! Please try again!!");
                return false;
            }
            current_ID = Integer.toString(Integer.parseInt(current_ID) + 1);
        }
        apearWindow.display("Notification", "All Appointment made Successfully!!");
        return true;
    }

    //////////////////////////Set Vaccination Status/////////////////////////////////////////////////////////////////////////
    /**
     * Create a window to let the user to set the Vaccination status for a recipient
     * that belongs to the user by submitting the required details in the text field that provided.
     */
    public void setVaccineStatus() {
        
        Stage stage = new Stage();
        stage.setTitle("Set Vaccination Sattus");
        Label lblID = new Label("Enter Recipient ID: ");
        Label lblStatus = new Label("Set Vaccine Status (AppointmentMade/Done): ");
        TextField tfID = new TextField();
        TextField tfStatus = new TextField();
        Button btnSubmit = new Button("Submit");

        btnSubmit.setOnAction(action -> {
            System.out.println(tfID.getText());
            System.out.println(tfStatus.getText());
            if(setVaccineStatus(tfID.getText(), tfStatus.getText()))
                stage.hide();    
        });

        GridPane gridPane = new GridPane();
        gridPane.add(lblID, 0, 1);
        gridPane.add(tfID, 0, 2);
        gridPane.add(lblStatus, 0, 3);
        gridPane.add(tfStatus, 0, 4);
        gridPane.add(btnSubmit, 0, 5);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(gridPane, 275, 185);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.showAndWait();
    
    }

    /**
     * Set an vaccination status for a recipient by passing the parameters and 
     * it will return true is the vaccination status is set successfully.
     * @param ID Recipient's ID
     * @param Status Recipient's vaccination status
     * @return boolean
     */
    public boolean setVaccineStatus(String ID, String Status) {           // to change vaccination status
        if(ID.equals("")) {
            apearWindow.display("Notification", "Please enter an ID!!");
            return false;
        }
        while(csv.GetUserDataByID(ID, VCASSINGED_INDEX).equals(userData[USERNAME_INDEX])) {      
            int WhichVac = 0;
            String Dose;
            if(csv.GetUserDataByID(ID, SCNDSTATUS_INDEX).equals("Pending")){        
                WhichVac = FSTSTATUS_INDEX;
                Dose = "1st dose";
            }
            else {
                WhichVac = SCNDSTATUS_INDEX;
                Dose = "2nd dose";
            }

            if(Status.equals("AppointmentMade") || Status.equals("Done")) {
                csv.setUserData(ID,Status,WhichVac);
                apearWindow.display("Notification", "Vaccine Status for "+ Dose +" is set Successfully!!");
                break; 
            }else {
                apearWindow.display("Notification", "Invalid input for Status!! Please try again!!");
                return false;
            }
        }
        if(!csv.GetUserDataByID(ID, VCASSINGED_INDEX).equals(userData[USERNAME_INDEX])){
            apearWindow.display("Notification", "This recipient is not assgined to this Vaccination Center!! Please try again!!");
            return false;
        }
        return true;
    }

    //////////////////////////View Vaccination Center Static/////////////////////////////////////////////////////////////////
    /**
     * Create a window to let the user to view the Static of the Vaccination Center by showing a stacked barchart.
     */
    public void viewVaccinationCenterStatic() {
        
        Stage stage = new Stage();
        stage.setTitle(userData[USERNAME_INDEX] + " Static");
        Label totalVacTaken = new Label("\tTotal Vaccination taken at  "+ userData[USERNAME_INDEX] +": " 
                                + (csv.ComparenCountFieldByVC(FSTSTATUS_INDEX, "Done", userData[USERNAME_INDEX])
                                + csv.ComparenCountFieldByVC(SCNDSTATUS_INDEX, "Done", userData[USERNAME_INDEX])));
        Label totalVacAvailable= new Label("\tTotal Vaccine Available at VCSelangor: " + totalVac_Available);
        
        countDate_registered();
        countDate_taken();

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");
        xAxis.getCategories().addAll("Number of Vaccination that registered (by day)",("Total Vaccination taken at  "+ userData[USERNAME_INDEX]));
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Num of people");

        StackedBarChart stackedBarChart = new StackedBarChart(xAxis, yAxis); 
            
        XYChart.Series dataSeries_registered = new XYChart.Series();    // static for Vaccination registered by day
        dataSeries_registered.setName("Number of Vaccination that registered "+ userData[USERNAME_INDEX] +" (by day)");
        addDate(dateList_registered, dataSeries_registered);
        stackedBarChart.getData().add(dataSeries_registered);

        XYChart.Series dataSeries_taken = new XYChart.Series();     // static for Total Vaccination taken by day         
        dataSeries_taken.setName("Total Vaccination taken at  "+ userData[USERNAME_INDEX] + " (by day)");
        addDate(dateList_taken, dataSeries_taken);
        stackedBarChart.getData().add(dataSeries_taken);

        VBox vBoxMenu = new VBox(stackedBarChart);
        vBoxMenu.setPrefWidth(500);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(10, 5, 5, 5));
        vBoxMenu.getChildren().addAll(totalVacTaken,totalVacAvailable);

        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene (vBoxMenu,450,575);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.showAndWait();
    
    }

    /**
     * Adding the date from an array list into a XYChart
     * @param list Array list that stored the date
     * @param dataSeries XYChart that need to get the data
     */
    public void addDate(ArrayList<String> list, XYChart.Series dataSeries) {           // add date from array list into XYChart
        Map<String,Integer> table = new HashMap<String, Integer>();
        for (String i : list) {
            Integer j = table.get(i);
            table.put(i, (j == null) ? 1 : j + 1);
        }
        for (Map.Entry<String, Integer> val : table.entrySet()) {
            dataSeries.getData().add(new XYChart.Data(val.getKey() , val.getValue()));
        }
    }

    /**
     * Count the date for vaccination registered (by day).
     */
    public void countDate_registered() {                                               // count date for Vaccination registered by day
        userInfo = csv.getUserInfo(); // to update list everytime function is called
        dateList_registered.clear();
        for (int i = 2; i < userInfo.size(); i++) {
            String[] items = userInfo.get(i).split(",");
            String fstVacDate = items[FSTVACDATE_INDEX];
            String scdVacDate = items[SCNDVACDATE_INDEX];

            if (!fstVacDate.equals("none") && items[VCASSINGED_INDEX].equals(userData[USERNAME_INDEX])) { 
                dateList_registered.add(fstVacDate.substring(0, 10));
            }
            if (!scdVacDate.equals("none") && items[VCASSINGED_INDEX].equals(userData[USERNAME_INDEX])) { 
                dateList_registered.add(scdVacDate.substring(0, 10));
            }
        }
    }

    /**
     * Count the date for total vaccination taken (by day).
     */
    public void countDate_taken() {                                                     // count date for Total Vaccination taken by day   
        userInfo = csv.getUserInfo(); // to update list everytime function is called
        dateList_taken.clear();
        for (int i = 2; i < userInfo.size(); i++) {
            String[] items = userInfo.get(i).split(",");
            String fstVacStatus = items[FSTSTATUS_INDEX];
            String fstVacDate = items[FSTVACDATE_INDEX];
            String scdVacStatus = items[SCNDSTATUS_INDEX];
            String scdVacDate = items[SCNDVACDATE_INDEX];

            if (fstVacStatus.equals("Done") && items[VCASSINGED_INDEX].equals(userData[USERNAME_INDEX])) { 
                dateList_taken.add(fstVacDate.substring(0, 10));
            }
            if (scdVacStatus.equals("Done") && items[VCASSINGED_INDEX].equals(userData[USERNAME_INDEX])) { 
                dateList_taken.add(scdVacDate.substring(0, 10));
            }
        }
    }

}
