import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;


public class MainMenuMoh extends Application {
    static int n = 0;

    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("MOH MENU");

        Button button1 = new Button();
        button1.setText("Add User");
        button1.setOnAction(e->{
            addUser();
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

        HBox hBoxMenu = new HBox();
        hBoxMenu.setPrefWidth(200);
        hBoxMenu.setAlignment(Pos.TOP_CENTER);
        hBoxMenu.setSpacing(30);
        hBoxMenu.setPadding(new Insets(90, 5, 5, 5));
        hBoxMenu.getChildren().addAll(button1, button2, button3, button4, button5);

        // StackPane layout = new StackPane();
        // layout.setAlignment(Pos.CENTER);
        // layout.setMinHeight(60);
        // layout.getChildren().add(button1);
        // layout.getChildren().add(button2);

        Scene scene = new Scene (hBoxMenu,700,250);
        mainStage.setScene(scene);
        mainStage.show();


    }

    public void addUser() {
        Stage stage = new Stage();
        stage.setTitle("ADD USER!");

        Label name = new Label("Name: ");
        Label phone = new Label("Phone: ");
        Label password = new Label("Password: ");


        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(90, 5, 5, 5));
        vBoxMenu.getChildren().addAll(name, phone, password);

        Scene scene = new Scene (vBoxMenu,700,250);
        stage.setScene(scene);
        stage.show();
    }
}