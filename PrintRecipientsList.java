import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;

public class PrintRecipientsList extends Application{
    
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

    private final TableView<Record> tableView = new TableView<>();
 
    private final ObservableList<Record> dataList = FXCollections.observableArrayList();
   
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("PrintRecipientsList");

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
        vBox.setSpacing(20);
        vBox.getChildren().add(tableView);
                            
        root.getChildren().add(vBox);
                            
        primaryStage.setScene(new Scene(root, 900, 475));
        primaryStage.show();
        //tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                            
        readCSV();
    }

    private void readCSV() {
 
        String currentPath = System.getProperty("user.dir");
        String FieldDelimiter = ",";
 
        BufferedReader br;
 
        try {
            br = new BufferedReader(new FileReader(currentPath + "/users.csv"));
 
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                if(fields[9].equals("VCSelangor")) {
                    Record record = new Record(fields[0], fields[1], fields[2],
                        fields[3], fields[4], fields[5],fields[6],fields[7],
                        fields[8],fields[9],fields[11]);
                    dataList.add(record);
                }
            }
 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrintRecipientsList.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrintRecipientsList.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
 
    }
    public static void main(String[] args) {
        Platform.setImplicitExit(false);
        launch(args);
    }

}

