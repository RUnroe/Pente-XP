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
    private final int[] captures = new int[]{0, 0, 0, 0};

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

        Piece color = switch (turn % numOfPlayers) {
            case 0 -> Piece.WHITE;
            case 1 -> Piece.BLACK;
            case 2 -> Piece.RED;
            case 3 -> Piece.BLUE;
            default -> throw new IllegalStateException("Unexpected value: " + turn % numOfPlayers);
        };
        int[][] scores = new int[19][19];

        boolean isValid = false;
        int[] move = new int[0];
        for(int y = 0; y < board.length; y++){
            for(int x = 0; x < board[0].length; x++){
                if(board[y][x] == Piece.EMPTY) {
                    for (int i = 0; i < 4; i++) {
                        if (checkSpaceForAi(y, x, i)[0] != 0) {
                            return new int[]{y, x};
                        }
                    }
                }
            }
            do {
                move = new int[]{new Random().nextInt(19), new Random().nextInt(19)};
                isValid = isValidMove(move[0], move[1]);
            } while (!isValid);

        }
        return move;

    }

    public int[] checkSpaceForAi(int y, int x, int type){
        Piece original = board[y][x];
        Piece color;
        switch (turn % numOfPlayers) {
            case 1:
                color = Piece.BLACK;
                break;
            case 2:
                color = Piece.RED;
                break;
            case 3:
                color = Piece.BLUE;
                break;
            default:
                throw new RuntimeException();
        }
        board[y][x] = color;
        int[] result = new int[]{0};
        switch(type){
            case(0):
                result = checkForTria(y, x);
                break;
            case(1):
                result = checkForTesera(y, x);
                break;
            case(2):
                if(checkForWin(y, x)){
                    result = new int[]{1};
                }
                break;
            case(3):
                result = checkForCapture(y, x);
                break;
            default:
                throw new RuntimeException();
        }
        board[y][x] = original;
        return result;
    }
    public boolean isValidMove(int y, int x) {
        boolean isValidMove;
        if (turn == 0) {
            isValidMove = (y == 9 && x == 9);
        } else {
            isValidMove = (y > -1 && y < 19 &&
                    x > -1 && x < 19 &&
                    board[y][x] == Piece.EMPTY);
        }
        return isValidMove;
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
                captures[turn % numOfPlayers]++;
                board[y][x - 1] = Piece.EMPTY;
                board[y][x - 2] = Piece.EMPTY;
                break;
            case 2:
                captures[turn % numOfPlayers]++;
                board[y][x + 1] = Piece.EMPTY;
                board[y][x + 2] = Piece.EMPTY;
                break;
            case 3:
                captures[turn % numOfPlayers]++;
                board[y - 1][x] = Piece.EMPTY;
                board[y - 2][x] = Piece.EMPTY;
                break;
            case 4:
                captures[turn % numOfPlayers]++;
                board[y + 1][x] = Piece.EMPTY;
                board[y + 2][x] = Piece.EMPTY;
                break;
            case 5:
                captures[turn % numOfPlayers]++;
                board[y - 1][x - 1] = Piece.EMPTY;
                board[y - 2][x - 2] = Piece.EMPTY;
                break;
            case 6:
                captures[turn % numOfPlayers]++;
                board[y + 1][x + 1] = Piece.EMPTY;
                board[y + 2][x + 2] = Piece.EMPTY;
                break;
            case 7:
                captures[turn % numOfPlayers]++;
                board[y + 1][x - 1] = Piece.EMPTY;
                board[y + 2][x - 2] = Piece.EMPTY;
                break;
            case 8:
                captures[turn % numOfPlayers]++;
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
            if (board[y][x - 1] != Piece.EMPTY && board[y][x - 2] != Piece.EMPTY && board[y][x - 1] != color && board[y][x - 2] != color && board[y][x - 3] == color) {
                captureList.add(1);
            }
        }
        if (x < 16) {
            if (board[y][x + 1] != Piece.EMPTY && board[y][x + 2] != Piece.EMPTY && board[y][x + 1] != color && board[y][x + 2] != color && board[y][x + 3] == color) {
                captureList.add(2);
            }
        }
        if(y > 2) {
            if (board[y - 1][x] != Piece.EMPTY && board[y - 2][x] != Piece.EMPTY && board[y - 1][x] != color && board[y - 2][x] != color && board[y - 3][x] == color) {
                captureList.add(3);
            }
        }
        if (y < 16) {
            if (board[y + 1][x] != Piece.EMPTY && board[y + 2][x] != Piece.EMPTY && board[y + 1][x] != color && board[y + 2][x] != color && board[y + 3][x] == color) {
                captureList.add(4);
            }
        }
            //Checks Diagonally
            if (x > 2 && y > 2) {
                if (board[y - 1][x - 1] != Piece.EMPTY && board[y - 2][x - 2] != Piece.EMPTY && board[y - 1][x - 1] != color && board[y - 2][x - 2] != color && board[y - 3][x - 3] == color) {
                    captureList.add(5);
                }
            }
            if (x < 16 && y < 16) {
                if (board[y + 1][x + 1] != Piece.EMPTY && board[y + 2][x + 2] != Piece.EMPTY && board[y + 1][x + 1] != color && board[y + 2][x + 2] != color && board[y + 3][x + 3] == color) {
                    captureList.add(6);
                }
            }
            if (x > 2 && y < 16) {
                if (board[y + 1][x - 1] != Piece.EMPTY && board[y + 2][x - 2] != Piece.EMPTY && board[y + 1][x - 1] != color && board[y + 2][x - 2] != color && board[y + 3][x - 3] == color) {
                    captureList.add(7);
                }
            }
            if (x < 16 && y > 2) {
                if (board[y - 1][x + 1] != Piece.EMPTY && board[y - 2][x + 2] != Piece.EMPTY && board[y - 1][x + 1] != color && board[y + -2][x + 2] != color && board[y - 3][x + 3] == color) {
                    captureList.add(8);
                }
            }
            if (captureList.size() == 0) {
                captureList.add(0);
            }
            int[] capturesArray = new int[captureList.size()];
            for (int i = 0; i < captureList.size(); i++) {
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
