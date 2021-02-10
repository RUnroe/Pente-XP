package main.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PenteView {

    private Stage primaryStage;
    private FxHandler fxHandler;

    public PenteView(Stage primaryStage) {
        setupGui(primaryStage);
    }

    public void setupGui(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();

            //Sets the resource path for the fxml loader
            loader.setLocation(Objects.requireNonNull(getClass().getResource("../resources/penteTemplateTest.fxml")));

            //Instantiates a base template for scenes to use from the fxml
            Parent root = loader.load();

            primaryStage.setTitle("Pente XP");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            setPrimaryStage(primaryStage);

            //Use this every time new fxml is loaded to ensure correct handler
            setFxHandler(loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public FxHandler getFxHandler() {
        return fxHandler;
    }

    public void setFxHandler(FxHandler fxHandler) {
        this.fxHandler = fxHandler;
    }
}
