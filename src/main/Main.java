package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.controllers.Controller;
import main.views.PenteView;

import static javafx.application.Application.launch;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller(primaryStage);
    }

}
