import javafx.application.Application;
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
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
import java.util.Queue;
import java.util.LinkedList;


public class HallSimulator extends Application {
    Queue<Customer> queue = new LinkedList<Customer>();
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

    TableView<Customer> mainQueueTable = new TableView<Customer>();
    TableView<Customer> aboveSixtyQueueTable = new TableView<Customer>();
    TableView vaccineTable = new TableView();
    TableView<Customer> vaccinatedTable = new TableView<Customer>();

    @Override
    public void start(Stage stage) {
        setElements();

        setButton.setOnAction(action -> {
            setDate();
            nextButton.setDisable(false);
            setButton.setDisable(true);
        });

        nextButton.setOnAction(action -> {next();});

        Group root = new Group();
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
        stage.setResizable(false);
        stage.setTitle ("Vaccination Hall Simulator");
        stage.setScene(scene);
        stage.show();
    }

    public void next(){

    }

    public void setDate(){
        LocalDate setDate = datePicker.getValue();  // saves date value from date picker
        queue = csv.getQueue(setDate.toString(), "VCKL"); // gets recips and puts in queue
        System.out.println(queue.toString());
        for(int i=0;i<=queue.size();i++){
            mainQueueTable.getItems().add(queue.poll());
        }
    }


    public void setElements(){
        TableColumn<Customer, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("Username"));

        TableColumn<Customer, String> age = new TableColumn<>("Age");
        age.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Customer, Integer> vac = new TableColumn<>("Vaccine No.");
        vac.setCellValueFactory(new PropertyValueFactory<>("vaccineBatchNo"));

        mainQueueTable.getColumns().add(name);
        mainQueueTable.getColumns().add(age);


        // aboveSixtyQueueTable.getColumns().addAll(name, age);
        // vaccineTable.getColumns().addAll(vac);
        // vaccinatedTable.getColumns().addAll(name,age,vac);
 
        
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
        vac.setPrefWidth(144);
 
        mainQueueTable.setTranslateX(10);
        mainQueueTable.setTranslateY(20);

        aboveSixtyQueueTable.setTranslateX(310);
        aboveSixtyQueueTable.setTranslateY(20);

        vaccineTable.setTranslateX(610);
        vaccineTable.setTranslateY(20);

        vaccinatedTable.setTranslateX(765);
        vaccinatedTable.setTranslateY(20);
    }
    public static void main(String[] args){
        launch(args);
    }
}
