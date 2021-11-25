import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.time.LocalDate;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Class to handle hall simulator and vaccinating recipients 
 */
public class HallSimulator{
    private final int FRSTVACBATCH_INDEX = 13;
    private final int SCNDVACBATCH_INDEX = 14;
    private final int ID_INDEX = 0;
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    Queue<Customer> queue = new LinkedList<Customer>();
    Queue<Customer> elderlyQueue = new LinkedList<Customer>();   
    Queue<Vaccine> vacQueue = new LinkedList<Vaccine>();
    Label noticeLbl = new Label("Hello! Pick date to start");
    Label t1Lbl = new Label("Recipients");
    Label t2Lbl = new Label("Recipient bove 60yr");
    Label t3Lbl = new Label("Vaccine batch no.");
    Label t4Lbl = new Label("Vaccinated Recipients");
    Label d5Lbl = new Label("Choose Date");
    Csvreader csv = new Csvreader();
    DatePicker datePicker = new DatePicker();
    Button setButton = new Button("Set");
    Button nextButton = new Button("Next");
    Button btnTime = new Button("Submit");
    Boolean arrangeQueue = true;
    LocalDate setDate;
    private String vcName;
    int queueSize;
    TableView<Customer> mainQueueTable = new TableView<Customer>();
    TableView<Customer> aboveSixtyQueueTable = new TableView<Customer>();
    TableView<Vaccine> vaccineTable = new TableView<Vaccine>();
    TableView<Customer> vaccinatedTable = new TableView<Customer>();

    Stage stage = new Stage();

    HallSimulator(String vcName) {
        this.vcName = vcName;

        setElements();

        setButton.setOnAction(action -> {
            setDate();
        });

        nextButton.setOnAction(action -> {next();});

        Group root = new Group();
        root.getChildren().add(noticeLbl);
        root.getChildren().add(t1Lbl);
        root.getChildren().add(t2Lbl);
        root.getChildren().add(t3Lbl);
        root.getChildren().add(t4Lbl);
        root.getChildren().add(d5Lbl);
        root.getChildren().add(nextButton);
        root.getChildren().add(setButton);
        root.getChildren().add(datePicker);
        root.getChildren().add(mainQueueTable);
        root.getChildren().add(aboveSixtyQueueTable);
        root.getChildren().add(vaccineTable);
        root.getChildren().add(vaccinatedTable);
        Scene scene = new Scene(root, 1210, 500);
        scene.getStylesheets().add("style.css");
        stage.setResizable(false);
        stage.setTitle (vcName + " Vaccination Hall Simulator");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Arranges then Moves queue one recipient forward
     */
    public void next(){
        
        if(arrangeQueue){
            noticeLbl.setText("Now click next to move queue");
            mainQueueTable.getItems().clear();
            arrangeQueue();
            setButton.setDisable(true);
            arrangeQueue = false;
        }else{
            move();
        }
    }
    

    /**
     * Moves queue one recipient forward
     */
    public void move(){
        Customer tempCusOne = new Customer();
        Customer tempCusTwo = new Customer();
        Vaccine tempVacOne = new Vaccine(true);
        Vaccine tempVacTwo = new Vaccine(true);

        if(!mainQueueTable.getItems().isEmpty()){
            tempCusOne = mainQueueTable.getItems().remove(0);
            if(!vaccineTable.getItems().isEmpty()){
                tempVacOne = vaccineTable.getItems().remove(0);
            }else{
                vaccineTable.setPlaceholder(new Label("No more vaccines"));
            }
            tempCusOne.setvacBatchNo(tempVacOne.getVacBatchNo());
            System.out.println(tempCusOne.getUsername() + ", " + tempCusOne.getAge() + ", " + tempCusOne.getVacBatchNo());

            vaccinatedTable.getItems().add(tempCusOne);
        }else{
            mainQueueTable.setPlaceholder(new Label("No more recipients"));
        }

        if(!aboveSixtyQueueTable.getItems().isEmpty()){
            tempCusTwo = aboveSixtyQueueTable.getItems().remove(0);
            if(!vaccineTable.getItems().isEmpty()){
                tempVacTwo = vaccineTable.getItems().remove(0);
            }else{
                vaccineTable.setPlaceholder(new Label("No more vaccines"));
            }
            tempCusTwo.setvacBatchNo(tempVacTwo.getVacBatchNo());
            System.out.println(tempCusTwo.getUsername() + ", " + tempCusTwo.getAge() + ", " + tempCusTwo.getVacBatchNo());

            vaccinatedTable.getItems().add(tempCusTwo);
        }else{
            aboveSixtyQueueTable.setPlaceholder(new Label("No more recipients"));
        }

        if(aboveSixtyQueueTable.getItems().isEmpty() && mainQueueTable.getItems().isEmpty()){
            noticeLbl.setText("Queue is over, try picking another date");
            nextButton.setDisable(true);
            finialize();
        }
    }

    public void arrangeQueue(){
        queue = csv.getQueue(setDate.toString(),vcName); // gets recips and puts in queue
        if(!queue.isEmpty()){
            int queueSize = queue.size() - 1;
            for(int i=0;i<=queueSize;i++){
                if(Integer.parseInt(queue.peek().getAge()) < 60){
                    mainQueueTable.getItems().add(queue.poll());
                }else{
                    aboveSixtyQueueTable.getItems().add(queue.poll());
                }
            }
        }else{
            mainQueueTable.setPlaceholder(new Label("No recipients at this day"));
        }
    }


    /**
     * Saves Vaccine Batch Number to Recipinet in database
     */
    public void finialize(){
       System.out.println("FINIALZIED");
        for(int i=0;i<queueSize;i++){
            String username = vaccinatedTable.getItems().get(i).getUsername();
            if(csv.GetUserDataByUsername(username, FRSTVACBATCH_INDEX).equals("none")){
                csv.setUserData(csv.GetUserDataByUsername(username, ID_INDEX), vaccinatedTable.getItems().get(i).getVacBatchNo(), FRSTVACBATCH_INDEX);
                csv.setUserData(csv.GetUserDataByUsername(username, ID_INDEX), "Done", FSTSTATUS_INDEX);
            }
            else if(csv.GetUserDataByUsername(username, SCNDVACBATCH_INDEX).equals("none")){
                csv.setUserData(csv.GetUserDataByUsername(username, ID_INDEX), vaccinatedTable.getItems().get(i).getVacBatchNo(), SCNDVACBATCH_INDEX);
                csv.setUserData(csv.GetUserDataByUsername(username, ID_INDEX), "Done", SCNDSTATUS_INDEX);
            }
        }
    }
    
    /**
     * Retreives Recipients from set date and assgined vaccination center
     */
     public void setDate(){
        mainQueueTable.getItems().clear();
        if(datePicker.getValue() != null){
            
            setDate = datePicker.getValue();  // saves date value from date picker
            queue = csv.getQueue(setDate.toString(), vcName); // gets recips and puts in queue
            queueSize = queue.size() - 1;  

            if(!queue.isEmpty()){
                noticeLbl.setText("Now click next to arrange queue");
                nextButton.setDisable(false);
                for(int i=0;i<=queueSize;i++){
                    vacQueue.add(new Vaccine());
                }
                for(int i=0;i<=queueSize;i++){
                    mainQueueTable.getItems().add(queue.poll());
                    vaccineTable.getItems().add(vacQueue.poll());
                }
            }else{
                noticeLbl.setText("Try picking another date");
                mainQueueTable.setPlaceholder(new Label("No recipients at this day"));
            }
        }
    }


    /**
     * Set GUI elements at start
     */
    public void setElements(){
        TableColumn<Customer, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("Username"));

        TableColumn<Customer, String> age = new TableColumn<>("Age");
        age.setCellValueFactory(new PropertyValueFactory<>("age"));

        mainQueueTable.getColumns().add(name);
        mainQueueTable.getColumns().add(age);

        //==================================================

        TableColumn<Customer, String> nameEldery = new TableColumn<>("Name");
        nameEldery.setCellValueFactory(new PropertyValueFactory<>("Username"));

        TableColumn<Customer, String> ageEldery = new TableColumn<>("Age");
        ageEldery.setCellValueFactory(new PropertyValueFactory<>("age"));

        aboveSixtyQueueTable.getColumns().add(nameEldery);
        aboveSixtyQueueTable.getColumns().add(ageEldery);

        //====================================================

        TableColumn<Vaccine, String> vac = new TableColumn<>("Vaccine");
        vac.setCellValueFactory(new PropertyValueFactory<>("VacBatchNo"));
        vaccineTable.getColumns().add(vac);

        //===========================================================

        TableColumn<Customer, String> nameVaccined = new TableColumn<>("Name");
        nameVaccined.setCellValueFactory(new PropertyValueFactory<>("Username"));

        TableColumn<Customer, String> ageVaccined = new TableColumn<>("Age");
        ageVaccined.setCellValueFactory(new PropertyValueFactory<>("Age"));

        TableColumn<Customer, String> vacVaccined = new TableColumn<>("Vaccine");
        vacVaccined.setCellValueFactory(new PropertyValueFactory<>("VacBatchNo"));

        vaccinatedTable.getColumns().add(nameVaccined);
        vaccinatedTable.getColumns().add(ageVaccined);
        vaccinatedTable.getColumns().add(vacVaccined);

        //============================================================================
 
        t1Lbl.setTranslateX(130);
        t1Lbl.setTranslateY(4);

        t2Lbl.setTranslateX(410);
        t2Lbl.setTranslateY(4);

        t3Lbl.setTranslateX(637);
        t3Lbl.setTranslateY(4);

        t4Lbl.setTranslateX(925);
        t4Lbl.setTranslateY(4);

        d5Lbl.setTranslateX(500);
        d5Lbl.setTranslateY(364);

        noticeLbl.setTranslateX(200);
        noticeLbl.setTranslateY(380);

        nextButton.setTranslateX(715);
        nextButton.setTranslateY(380);
        nextButton.setDisable(true);

        setButton.setTranslateX(515);
        setButton.setTranslateY(410);

        datePicker.setTranslateX(450);
        datePicker.setTranslateY(380);

        mainQueueTable.setPrefSize(288, 300);
        aboveSixtyQueueTable.setPrefSize(288, 300);
        vaccineTable.setPrefSize(144, 300);
        vaccinatedTable.setPrefSize(432, 300);

        name.setPrefWidth(144);
        age.setPrefWidth(144);

        nameEldery.setPrefWidth(144);
        ageEldery.setPrefWidth(144);

        vac.setPrefWidth(144);

        nameVaccined.setPrefWidth(144);
        ageVaccined.setPrefWidth(144);
        vacVaccined.setPrefWidth(144);
 
        mainQueueTable.setTranslateX(10);
        mainQueueTable.setTranslateY(20);

        aboveSixtyQueueTable.setTranslateX(310);
        aboveSixtyQueueTable.setTranslateY(20);

        vaccineTable.setTranslateX(610);
        vaccineTable.setTranslateY(20);

        vaccinatedTable.setTranslateX(765);
        vaccinatedTable.setTranslateY(20);
    }
}
