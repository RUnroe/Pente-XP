package main.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameController {


private Engine engine;
    private String[] playerNames;
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
            while(engine.isPlayerAi(engine.getPlayerTurn()) && !isWin()) {
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
        if  (isTurnHandled) {
            int[] captures = engine.checkForCapture(y, x);
            boolean isCaptureFound = !(captures.length == 1 && captures[0] == 0); //Should return coords of captured pieces?
            if(isCaptureFound) {
                for(int i = 0; i < captures.length; i++) {
                    engine.removePieces(y, x, captures[i]);
                }
            }
            String currentPlayerName = playerNames[engine.getPlayerTurn()];
            isWin = engine.checkForWin(y, x);
            if (isWin()) {
                conditionStr = currentPlayerName + " wins!";
            } else if (!(engine.checkForTesera(y, x)[0] == 0)) {
                conditionStr = currentPlayerName + (" has made a tesera");
            } else if (!(engine.checkForTria(y, x)[0] == 0)) {
                conditionStr = currentPlayerName + (" has made a tria");
            }

            if (!isWin()) engine.passTurn();
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
        isWin = false;
        conditionStr = "";
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

}
