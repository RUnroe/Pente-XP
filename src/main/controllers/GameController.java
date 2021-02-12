package main.controllers;

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
    private String playerTwoName;


//    public boolean isWin;
//    public boolean isTesera;
//    public boolean isTria;
public String conditionStr = "";

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
        handleTurn(y, x);
//        System.out.println("User clicked " + x + "," + y +"!");
//        engine.makeMove(y, x);
//        engine.passTurn();

        //Checks if it is player 2's turn (not player one's turn) AND if player 2 is an AI

        if (!engine.isPlayerOneTurn() && engine.isP2Ai()) {
            handleAiTurn();
        }
    }

    private void handleAiTurn() {
        //Gets an array of [<y>, <x>] coordinates using the AI algorithms
        int[] yx;

        boolean isTurnHandled;

        //Attempts to handle turn until handle turn returns true
        //Uses same turn handling method as player with AI determined [<y>, <x>] values
        do {
            yx = engine.aiTurn();
            isTurnHandled = handleTurn(yx[0], yx[1]);
        } while (!isTurnHandled);

    }

    private boolean handleTurn(int y, int x) {
        boolean isTurnHandled = engine.makeMove(y, x);
        System.out.println("isTurnHandled: " + isTurnHandled);
        if  (isTurnHandled) {
//            //Check for win
//            if (engine.checkFor(y, x, 5)) {
//
//            } else {
//                //Check for tesera
//                if (engine.checkFor(y, x, 4)) {
//
//                }
//                //If not tesera, check for tria
//                else if (engine.checkFor(y, x, 3)) {
//
//                }
//                else {
//
//                }
//            }
            boolean isCaptureFound = engine.checkForCapture(y, x); //Should return coords of captured pieces?
            System.out.println("Capture: " + isCaptureFound);
            if (isCaptureFound) {
                if (engine.isPlayerOneTurn()) {
                    engine.setP1Captures(getEngine().getP1Captures() + 1);
                } else {
                    engine.setP2Captures(getEngine().getP2Captures() + 1);
                }
            }

            /*
            left, down, and the tl<->br diagonal return true for captures
              \
              -\
              | \- -- -- --
              |  \
              |   \
              |
            */

            conditionStr = (engine.checkForWin(y, x) || //Checks for win by 5 consecutive stones
                    ((engine.isPlayerOneTurn() ? engine.getP1Captures() : engine.getP2Captures()) >= 5)) ? //Checks for win by 5 captures
                    ((engine.isPlayerOneTurn() ? playerOneName : playerTwoName) + " wins!") :
                    (engine.checkForTesera(y, x)) ? //Checks for tesera
                            ((engine.isPlayerOneTurn() ? playerOneName : playerTwoName) + (" has a tesera")) :
                            (engine.checkForTria(y, x)) ? //Checks for tria
                                    ((engine.isPlayerOneTurn() ? playerOneName : playerTwoName) + (" has a tria")) : "";
            if (conditionStr.toLowerCase().contains("win")) engine.passTurn();
            engine.passTurn();
        }
        return isTurnHandled;
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
