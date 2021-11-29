import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;

public class apearWindow{
    public static void display(String title, String message){
        Stage stageapear = new Stage();
        stageapear.setResizable(false);
        stageapear.setTitle(title);
        stageapear.initModality(Modality.APPLICATION_MODAL);

        Label labelMessage = new Label();
        labelMessage.setId("layoutFont");
        labelMessage.setText(message);
        labelMessage.setMinSize(200, 100);
        labelMessage.setAlignment(Pos.CENTER);

        Button buttonAlert = new Button();
        buttonAlert.setText("OK");
        buttonAlert.setMinSize(200, 50);
        buttonAlert.setOnAction(e -> {
            stageapear.close();
        });
        buttonAlert.setAlignment(Pos.CENTER);

        VBox gridAlert = new VBox();
        gridAlert.setPadding(new Insets(5));
        gridAlert.getChildren().addAll(labelMessage, buttonAlert);
        gridAlert.setAlignment(Pos.CENTER);

        Scene sceneAlert = new Scene(gridAlert);
        sceneAlert.getStylesheets().add("style.css");
        stageapear.setScene(sceneAlert);
        stageapear.showAndWait();
    }
}
