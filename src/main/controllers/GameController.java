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
    private boolean isWin;


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
            boolean isCaptureFound = engine.checkForCapture(y, x); //Should return coords of captured pieces?
            System.out.println("Capture: " + isCaptureFound);

            String currentPlayerName = (engine.isPlayerOneTurn() ? playerOneName : playerTwoName);
            setWin(engine.checkForWin(y, x));
            if (isWin()) {
                conditionStr = currentPlayerName + " wins!";
            } else if (engine.checkForTesera(y, x)) {
                conditionStr = currentPlayerName + (" has made a tesera");
            } else if (engine.checkForTria(y, x)) {
                conditionStr = currentPlayerName + (" has made a tria");
            }

            engine.setTurnCounter(engine.getTurnCounter() + 1);
            if (!isWin()) engine.passTurn();
            System.out.println(isWin());

        }
        return isTurnHandled;
    }


    public Engine getEngine() {
        return engine;
    }

    public void createGame(Boolean secondPlayerIsAI) {
        engine = new Engine(secondPlayerIsAI);
        setWin(false);
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }


    //PenteView view

    //userClick(x, y) (call all engine methods) (what happened in turn. Display on gui)
    //                  (check for tria and tesera. Display in separate part of GUI)


    //setup()
}
