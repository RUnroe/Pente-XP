package main.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class PenteView {
    public TextField PlayerOneName;
    public TextField PlayerTwoName;
    public RadioButton gameTypePVP;
    public ToggleGroup gameType;
    public RadioButton gameTypePVC;
    public Button PlayGameBtn;
    public Button InstructionBtn;
    Stage primaryStage;

    public PenteView(Stage primaryStage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        this.primaryStage = primaryStage;
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
        setupFirstScene();
    }

    private void setupFirstScene() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("penteTemplateTest.fxml"));
//        Parent root = fxmlLoader.load();
        Parent root = FXMLLoader.load(getClass().getResource("penteTemplateTest.fxml"));
        primaryStage.setTitle("Pente-XP");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
