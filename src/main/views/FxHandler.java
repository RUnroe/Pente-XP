package main.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Class linked to fxml objects and methods
public class FxHandler {
    public Button PlayGameBtn;
    public Button btnBack;
    public Button InstructionBtn;
    public Button SettingsBtn;

    //Can't use this with JavaFX
//    private FxHandler() {}

    void changeScene(Stage stage, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GoToGame(ActionEvent actionEvent) {
        Stage stage = (Stage) PlayGameBtn.getScene().getWindow();
        changeScene(stage, "../resources/game.fxml");
    }

    public void GoToInstruction(ActionEvent actionEvent) {
        changeScene((Stage) InstructionBtn.getScene().getWindow(), "../resources/instructions.fxml");
    }

    public void onBackClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) btnBack.getScene().getWindow();

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../resources/penteTemplateTest.fxml")));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Pente-XP");
        stage.show();
    }

    public void UserClick(ActionEvent actionEvent) {
        Pattern pattern = Pattern.compile("\\d+_\\d+");
        Matcher matcher = pattern.matcher(actionEvent.getSource().toString());
        if (matcher.find()) System.out.println(matcher.group(0));
    }

    public void GoToSettings(ActionEvent actionEvent) {
        changeScene((Stage) SettingsBtn.getScene().getWindow(), "../resources/penteTemplateTest.fxml");
    }
}
