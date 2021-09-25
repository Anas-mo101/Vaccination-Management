import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

public class Login extends Application{
  Csvreader csv = new Csvreader();
  Label lblName = new Label("Username: ");
  Label lblPassword = new Label("Password: ");
  Label lblDateTime = new Label();
  TextField tfUsername = new TextField();
  TextField tfPassword = new TextField(); 
  Button btnTime = new Button("Submit");

  @Override
  public void start(Stage primaryStage) {

    btnTime.setOnAction((e) -> {     
      primaryStage.hide();
      csv.CheckLoginDetails(tfUsername.getText(), tfPassword.getText());
    });

    GridPane gridPane = new GridPane();
    gridPane.add (lblName, 0, 1);      
    gridPane.add (tfUsername,  1, 1);      
    gridPane.add (tfPassword,  1, 2);   
    gridPane.add (lblPassword,  0, 2);   
    gridPane.add (btnTime, 1, 3);      
    gridPane.add (lblDateTime, 0, 4);  

    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(5);
    Scene scene = new Scene (gridPane, 220, 120);

    primaryStage.setResizable(false);
    primaryStage.setTitle ("Login");
    primaryStage.setScene (scene);
    primaryStage.show();
  }
  
  public static void main(String[] args) {
    Platform.setImplicitExit(false);
    launch(args);
  }


}
