package main.controllers;

import javafx.stage.Stage;
import main.views.PenteView;

import java.io.IOException;

public class Controller {
    PenteView penteView;

    public Controller(Stage primaryStage) throws IOException {
        penteView = new PenteView(primaryStage);
    }

    //string playerOneName
    //string playerTwoName
    //Engine engine(secondPlayerIsAI)
    //PenteView view

    //userClick(x, y) (call all engine methods) (what happened in turn. Display on gui)
    //                  (check for tria and tesera. Display in separate part of GUI)


    //setup()
}
