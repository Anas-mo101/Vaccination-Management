import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class MainMenuMoh {
    Csvreader csv = new Csvreader();
    private final int ID_INDEX = 0;
    private final int USERTYPE_INDEX = 2;
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    private final int VCASSINGED_INDEX = 9;
    private final TableView<RecipientData> dataTable = new TableView<>();
    private final ArrayList<RecipientData> recipientPosition = new ArrayList<>();

    Stage mainStage = new Stage();

    MainMenuMoh(String[] data) {

        // @Override
        // public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("MOH MENU");

        Button buttonRecipient = new Button();
        buttonRecipient.setText("Add Recipient");
        buttonRecipient.setOnAction(e -> {
            addRecipent();
        });

        Button buttonVC = new Button();
        buttonVC.setText("Add VC");
        buttonVC.setOnAction(e -> {
            addVC();
        });

        Button buttonRecipientList = new Button();
        buttonRecipientList.setText("View Recipient List");
        buttonRecipientList.setOnAction(e -> {
            readCSV();
            viewRecipientList();
        });
        Button buttonStatistic = new Button();
        buttonStatistic.setText("View Vacination Statistic");
        buttonStatistic.setOnAction(e -> {
            vacinationStatistic();
        });

        Button distributeVaccineButton = new Button();
        distributeVaccineButton.setText("Distribute Vaccine");
        distributeVaccineButton.setOnAction(e -> {
            distributeVaccine();
        });

        Button buttonExit = new Button();
        buttonExit.setText("Exit");
        buttonExit.setOnAction(e -> {
            mainStage.close();
        });

        HBox hBoxMenu = new HBox();
        hBoxMenu.setPrefWidth(200);
        hBoxMenu.setAlignment(Pos.TOP_CENTER);
        hBoxMenu.setSpacing(30);
        hBoxMenu.setPadding(new Insets(90, 5, 5, 5));
        hBoxMenu.getChildren().addAll(buttonRecipient, buttonVC, buttonRecipientList, buttonStatistic,
                distributeVaccineButton, buttonExit);

        Scene scene = new Scene(hBoxMenu, 800, 250);
        mainStage.setScene(scene);
        mainStage.show();

    }

    ////////////////////// ADD RECIPIENT //////////////////////////////
    public void addRecipent() {
        Stage stage = new Stage();
        stage.setTitle("ADD USER");

        Label name = new Label("Enter name:  ");
        Label phone = new Label("Enter your phone number: ");
        Label age = new Label("Enter your Age: ");
        Label password = new Label("Enter a password: ");

        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField passField = new TextField();
        TextField ageField = new TextField();

        Button submit = new Button();
        submit.setText("Submit");
        submit.setOnAction(e -> {
            csv.addUser(passField.getText(), "recipient", nameField.getText(), "Pending", "Pending",
                    phoneField.getText(), "none", ageField.getText());
            apearWindow.display("Succesfull", "Succesfully Updated!");
            stage.close();
        });

        HBox insertName = new HBox();
        insertName.setPrefWidth(200);
        insertName.setAlignment(Pos.TOP_CENTER);
        insertName.setSpacing(30);
        insertName.setPadding(new Insets(5, 5, 5, 2));
        insertName.getChildren().addAll(name, nameField);

        HBox insertPhone = new HBox();
        insertPhone.setPrefWidth(200);
        insertPhone.setAlignment(Pos.TOP_CENTER);
        insertPhone.setSpacing(30);
        insertPhone.setPadding(new Insets(5, 5, 5, 60));
        insertPhone.getChildren().addAll(phone, phoneField);

        HBox insertPass = new HBox();
        insertPass.setPrefWidth(200);
        insertPass.setAlignment(Pos.TOP_CENTER);
        insertPass.setSpacing(30);
        insertPass.setPadding(new Insets(5, 5, 5, 15));
        insertPass.getChildren().addAll(password, passField);

        HBox insertAge = new HBox();
        insertAge.setPrefWidth(200);
        insertAge.setAlignment(Pos.TOP_CENTER);
        insertAge.setSpacing(30);
        insertAge.setPadding(new Insets(5, 5, 5, 8));
        insertAge.getChildren().addAll(age, ageField);

        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(10, 5, 5, 5));
        vBoxMenu.getChildren().addAll(insertName, insertAge, insertPhone, insertPass, submit);

        Scene scene = new Scene(vBoxMenu, 550, 350);
        stage.setScene(scene);
        stage.show();
    }

    ///////////////// ADD VC ////////////////////
    public void addVC() {
        Stage stage = new Stage();
        stage.setTitle("ADD USER");

        Label name = new Label("Enter name:  ");
        Label phone = new Label("Enter your phone number: ");
        Label password = new Label("Enter a password: ");
        Label capa = new Label("Enter a Capacity per day:");

        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField passField = new TextField();
        TextField capaField = new TextField();

        Button submit = new Button();
        submit.setText("Submit");
        submit.setOnAction(e -> {
            csv.addUser(passField.getText(), "vcadmin", nameField.getText(), "none", "none", phoneField.getText(),
                    capaField.getText(), "none");
            apearWindow.display("Succesfull", "Succesfully Updated!");
            stage.close();
        });

        HBox insertName = new HBox();
        insertName.setPrefWidth(200);
        insertName.setAlignment(Pos.TOP_CENTER);
        insertName.setSpacing(30);
        insertName.setPadding(new Insets(5, 5, 5, 5));
        insertName.getChildren().addAll(name, nameField);

        HBox insertPhone = new HBox();
        insertPhone.setPrefWidth(200);
        insertPhone.setAlignment(Pos.TOP_CENTER);
        insertPhone.setSpacing(30);
        insertPhone.setPadding(new Insets(5, 5, 5, 70));
        insertPhone.getChildren().addAll(phone, phoneField);

        HBox insertPass = new HBox();
        insertPass.setPrefWidth(200);
        insertPass.setAlignment(Pos.TOP_CENTER);
        insertPass.setSpacing(30);
        insertPass.setPadding(new Insets(5, 5, 5, 20));
        insertPass.getChildren().addAll(password, passField);

        HBox insertCapa = new HBox();
        insertCapa.setPrefWidth(200);
        insertCapa.setAlignment(Pos.TOP_CENTER);
        insertCapa.setSpacing(30);
        insertCapa.setPadding(new Insets(5, 5, 5, 50));
        insertCapa.getChildren().addAll(capa, capaField);

        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(10, 5, 5, 5));
        vBoxMenu.getChildren().addAll(insertName, insertPhone, insertPass, insertCapa, submit);

        Scene scene = new Scene(vBoxMenu, 550, 350);
        stage.setScene(scene);
        stage.show();
    }

    ////////////// DISTRIBUTE VACCINE //////////////////////
    public void distributeVaccine() {
        Stage stage = new Stage();
        stage.setTitle("Distribute Vaccine");

        Label idFrom = new Label("Enter User ID from");
        Label idTo = new Label("Enter User ID to");
        Label assignVC = new Label("Enter Assigned Vaccination center:");

        TextField idFromField = new TextField();
        TextField idToField = new TextField();
        TextField assignVCField = new TextField();

        Button submit = new Button();
        submit.setText("Submit");
        submit.setOnAction(e -> {
            try {
                if(csv.GetUserDataByID(idFromField.getText(),ID_INDEX).equals("NOT FOUND") || csv.GetUserDataByID(idToField.getText(),ID_INDEX).equals("NOT FOUND")     ){
                    throw new Exception("ID out of Index");
                }
                if(!csv.GetUserDataByUsername(assignVCField.getText(), USERTYPE_INDEX).equals("vcadmin")){
                    throw new Exception("Invalid Vaccination Center");
                }
                csv.setMultipleUserData(idFromField.getText(), idToField.getText(), assignVCField.getText(),VCASSINGED_INDEX);
                stage.close();
                System.out.println("succes");
            } catch (Exception ex) {
                apearWindow.display("Error!", "close" + ex.getMessage());
                System.out.println("fail");
            }
        });

        HBox insertFrom = new HBox();
        insertFrom.setPrefWidth(200);
        insertFrom.setAlignment(Pos.TOP_CENTER);
        insertFrom.setSpacing(30);
        insertFrom.setPadding(new Insets(5, 5, 5, 20));
        insertFrom.getChildren().addAll(idFrom, idFromField);

        HBox insertTo = new HBox();
        insertTo.setPrefWidth(200);
        insertTo.setAlignment(Pos.TOP_CENTER);
        insertTo.setSpacing(30);
        insertTo.setPadding(new Insets(5, 5, 5, 5));
        insertTo.getChildren().addAll(idTo, idToField);

        HBox insertVC = new HBox();
        insertVC.setPrefWidth(200);
        insertVC.setAlignment(Pos.TOP_CENTER);
        insertVC.setSpacing(30);
        insertVC.setPadding(new Insets(5, 5, 5, 100));
        insertVC.getChildren().addAll(assignVC, assignVCField);

        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(10, 5, 5, 5));
        vBoxMenu.getChildren().addAll(insertFrom, insertTo, insertVC, submit);

        Scene scene = new Scene(vBoxMenu, 600, 300);
        stage.setScene(scene);
        stage.show();
    }

    ////////////// VACCINATION STATISTIC /////////////////////
    public void vacinationStatistic() {
        Stage stage = new Stage();
        stage.setTitle("Vacination Statistic");

        Label receiveOneDose = new Label("\tReceive 1st Dose Date of vaccination! \n\t\t ==> "
                + (csv.ComparenCountField(FSTSTATUS_INDEX, "Appointment made")));
        Label completedOneDose = new Label(
                "\tComplete 1st Dose of Vaccination! \n\t\t ==> " + csv.ComparenCountField(FSTSTATUS_INDEX, "Done"));
        Label receiveTwoDose = new Label("\tReceive 2nd Dose Date of vaccination! \n\t\t ==> "
                + (csv.ComparenCountField(SCNDSTATUS_INDEX, "Appointment made")));
        Label completedTwoDose = new Label(
                "\tComplete 2nd Dose of Vaccination! \n\t\t ==> " + csv.ComparenCountField(SCNDSTATUS_INDEX, "Done"));
        Label completedBothDose = new Label("\tComplete Both Dose of Vaccination! \n\t\t ==> "
                + (csv.ComparenCountField(FSTSTATUS_INDEX, "Done") + csv.ComparenCountField(SCNDSTATUS_INDEX, "Done")));

        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(10, 5, 5, 5));
        vBoxMenu.getChildren().addAll(receiveOneDose, completedOneDose, receiveTwoDose, completedTwoDose,
                completedBothDose);

        Scene scene = new Scene(vBoxMenu, 450, 350);
        stage.setScene(scene);
        stage.show();
    }

    /////////// VIEW RECIPIENT LIST ////////////
    public void viewRecipientList() {
        Stage stage = new Stage();
        stage.setTitle("Vacination Recipient Statistic");

        TableColumn<RecipientData, String> id = new TableColumn<>("ID");
        TableColumn<RecipientData, String> pass = new TableColumn<>("PASS");
        TableColumn<RecipientData, String> userName = new TableColumn<>("USERNAME");
        TableColumn<RecipientData, String> firstVacStatus = new TableColumn<>("1st Vac Status");
        TableColumn<RecipientData, String> secondVacStatus = new TableColumn<>("2nd Vac Status");
        TableColumn<RecipientData, String> firstVacDate = new TableColumn<>("1st Vac Date");
        TableColumn<RecipientData, String> secondVacDate = new TableColumn<>("2nd Vac Date");
        TableColumn<RecipientData, String> phone = new TableColumn<>("Phone");
        TableColumn<RecipientData, String> assignedVC = new TableColumn<>("ASSGINED VC");
        TableColumn<RecipientData, String> age = new TableColumn<>("Age");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        pass.setCellValueFactory(new PropertyValueFactory<>("pass"));
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        firstVacDate.setCellValueFactory(new PropertyValueFactory<>("firstVacDate"));
        firstVacStatus.setCellValueFactory(new PropertyValueFactory<>("firstVacStatus"));
        secondVacDate.setCellValueFactory(new PropertyValueFactory<>("secondVacDate"));
        secondVacStatus.setCellValueFactory(new PropertyValueFactory<>("secondVacStatus"));

        dataTable.getColumns().addAll(id, pass, userName, age, phone, assignedVC, firstVacDate, firstVacStatus,
                secondVacDate, secondVacStatus);
        dataTable.getItems().addAll(recipientPosition);

        VBox vBoxMenu = new VBox();
        vBoxMenu.setSpacing(20);
        vBoxMenu.getChildren().add(dataTable);

        Scene scene = new Scene(vBoxMenu, 800, 400);
        stage.setScene(scene);
        stage.show();
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
                if (fields[USERTYPE_INDEX].equals("recipient")) {
                    RecipientData record = new RecipientData(fields[0], fields[1], fields[3], fields[11], fields[8],
                            fields[9], fields[6], fields[4], fields[7], fields[5]);
                    recipientPosition.add(record);

                }
            }

        } catch (FileNotFoundException ex) {
            apearWindow.display("Error!", "ERROR :" + ex.getMessage());
        } catch (IOException ex) {

            apearWindow.display("Error!", "ERROR : " + ex.getMessage());
        }
    }
}
    
