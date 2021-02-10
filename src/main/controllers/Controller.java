package main.controllers;

import javafx.stage.Stage;
import main.views.FxHandler;
import main.views.PenteView;

import java.io.IOException;

public class Controller {

    //Main view integrating with JavaFX
    PenteView penteView;

    //Constructor taking a stage to utilize JavaFX
    public Controller(Stage primaryStage) throws IOException {

        //Initializing the view with a JavaFX stage
        penteView = new PenteView(primaryStage);
    }

    //Test method as the core of this class
    public void run () {

        //Instantiating the current handler class connected to the fxml loader in the view
        //Must retrieve from view instead of using constructor
        FxHandler fxHandler = penteView.getFxHandler();

        //Test command to ensure fxml objects can be manipulated
        fxHandler.PlayGameBtn.setText("Don't press this button!");
    }

    //string playerOneName
    //string playerTwoName
    //Engine engine(secondPlayerIsAI)
    //PenteView view

    //userClick(x, y) (call all engine methods) (what happened in turn. Display on gui)
    //                  (check for tria and tesera. Display in separate part of GUI)


    //setup()
}
