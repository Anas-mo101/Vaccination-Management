import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.Insets;

public class GUIrecipient {

    private final int NAME_INDEX = 3;
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    private final int FIRSTVAC_INDEX = 6;
    private final int SCNDVAC_INDEX = 7;
    private final int PHONE_INDEX = 8;
    private final int AGE_INDEX = 11;
    private final int VAC1_INDEX = 13;
    private final int VAC2_INDEX = 14;

    Stage mainStage = new Stage();

    private String UserName;

    GUIrecipient(String[] data, String UserName) {
        this.UserName = UserName;

        mainStage.setTitle("RECIPIENT MENU");
        Text menuTitle = new Text("Hi dearest customer, " + UserName);
        Text menuTitle2 = new Text("please choose:");
        menuTitle.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        menuTitle.setStroke(Color.BLUE);
        menuTitle2.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        menuTitle2.setStroke(Color.BLUE);

        Button buttonStatus = new Button();
        buttonStatus.setText("View Recipient Status");
        buttonStatus.setOnAction(e -> {
            ViewStatus(data);
        });
        buttonStatus.setMinWidth(250);

        Button buttonExit = new Button();
        buttonExit.setText("Exit");
        buttonExit.setOnAction(e -> {
            mainStage.close();
        });
        buttonExit.setMinWidth(175);

        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(50, 5, 5, 5));
        vBoxMenu.getChildren().addAll(menuTitle, menuTitle2, buttonStatus, buttonExit);

        Scene scene = new Scene(vBoxMenu, 675, 300);
        mainStage.setScene(scene);
        mainStage.show();

    }

    public void ViewStatus(String[] userData) {
        Stage stage = new Stage();
        stage.setTitle("VIEW RECIPIENT STATUS");
        Text menuTitle3 = new Text("Profile details for " + UserName);
        menuTitle3.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        menuTitle3.setStroke(Color.GREEN);

        Label tfName = new Label("Name \t  " + userData[NAME_INDEX]);
        Label tfAge = new Label("Age \t  " + userData[AGE_INDEX]);
        Label tfPhone = new Label("Phone number \t  " + userData[PHONE_INDEX]);
        Label tfVac1 = new Label(
                "1st Vaccine Status \t  " + userData[FSTSTATUS_INDEX] + " - " + userData[FIRSTVAC_INDEX]);
        Label tfBatch1 = new Label("Batch number \t  " + userData[VAC1_INDEX]);
        Label tfVac2 = new Label(
                "\t2nd Vaccine Status \t   " + userData[SCNDSTATUS_INDEX] + " - " + userData[SCNDVAC_INDEX]);
        Label tfBatch2 = new Label("Batch number \t   " + userData[VAC2_INDEX]);

        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(10, 5, 5, 5));
        vBoxMenu.getChildren().addAll(menuTitle3, tfName, tfAge, tfPhone, tfVac1, tfBatch1, tfVac2, tfBatch2);

        Scene scene = new Scene(vBoxMenu, 675, 400);
        stage.setScene(scene);
        stage.show();
    }

    private int getUserLine() {

        String data = System.getProperty("user.dir");
        String FieldDelimiter = ",";

        BufferedReader br;
        int counter = 0;

        try {
            br = new BufferedReader(new FileReader(data + "/users.csv"));

            String line;
            while ((line = br.readLine()) != null) {
                counter++;
                String[] fields = line.split(FieldDelimiter, -1);
                if (fields[3].equals(UserName))
                    break;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("ERROR :" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERROR :" + ex.getMessage());
        }
        return counter - 1;
    }
}


