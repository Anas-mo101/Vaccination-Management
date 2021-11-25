import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class GUIrecipient {

    private final int NAME_INDEX = 3;
    private final int FSTSTATUS_INDEX = 4;
    private final int SCNDSTATUS_INDEX = 5;
    private final int FIRSTVAC_INDEX = 6;
    private final int SCNDVAC_INDEX = 7;
    private final int PHONE_INDEX = 8;
    private final int VCASSIGNED_INDEX = 9;
    private final int AGE_INDEX = 11;

    Stage mainStage = new Stage();

    private String UserName;

    GUIrecipient(String[] data, String UserName) {
        this.UserName = UserName;
        // @Override
        // public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("RECIPIENT MENU");

        Button buttonStatus = new Button();
        buttonStatus.setText("View Recipient Status");
        buttonStatus.setOnAction(e -> {
            ViewStatus(data);
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
        hBoxMenu.getChildren().addAll(buttonStatus, buttonExit);

        Scene scene = new Scene(hBoxMenu, 800, 250);
        mainStage.setScene(scene);
        mainStage.show();

    }

    public void ViewStatus(String[] userData) {
        Stage stage = new Stage();
        Vaccine vac = new Vaccine();
        // Csvreader csv = new Csvreader();

        stage.setTitle("VIEW RECIPIENT STATUS");

        /*
         * Label tfName = new Label("\tName \t ==>  " + csv.GetUserData(NAME_INDEX,
         * getUserLine())); Label tfAge = new Label("\tAge \t ==>  " +
         * csv.GetUserData(AGE_INDEX, getUserLine())); Label tfPhone = new
         * Label("\tPhone number \t ==>  " + csv.GetUserData(PHONE_INDEX,
         * getUserLine())); Label tfVac1 = new Label("\t1st Vaccine Status \t ==>  " +
         * csv.GetUserData(FSTSTATUS_INDEX, getUserLine()) + " - " +
         * csv.GetUserData(FIRSTVAC_INDEX, getUserLine())); Label tfBatch1 = new
         * Label("\tBatch number \t ==>  " + vac.addVacBatchNO()); Label tfVac2 = new
         * Label("\t2nd Vaccine Status \t ==>  " + csv.GetUserData(SCNDSTATUS_INDEX,
         * getUserLine()) + " - " + csv.GetUserData(SCNDVAC_INDEX, getUserLine()));
         * Label tfBatch2 = new Label("\tBatch number \t ==>  " + vac.addVacBatchNO());
         */

        Label tfName = new Label("\tName \t ==>  " + userData[NAME_INDEX]);
        Label tfAge = new Label("\tAge \t ==>  " + userData[AGE_INDEX]);
        Label tfPhone = new Label("\tPhone number \t ==>  " + userData[PHONE_INDEX]);
        Label tfVac1 = new Label(
                "\t1st Vaccine Status \t ==>  " + userData[FSTSTATUS_INDEX] + " - " + userData[FIRSTVAC_INDEX]);
        Label tfBatch1 = new Label("\tBatch number \t ==>  " + vac.addVacBatchNO());
        Label tfVac2 = new Label(
                "\t2nd Vaccine Status \t ==>  " + userData[SCNDSTATUS_INDEX] + " - " + userData[SCNDVAC_INDEX]);
        Label tfBatch2 = new Label("\tBatch number \t ==>  " + vac.addVacBatchNO());

        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(200);
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.setSpacing(30);
        vBoxMenu.setPadding(new Insets(10, 5, 5, 5));
        vBoxMenu.getChildren().addAll(tfName, tfAge, tfPhone, tfVac1, tfBatch1, tfVac2, tfBatch2);

        Scene scene = new Scene(vBoxMenu, 600, 350);
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
