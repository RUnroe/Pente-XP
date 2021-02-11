package main.controllers;

import javafx.stage.Stage;
import main.views.PenteView;

import java.io.IOException;

public class GameController {


//    //Main view integrating with JavaFX
//    PenteView penteView;
//
//    //Constructor taking a stage to utilize JavaFX
//    public GameController(Stage primaryStage) throws IOException {
//
//        //Initializing the view with a JavaFX stage
//        penteView = new PenteView(primaryStage);
//    }
    private Engine engine;
    private String playerOneName;

    public String getPlayerOneName() {
        return playerOneName;
    }

    public void setPlayerOneName(String playerOneName) {
        this.playerOneName = playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public void setPlayerTwoName(String playerTwoName) {
        this.playerTwoName = playerTwoName;
    }

    private String playerTwoName;

    public GameController() {}

    //Test method as the core of this class
    public void run () {

        //Instantiating the current handler class connected to the fxml loader in the view
        //Must retrieve from view instead of using constructor
//        FxHandler fxHandler = penteView.getFxHandler();

        //Test command to ensure fxml objects can be manipulated
//        fxHandler.PlayGameBtn.setText("Don't press this button!");
//        String playerOneName = fxHandler.pOneName;

    }

    //Test for clicking coords
    public void userClick(int y, int x) {
        System.out.println("User clicked " + x + "," + y +"!");
        engine.makeMove(y, x);
        engine.passTurn();

    }

    public Engine getEngine() {
        return engine;
    }
    public void createGame(Boolean secondPlayerIsAI) {
        engine = new Engine(secondPlayerIsAI);
    }


    //PenteView view

    //userClick(x, y) (call all engine methods) (what happened in turn. Display on gui)
    //                  (check for tria and tesera. Display in separate part of GUI)


    //setup()
}
