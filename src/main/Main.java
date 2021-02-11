package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.controllers.GameController;
import main.views.PenteView;

import static javafx.application.Application.launch;

//Must extend application for JavaFX
public class Main extends Application {

    public static void main(String[] args) {

        //Runs start method using command line arguments if any exist
        launch(args);
    }

    //Application method to use JavaFX
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Instantiates new Controller with the main JavaFx stage and calls the controller's core method
        new PenteView(primaryStage);
    }

}
