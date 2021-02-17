package main.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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
    //    private String playerOneName;
//    private String playerTwoName;
    private String[] playerNames;


    //    public boolean isWin;
//    public boolean isTesera;
//    public boolean isTria;
    public String conditionStr = "";

    private boolean isWin = false;



    public void setWin(boolean win) {
        isWin = win;
    }

    public boolean isWin() {
        return isWin;
    }


    public void userClick(int y, int x) {
        if (!engine.isPlayerAi(engine.getPlayerTurn())) {
            handleTurn(y, x);
            while(engine.isPlayerAi(engine.getPlayerTurn())) {
                handleAiTurn();
            }
        }

    }

    private void handleAiTurn() {
        //Gets an array of [<y>, <x>] coordinates using the AI algorithms
        int[] yx;

        boolean isTurnHandled = false;

        //Attempts to handle turn until handle turn returns true
        //Uses same turn handling method as player with AI determined [<y>, <x>] values
        do {
            yx = engine.aiTurn();
            if(engine.isValidMove(yx[0], yx[1])) {
                isTurnHandled = handleTurn(yx[0], yx[1]);
            }
        } while (!isTurnHandled);

    }


    public boolean handleTurn(int y, int x) {
        boolean isTurnHandled = engine.makeMove(y, x);
        System.out.println("isTurnHandled: " + isTurnHandled);
        if (isTurnHandled) {
            int[] capturesFound = engine.checkForCapture(y, x); //Should return coords of captured pieces?
            if(capturesFound[0] != 0){
                for(int i = 0; i < capturesFound.length; i++){
                    engine.removePieces(y,x,capturesFound[i]);
                }
            }
            System.out.println("Capture: " + capturesFound);
            String currentPlayerName = playerNames[engine.getPlayerTurn()];
            isWin = engine.checkForWin(y, x);
            if (isWin()) {
                conditionStr = currentPlayerName + " wins!";
            } else if (!(engine.checkForTesera(y, x)[0] == 0)) {
                conditionStr = currentPlayerName + (" has made a tesera");
            } else if (!(engine.checkForTria(y, x)[0] == 0)) {
                conditionStr = currentPlayerName + (" has made a tria");
            }

            engine.passTurn();
            System.out.println(isWin());

        }
        return isTurnHandled;
    }

    public boolean saveBoard(File file) {
        try {
            file.createNewFile();
            FileOutputStream f = new FileOutputStream(file, false);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(engine);
            o.close();
            f.close();
            System.out.println(file);
            return true;
        } catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    public void loadBoard(File file){
        try {
            FileInputStream f = new FileInputStream(file);
            ObjectInputStream o = new ObjectInputStream(f);
            Engine e = (Engine) o.readObject();
            f.close();
            o.close();
            this.engine = e;
            playerNames = engine.getPlayerNames();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public Engine getEngine() {
        return engine;
    }

    public void createGame(int numOfPlayers, boolean secondPlayerIsAI, boolean thirdPlayerIsAI, boolean fourthPlayerIsAI) {
        engine = new Engine(numOfPlayers, secondPlayerIsAI, thirdPlayerIsAI, fourthPlayerIsAI);
        engine.setPlayerNames(playerNames);
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

}
