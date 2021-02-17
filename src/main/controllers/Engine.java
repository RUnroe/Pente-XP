package main.controllers;

////////////////////////////
// NOTE:
// Y is vertical, X is horizontal
////////////////////////////


import main.models.Piece;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Engine implements Serializable {

    private String[] playerNames;
    private Piece[][] board;
    private final int numOfPlayers;
    private final byte[] captures = new byte[]{0, 0, 0, 0};

    private final boolean[] AIArray;
    private int turn = 0;

    public Engine(int numOfPlayers, boolean isPlayerTwoAi, boolean isPlayerThreeAi, boolean isPlayerFourAi) {
        this.numOfPlayers = numOfPlayers;
        AIArray = new boolean[]{false, isPlayerTwoAi, isPlayerThreeAi, isPlayerFourAi};
        createBoard();
    }

    private void createBoard() {
        board = new Piece[19][19];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                board[y][x] = Piece.EMPTY;
            }
        }
    }

    public int[] aiTurn() {
        boolean isValid = false;
        int[] move;
        do {
            move = new int[]{new Random().nextInt(19), new Random().nextInt(19)};
            isValid = isValidMove(move[0], move[1]);
        } while (!isValid);
        return move;
//        Piece color;
//        switch(turn % numOfPlayers){
//            case 2:
//                color = Piece.BLACK;
//                break;
//            case 3:
//                color = Piece.RED;
//                break;
//            case 4:
//                color = Piece.BLUE;
//                break;
//            default:
//                throw new RuntimeException();
//        }
//        for(int y = 0; y < board.length; y++){
//            for(int x = 0; x < 19; x++){
//                if(board[y][x] != color && board[y][x] != Piece.EMPTY){
//                    checkForTria(y, x);
//                }
//            }
//        }

    }

    public boolean isValidMove(int y, int x) {
        return (y > -1 && y < 19 &&
                x > -1 && x < 19 &&
                board[y][x] == Piece.EMPTY);
    }

    public boolean makeMove(int y, int x) {
        boolean isValidMove = isValidMove(y, x);
        if (isValidMove) {
            switch (turn % numOfPlayers) {
                case (0):
                    board[y][x] = Piece.WHITE;
                    break;
                case (1):
                    board[y][x] = Piece.BLACK;
                    break;
                case (2):
                    board[y][x] = Piece.RED;
                    break;
                case (3):
                    board[y][x] = Piece.BLUE;
                    break;

            }
        }
            return isValidMove;
    }

    public void passTurn() {
        turn++;
    }

    public void removePieces(int y, int x, int direction){
        switch(direction){
            case 1:
                board[y][x - 1] = Piece.EMPTY;
                board[y][x - 2] = Piece.EMPTY;
                break;
            case 2:
                board[y][x + 1] = Piece.EMPTY;
                board[y][x + 2] = Piece.EMPTY;
                break;
            case 3:
                board[y - 1][x] = Piece.EMPTY;
                board[y - 2][x] = Piece.EMPTY;
                break;
            case 4:
                board[y + 1][x] = Piece.EMPTY;
                board[y + 2][x] = Piece.EMPTY;
                break;
            case 5:
                board[y - 1][x - 1] = Piece.EMPTY;
                board[y - 2][x - 2] = Piece.EMPTY;
                break;
            case 6:
                board[y + 1][x + 1] = Piece.EMPTY;
                board[y + 2][x + 2] = Piece.EMPTY;
                break;
            case 7:
                board[y + 1][x - 1] = Piece.EMPTY;
                board[y + 2][x - 2] = Piece.EMPTY;
                break;
            case 8:
                board[y - 1][x + 1] = Piece.EMPTY;
                board[y - 2][x + 2] = Piece.EMPTY;
                break;
        }
    }

    public int[] checkForCapture(int y, int x) {
        Piece color = board[y][x];
        ArrayList<Integer> captureList = new ArrayList<>();

        //Checks horizontally
        if (x > 2) {
            if (board[y][x - 1] != color && board[y][x - 2] != color && board[y][x - 3] == color) {
                captures[turn % numOfPlayers]++;
                captureList.add(1);
            }
        }
        if (x < 16) {
            if (board[y][x + 1] != color && board[y][x + 2] != color && board[y][x + 3] == color) {
                captures[turn % numOfPlayers]++;
                captureList.add(2);
            }
        }
        //Checks Vertically
        if (y > 2) {
            if (board[y - 1][x] != color && board[y - 2][x] != color && board[y - 3][x] == color) {
                captures[turn % numOfPlayers]++;
                captureList.add(3);
            }
        }
        if (y < 16) {
            if (board[y + 1][x] != color && board[y + 2][x] != color && board[y + 3][x] == color) {
                captures[turn % numOfPlayers]++;
                captureList.add(4);
            }
        }
        //Checks Diagonally
        if (x > 2 && y > 2) {
            if (board[y - 1][x - 1] != color && board[y - 2][x - 2] != color && board[y - 3][x - 3] == color) {
                captures[turn % numOfPlayers]++;
                captureList.add(5);
            }
        }
        if (x < 16 && y < 16) {
            if (board[y + 1][x + 1] != color && board[y + 2][x + 2] != color && board[y + 3][x + 3] == color) {
                captures[turn % numOfPlayers]++;
                captureList.add(6);
            }
        }
        if (x > 2 && y < 16) {
            if (board[y + 1][x - 1] != color && board[y + 2][x - 2] != color && board[y + 3][x - 3] == color) {
                captures[turn % numOfPlayers]++;
                captureList.add(7);
            }
        }
        if (x < 16 && y > 2) {
            if (board[y - 1][x + 1] != color && board[y + -2][x + 2] != color && board[y - 3][x + 3] == color) {
                captures[turn % numOfPlayers]++;
                captureList.add(8);
            }
        }
        if(captureList.size() == 0){
            captureList.add(0);
        }
        int[] capturesArray = new int[captureList.size()];
        for(int i = 0; i < captureList.size(); i++){
            capturesArray[i] = captureList.get(i);
        }
        return capturesArray;
    }
    private int[] checkFor(int y, int x, int num) {
        //check horizontal
        Piece color = board[y][x];
        ArrayList<Integer> checked = new ArrayList<>();

        int pieces = 1;
        try {
            for (int i = x - 1; pieces < 5; i--) {
                if (board[y][i] == color) {
                    pieces++;
                } else {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
        }
        try {
            for (int i = x + 1; pieces < 5; i++) {
                if (board[y][i] == color) {
                    pieces++;
                } else {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
        }
        if (pieces >= num) {
            checked.add(1);
        }
        pieces = 1;
        try {
            for (int i = y - 1; pieces < 5; i--) {
                if (board[i][x] == color) {
                    pieces++;
                } else {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
        }
        try {
            for (int i = y + 1; pieces < num; i++) {
                if (board[i][x] == color) {
                    pieces++;
                } else {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
        }
        if (pieces >= num) {
            checked.add(2);
        }
        pieces = 1;
        try {
            int j = y - 1;
            for (int i = x - 1; pieces < num; i--) {
                if (board[j][i] == color) {
                    pieces++;
                } else {
                    throw new Exception();
                }
                j--;

            }
        } catch (Exception e) {
        }
        try {
            int j = y + 1;
            for (int i = x + 1; pieces < num; i++) {
                if (board[j][i] == color) {
                    pieces++;
                } else {
                    throw new Exception();
                }
                j++;
            }
        } catch (Exception e){}
        if(pieces >= num){
            checked.add(3);
        }
        pieces = 1;
        try {
            int j = y + 1;
            for (int i = x - 1; pieces < num; i--) {
                if (board[j][i] == color) {
                    pieces++;
                } else {
                    throw new Exception();
                }
                j++;

            }
        } catch (Exception e) {
        }
        try {
            int j = y - 1;
            for (int i = x + 1; pieces < num; i++) {
                if (board[j][i] == color) {
                    pieces++;
                } else {
                    throw new Exception();
                }
                j--;
            }
        } catch (Exception e){}
        if(pieces >= num){
            checked.add(4);
        }
        if(checked.size() == 0){
            checked.add(0);
        }
        int[] checkedArray = new int[checked.size()];
        for(int i = 0; i < checked.size(); i++){
            checkedArray[i] = checked.get(i);
        }
        return checkedArray;
    }

    public boolean checkForWin(int y, int x) {
        if(captures[turn % numOfPlayers] >= 5){
            return true;
        } else {
            return checkFor(y, x, 5)[0] != 0;
        }
    }
    public int[] checkForTria(int y, int x) {
        return checkFor(y, x, 3);
    }
    public int[] checkForTesera(int y, int x) {
        return checkFor(y, x, 4);
    }
    public int getCaptures(int player){
        return captures[player];
    }
    public boolean isPlayerAi(int player){
        return AIArray[player];
    }

    public int getPlayerTurn() {
        return (turn % numOfPlayers);
    }

    public int getTurn() {
        return turn;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }
}
