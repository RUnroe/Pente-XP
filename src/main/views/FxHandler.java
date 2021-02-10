package main.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FxHandler {
    public Button PlayGameBtn;
    public Button btnBack;
    public Button InstructionBtn;

    public void GoToGame(ActionEvent actionEvent) {
        Stage stage = (Stage) PlayGameBtn.getScene().getWindow();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../resources/game.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("../resources/penteTemplateTest.fxml"));
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
}
