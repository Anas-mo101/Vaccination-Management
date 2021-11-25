import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;

public class Login extends Application {
    Csvreader csv = new Csvreader();
    Label lblStatus = new Label("");
    Label lblName = new Label("Username: ");
    Label lblPassword = new Label("Password: ");
    Label lblDateTime = new Label();
    TextField tfUsername = new TextField();
    TextField tfPassword = new TextField();
    Button btnLogin = new Button("Submit");
    Button btnReg = new Button("Register");
    private final int USERTYPE_INDEX = 2;

    @Override
    public void start(Stage primaryStage) {
        setElements();

        btnLogin.setOnAction((e) -> {
            login();
        });

        btnReg.setOnAction((e) -> {
            register();
        });

        Group root = new Group();
        root.getChildren().add(lblName);
        root.getChildren().add(lblPassword);
        root.getChildren().add(tfUsername);
        root.getChildren().add(tfPassword);
        root.getChildren().add(btnLogin);
        root.getChildren().add(btnReg);
        root.getChildren().add(lblStatus);
        Scene scene = new Scene(root, 230, 180);
        scene.getStylesheets().add("style.css");
        primaryStage.setResizable(false);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setElements() {
        lblName.setTranslateX(10);
        lblName.setTranslateY(10);

        lblPassword.setTranslateX(10);
        lblPassword.setTranslateY(50);

        tfUsername.setTranslateX(70);
        tfUsername.setTranslateY(10);

        tfPassword.setTranslateX(70);
        tfPassword.setTranslateY(50);

        lblStatus.setTranslateX(83);
        lblStatus.setTranslateY(90);

        btnLogin.setMinWidth(220);
        btnLogin.setTranslateX(5);
        btnLogin.setTranslateY(120);

        btnReg.setMinWidth(220);
        btnReg.setTranslateX(5);
        btnReg.setTranslateY(150);
    }

    public void login() {

        if (csv.CheckLoginDetails(tfUsername.getText(), tfPassword.getText())) {
            lblStatus.setText("Login Successful");
            switch (csv.GetUserData(USERTYPE_INDEX, csv.getUserLineLocation())) { // retrives user type after
                                                                                  // successfull login

            case "admin":
                MainMenuMoh admin = new MainMenuMoh(csv.GetUserData(csv.getUserLineLocation()));
                break;

            case "vcadmin":
                MainMenuVaccinationCenter user = new MainMenuVaccinationCenter(
                        csv.GetUserData(csv.getUserLineLocation()));
                break;

            case "recipient":
                GUIrecipient recipient = new GUIrecipient(csv.GetUserData(csv.getUserLineLocation()),
                        tfUsername.getText());
                break;

            default:
                break;
            }
        } else {
            lblStatus.setText("Login Failed");
        }
    }

    public void register() {

        Stage stage = new Stage();
        stage.setTitle("REGISTRATION");
        Text menuTitle = new Text("WELCOME TO JAVA COVID-19 VACCINATION PROGRAM");
        menuTitle.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        menuTitle.setStroke(Color.BLUE);

        Label tfName = new Label("Name:  ");
        Label tfPhone = new Label("Phone number: ");
        Label tfAge = new Label("Age: ");
        Label tfPassword = new Label("Password: ");

        TextField tfNamee = new TextField();
        TextField tfPhonee = new TextField();
        TextField tfAgee = new TextField();
        TextField tfPasswordd = new TextField();

        Button submit = new Button("Submit");

        submit.setOnAction(e -> {

            if (valPhone(tfPhonee.getText())) {
                csv.addUser(tfPasswordd.getText(), "recipient", tfNamee.getText(), "Pending", "Pending",
                        tfPhonee.getText(), "none", tfAgee.getText());
                csv = new Csvreader();
                apearWindow.display("Succesfull", "Succesfully Updated!");

            } else
                apearWindow.display("Failed", "Invalid phone number!");

            stage.close();
        });

        tfName.setTranslateX(10);
        tfName.setTranslateY(10);

        tfPhone.setTranslateX(10);
        tfPhone.setTranslateY(50);

        tfAge.setTranslateX(10);
        tfAge.setTranslateY(90);

        tfPassword.setTranslateX(10);
        tfPassword.setTranslateY(130);

        tfNamee.setTranslateX(100);
        tfNamee.setTranslateY(10);

        tfPhonee.setTranslateX(100);
        tfPhonee.setTranslateY(50);

        tfAgee.setTranslateX(100);
        tfAgee.setTranslateY(90);

        tfPasswordd.setTranslateX(100);
        tfPasswordd.setTranslateY(130);

        submit.setTranslateX(100);
        submit.setTranslateY(160);

        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(50, 5, 5, 5));
        vBoxMenu.getChildren().addAll(menuTitle);

        Group root = new Group();
        root.getChildren().add(tfName);
        root.getChildren().add(tfPhone);
        root.getChildren().add(tfAge);
        root.getChildren().add(tfPassword);
        root.getChildren().add(tfNamee);
        root.getChildren().add(tfPhonee);
        root.getChildren().add(tfAgee);
        root.getChildren().add(tfPasswordd);
        root.getChildren().add(submit);

        vBoxMenu.getChildren().add(root);

        Scene scene = new Scene(vBoxMenu, 370, 300); 
        scene.getStylesheets().add("style.css"); 
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public boolean valPhone(String tfPhonee) {
        return tfPhonee.charAt(0) == '0' && tfPhonee.charAt(1) == '1' && tfPhonee.length() == 10;
    }

    public static void main(String[] args) {
        Platform.setImplicitExit(false);
        launch(args);
    }
}
