import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;


public class MainMenuMoh extends Application {
    Csvreader csv = new Csvreader();
    private final int ID_INDEX = 0;
    private final int USERTYPE_INDEX = 2;
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    private final int VCASSINGED_INDEX = 9;

    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("MOH MENU");

        Button buttonRecipient = new Button();
        buttonRecipient.setText("Add Recipient");
        buttonRecipient.setOnAction(e->{
            addRecipent();
        });

        Button buttonVC = new Button();
        buttonVC.setText("Add VC");
        buttonVC.setOnAction(e->{
            addVC();
        });

        Button buttonRecipientList = new Button();
        buttonRecipientList.setText("View Recipient List");
        buttonRecipientList.setOnAction(e->{
            viewRecipientList();
        });
        Button buttonStatistic = new Button();
        buttonStatistic.setText("View Vacination Statistic");
        buttonStatistic.setOnAction(e->{
            vacinationStatistic();
        });

        Button distributeVaccineButton = new Button();
        distributeVaccineButton.setText("Distribute Vaccine");
        distributeVaccineButton.setOnAction(e->{
            distributeVaccine();
        });

        Button buttonExit = new Button();
        buttonExit.setText("Exit");
        buttonExit.setOnAction(e->{
            mainStage.close();});

        HBox hBoxMenu = new HBox();
        hBoxMenu.setPrefWidth(200);
        hBoxMenu.setAlignment(Pos.TOP_CENTER);
        hBoxMenu.setSpacing(30);
        hBoxMenu.setPadding(new Insets(90, 5, 5, 5));
        hBoxMenu.getChildren().addAll(buttonRecipient,buttonVC, buttonRecipientList, buttonStatistic, distributeVaccineButton, buttonExit);

        Scene scene = new Scene (hBoxMenu,800,250);
        mainStage.setScene(scene);
        mainStage.show();


    }

    
    public void addRecipent() {
        Stage stage = new Stage();
        stage.setTitle("ADD USER");

        Label name = new Label("Enter name:  "); 
        Label phone = new Label("Enter your phone number: ");
        Label age = new Label ("Enter your Age: ");
        Label password = new Label("Enter a password: ");

        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField passField = new TextField();
        TextField ageField = new TextField();


        Button submit = new Button();
        submit.setText("Submit");
        submit.setOnAction(e->{
            csv.addUser(passField.getText(), "recipient", nameField.getText(), "Pending", "Pending", phoneField.getText(), "none", ageField.getText());
            apearWindow.display("Succesfull", "Succesfully Updated!");
            stage.close();
        });
        

        HBox insertName = new HBox();
        insertName.setPrefWidth(200);
        insertName.setAlignment(Pos.TOP_CENTER);
        insertName.setSpacing(30);
        insertName.setPadding(new Insets(5, 5, 5, 5));
        insertName.getChildren().addAll(name ,nameField);

        HBox insertPhone = new HBox();
        insertPhone.setPrefWidth(200);
        insertPhone.setAlignment(Pos.TOP_CENTER);
        insertPhone.setSpacing(30);
        insertPhone.setPadding(new Insets(5, 5, 5, 5));
        insertPhone.getChildren().addAll(phone, phoneField);

        HBox insertPass = new HBox();
        insertPass.setPrefWidth(200);
        insertPass.setAlignment(Pos.TOP_CENTER);
        insertPass.setSpacing(30);
        insertPass.setPadding(new Insets(5, 5, 5, 5));
        insertPass.getChildren().addAll(password, passField);

        HBox insertAge = new HBox();
        insertAge.setPrefWidth(200);
        insertAge.setAlignment(Pos.TOP_CENTER);
        insertAge.setSpacing(30);
        insertAge.setPadding(new Insets(5, 5, 5, 5));
        insertAge.getChildren().addAll(age,ageField);
        

        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(10, 5, 5, 5));
        vBoxMenu.getChildren().addAll(insertName,insertAge,insertPhone,insertPass,submit);

        Scene scene = new Scene (vBoxMenu,600,350);
        stage.setScene(scene);
        stage.show();
    }
    public void addVC() {
        Stage stage = new Stage();
        stage.setTitle("ADD USER");

        Label name = new Label("Enter name:  ");
        Label phone = new Label("Enter your phone number: ");
        Label password = new Label("Enter a password: ");
        Label capa = new Label ("Enter a Capacity per day:");

        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField passField = new TextField();
        TextField capaField = new TextField();



        Button submit = new Button();
        submit.setText("Submit");
        submit.setOnAction(e->{
            csv.addUser(passField.getText(), "vcadmin", nameField.getText(), "none", "none", phoneField.getText(), capaField.getText(), "none");
            apearWindow.display("Succesfull", "Succesfully Updated!");
            stage.close();
        });
        

        HBox insertName = new HBox();
        insertName.setPrefWidth(200);
        insertName.setAlignment(Pos.TOP_CENTER);
        insertName.setSpacing(30);
        insertName.setPadding(new Insets(5, 5, 5, 5));
        insertName.getChildren().addAll(name ,nameField);

        HBox insertPhone = new HBox();
        insertPhone.setPrefWidth(200);
        insertPhone.setAlignment(Pos.TOP_CENTER);
        insertPhone.setSpacing(30);
        insertPhone.setPadding(new Insets(5, 5, 5, 5));
        insertPhone.getChildren().addAll(phone, phoneField);

        HBox insertPass = new HBox();
        insertPass.setPrefWidth(200);
        insertPass.setAlignment(Pos.TOP_CENTER);
        insertPass.setSpacing(30);
        insertPass.setPadding(new Insets(5, 5, 5, 5));
        insertPass.getChildren().addAll(password, passField);
     
        HBox insertCapa = new HBox();
        insertCapa.setPrefWidth(200);
        insertCapa.setAlignment(Pos.TOP_CENTER);
        insertCapa.setSpacing(30);
        insertCapa.setPadding(new Insets(5, 5, 5, 5));
        insertCapa.getChildren().addAll(capa,capaField);

        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(10, 5, 5, 5));
        vBoxMenu.getChildren().addAll(insertName,insertPhone,insertPass,insertCapa,submit);

        Scene scene = new Scene (vBoxMenu,600,350);
        stage.setScene(scene);
        stage.show();
    }

    public void distributeVaccine(){
        Stage stage = new Stage();
        stage.setTitle("Distribute Vaccine");

        Label idFrom = new Label("Enter User ID from");
        Label idTo = new Label("Enter User ID to");
        Label assignVC = new Label ("Enter Assigned Vaccination center:");

        TextField idFromField = new TextField();
        TextField idToField = new TextField();
        TextField assignVCField = new TextField();



        Button submit = new Button();
            submit.setText("Submit");
            submit.setOnAction(e->{
                try{
                    int idFrom_intForm = Integer.parseInt(idFromField.getText());
                    int idTo_intTo = Integer.parseInt(idToField.getText());
                    if(idFrom_intForm <5 || idTo_intTo >300 || idFrom_intForm > idTo_intTo ) //change sepcific
                        throw new Exception("ID out of Index");
                    csv.GetUserDataByUsername(assignVCField.getText(), USERTYPE_INDEX).equals("vcadmin");
                    csv.setMultipleUserData(idFromField.getText(),idToField.getText(),assignVCField.getText(),VCASSINGED_INDEX);
                    System.out.println("succes");
                }
                catch(Exception ex){
                    apearWindow.display("Error!", "close" + ex.getMessage());
                    System.out.println("fail");
                }
            });
        
        HBox insertFrom = new HBox();
        insertFrom.setPrefWidth(200);
        insertFrom.setAlignment(Pos.TOP_CENTER);
        insertFrom.setSpacing(30);
        insertFrom.setPadding(new Insets(5, 5, 5, 5));
        insertFrom.getChildren().addAll(idFrom,idFromField);

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
        insertVC.setPadding(new Insets(5, 5, 5, 5));
        insertVC.getChildren().addAll(assignVC, assignVCField);

        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(10, 5, 5, 5));
        vBoxMenu.getChildren().addAll(insertFrom,insertTo,insertVC,submit);

        Scene scene = new Scene (vBoxMenu,700,300);
        stage.setScene(scene);
        stage.show();
    }   

    public void vacinationStatistic(){
        Stage stage = new Stage();
        stage.setTitle("Vacination Statistic");

        Label receiveOneDose = new Label("\tReceive 1st Dose Date of vaccination! \n\t\t ==> "+ (csv.ComparenCountField(FSTSTATUS_INDEX, "Appointment made")));
        Label completedOneDose = new Label("\tComplete 1st Dose of Vaccination! \n\t\t ==> "+ csv.ComparenCountField(FSTSTATUS_INDEX, "Done"));
        Label receiveTwoDose = new Label ("\tReceive 2nd Dose Date of vaccination! \n\t\t ==> "+ (csv.ComparenCountField(SCNDSTATUS_INDEX, "Appointment made")));
        Label completedTwoDose = new Label("\tComplete 2nd Dose of Vaccination! \n\t\t ==> "+ csv.ComparenCountField(SCNDSTATUS_INDEX, "Done"));
        Label completedBothDose = new Label("\tComplete Both Dose of Vaccination! \n\t\t ==> "+ (csv.ComparenCountField(FSTSTATUS_INDEX, "Done")+ csv.ComparenCountField(SCNDSTATUS_INDEX, "Done")));

        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(10, 5, 5, 5));
        vBoxMenu.getChildren().addAll(receiveOneDose,completedOneDose,receiveTwoDose,completedTwoDose,completedBothDose);

        Scene scene = new Scene (vBoxMenu,450,350);
        stage.setScene(scene);
        stage.show();
    }

    public void viewRecipientList(){

    }
}
