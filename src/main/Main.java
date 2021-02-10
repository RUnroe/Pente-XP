package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.controllers.Controller;
import main.views.PenteView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller(primaryStage);
        //controller.start()//or whatever setup method controller has lulz
    }

    public static void main(String[] args) {
        launch(args);
    }
}
