import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class GUIregister extends Application {
    Csvreader csv = new Csvreader();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("MAIN MENU");

        Button buttonRegister = new Button();
        buttonRegister.setText("Register");
        buttonRegister.setOnAction(e -> {
            register();
        });

        Button buttonLogin = new Button();
        buttonLogin.setText("Login");
        buttonLogin.setOnAction(e -> {
            // login
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
        hBoxMenu.getChildren().addAll(buttonRegister, buttonLogin, buttonExit);

        Scene scene = new Scene(hBoxMenu, 800, 250);
        mainStage.setScene(scene);
        mainStage.show();

    }

    public void register() {
        Stage stage = new Stage();
        stage.setTitle("REGISTRATION");

        Label tfName = new Label("Name:  ");
        Label tfPhone = new Label("Phone number: ");
        Label tfAge = new Label("Age: ");
        Label tfPassword = new Label("Password: ");

        TextField tfNamee = new TextField();
        TextField tfPhonee = new TextField();
        TextField tfAgee = new TextField();
        TextField tfPasswordd = new TextField();

        Button submit = new Button();
        submit.setText("Submit");
        submit.setOnAction(e -> {
            csv.addUser(tfPasswordd.getText(), "recipient", tfNamee.getText(), "Pending", "Pending", tfPhonee.getText(),
                    "none", tfAgee.getText());
            apearWindow.display("Succesfull", "Succesfully Updated!");
            stage.close();
        });

        HBox insertName = new HBox();
        insertName.setPrefWidth(200);
        insertName.setAlignment(Pos.TOP_CENTER);
        insertName.setSpacing(30);
        insertName.setPadding(new Insets(5, 5, 5, 5));
        insertName.getChildren().addAll(tfName, tfNamee);

        HBox insertPhone = new HBox();
        insertPhone.setPrefWidth(200);
        insertPhone.setAlignment(Pos.TOP_CENTER);
        insertPhone.setSpacing(30);
        insertPhone.setPadding(new Insets(5, 5, 5, 5));
        insertPhone.getChildren().addAll(tfPhone, tfPhonee);

        HBox insertPass = new HBox();
        insertPass.setPrefWidth(200);
        insertPass.setAlignment(Pos.TOP_CENTER);
        insertPass.setSpacing(30);
        insertPass.setPadding(new Insets(5, 5, 5, 5));
        insertPass.getChildren().addAll(tfPassword, tfPasswordd);

        HBox insertAge = new HBox();
        insertAge.setPrefWidth(200);
        insertAge.setAlignment(Pos.TOP_CENTER);
        insertAge.setSpacing(30);
        insertAge.setPadding(new Insets(5, 5, 5, 5));
        insertAge.getChildren().addAll(tfAge, tfAgee);

        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(10, 5, 5, 5));
        vBoxMenu.getChildren().addAll(insertName, insertAge, insertPhone, insertPass, submit);

        Scene scene = new Scene(vBoxMenu, 600, 350);
        stage.setScene(scene);
        stage.show();
    }
}