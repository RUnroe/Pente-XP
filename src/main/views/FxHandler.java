package main.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FxHandler {
    public Button PlayGameBtn;
    public Button btnBack;

    public void GoToGame(ActionEvent actionEvent) {
        Stage stage = (Stage) PlayGameBtn.getScene().getWindow();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Game!");
        stage.show();
    }

    public void GoToInstruction(ActionEvent actionEvent) {
    }

    public void onBackClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) btnBack.getScene().getWindow();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("penteTemplateTest.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Pente-XP");
        stage.show();
    }
}
