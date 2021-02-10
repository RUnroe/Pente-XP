package main.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PenteView {

    public PenteView(Stage primaryStage) {
        setupGui(primaryStage);
    }

    public void setupGui(Stage primaryStage) {
        Parent root = new StackPane();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../resources/penteTemplateTest.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Pente XP");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
