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

    public void userClick(int y, int x) {
        if (engine.isPlayerAi(engine.getPlayerTurn())) {
            handleAiTurn();
        } else {
            handleTurn(y, x);
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


    public boolean handleTurn(int y, int x) {
        boolean isTurnHandled = engine.makeMove(y, x);
        System.out.println("isTurnHandled: " + isTurnHandled);
        if  (isTurnHandled) {
            boolean isCaptureFound = engine.checkForCapture(y, x); //Should return coords of captured pieces?
            System.out.println("Capture: " + isCaptureFound);
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

//    public boolean saveBoard(String filename) {
//        try {
//            File file = new File("games/" + filename + ".pxp");
//            file.createNewFile();
//            FileOutputStream f = new FileOutputStream(file, false);
//            ObjectOutputStream o = new ObjectOutputStream(f);
//            o.writeObject(engine);
//            o.close();
//            f.close();
//            return true;
//        } catch(Exception e){
//            return false;
//        }
//    }
//    public Engine loadBoard(String fileName){
//        try {
//            File file = new File("games/" + fileName + ".pxp");
//            FileInputStream f = new FileInputStream(file);
//            ObjectInputStream o = new ObjectInputStream(f);
//            Engine e = (Engine) o.readObject();
//            f.close();
//            o.close();
//            return e;
//        } catch (Exception e){
//            return null;
//        }
//    }
//    public String[] getFileNames(){
//        try {
//           String[] files = new File("games/").list();
//           for(int i = 0; i < files.length; i++){
//               files[i] = files[i].replace(".pxp", "");
//           }
//           return files;
//        } catch(Exception e) {
//            return new String[]{};
//        }
//    }

    public Engine getEngine() {
        return engine;
    }
    public void createGame(Boolean secondPlayerIsAI) {
        engine = new Engine(secondPlayerIsAI, isP3Ai, isP4Ai);
    }


    //PenteView view

    //userClick(x, y) (call all engine methods) (what happened in turn. Display on gui)
    //                  (check for tria and tesera. Display in separate part of GUI)


    //setup()
}
