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
    static int n = 0;
    Csvreader csv = new Csvreader();

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
            System.out.println("Call: "+n+" times");
            n++;
        });

        Button buttonVC = new Button();
        buttonVC.setText("Add VC");
        buttonVC.setOnAction(e->{
            addVC();
            System.out.println("Call: "+n+" times");
            n++;
        });

        Button button2 = new Button();
        button2.setText("View Recipient List");
        button2.setOnAction(e->{
            mainStage.close();
            // System.out.println("Call: "+n+" times");
            // n++;
        });
        Button button3 = new Button();
        button3.setText("View Vacination Static");

        Button button4 = new Button();
        button4.setText("Distribute Vaccine");

        Button button5 = new Button();
        button5.setText("Exit");
        button5.setOnAction(e->{
            mainStage.close();});

        HBox hBoxMenu = new HBox();
        hBoxMenu.setPrefWidth(200);
        hBoxMenu.setAlignment(Pos.TOP_CENTER);
        hBoxMenu.setSpacing(30);
        hBoxMenu.setPadding(new Insets(90, 5, 5, 5));
        hBoxMenu.getChildren().addAll(buttonRecipient,buttonVC, button2, button3, button4, button5);

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
            PopUpWindow.display("Succesfull", "Succesfully Updated!");
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

        Scene scene = new Scene (vBoxMenu,700,500);
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
            PopUpWindow.display("Succesfull", "Succesfully Updated!");
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

        Scene scene = new Scene (vBoxMenu,700,500);
        stage.setScene(scene);
        stage.show();
    }
}
