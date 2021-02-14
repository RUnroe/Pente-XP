package main.controllers;

////////////////////////////
// NOTE:
// Y is vertical, X is horizontal
////////////////////////////


import main.models.Piece;

import java.io.Serializable;
import java.util.Random;

public class Engine implements Serializable {

    private Piece[][] board;
    private int turn = 0, players;
    private final boolean[] AIArray;
    private byte[] captures = new byte[]{0,0,0,0};

    public Engine(int players, Boolean isP2Ai, Boolean isP3Ai, Boolean isP4Ai) {
        this.players = players;
        AIArray = new boolean[]{false, isP2Ai, isP3Ai, isP4Ai};
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
    }

    public boolean isValidMove(int y, int x) {
        return (y > -1 && y < 19 &&
                x > -1 && x < 19 &&
                board[y][x] == Piece.EMPTY);
    }

    public boolean makeMove(int y, int x) {
        boolean isValidMove = isValidMove(y, x);
        if (isValidMove) {
            switch(turn % players){
                case(0):
                    board[y][x] = Piece.WHITE;
                    break;
                case(1):
                    board[y][x] = Piece.BLACK;
                    break;
                case(2):
                    board[y][x] = Piece.RED;
                    break;
                case(3):
                    board[y][x] = Piece.BLUE;
                    break;

            }
        }
            return isValidMove;
    }

    public void passTurn() {
        turn++;
    }

    public boolean checkForCapture(int y, int x) {
        boolean isCapture = false;
        Piece color = board[y][x];
        boolean p1Turn;

        p1Turn = (color == Piece.WHITE);

        //Checks horizontally
        if (x > 2) {
            if (board[y][x - 1] != color && board[y][x - 2] != color && board[y][x - 3] == color) {
                captures[turn % players]++;
                board[y][x - 1] = Piece.EMPTY;
                board[y][x - 2] = Piece.EMPTY;
                isCapture = true;
            }
        }
        if (x < 16) {
            if (board[y][x + 1] != color && board[y][x + 2] != color && board[y][x + 3] == color) {
                captures[turn % players]++;
                board[y][x + 1] = Piece.EMPTY;
                board[y][x + 2] = Piece.EMPTY;
                isCapture = true;
            }
        }
        //Checks Vertically
        if (y > 2) {
            if (board[y - 1][x] != color && board[y - 2][x] != color && board[y - 3][x] == color) {
                captures[turn % players]++;
                board[y - 1][x] = Piece.EMPTY;
                board[y - 2][x] = Piece.EMPTY;
                isCapture = true;
            }
        }
        if (y < 16) {
            if (board[y + 1][x] != color && board[y + 2][x] != color && board[y + 3][x] == color) {
                captures[turn % players]++;
                board[y + 1][x] = Piece.EMPTY;
                board[y + 2][x] = Piece.EMPTY;
                isCapture = true;
            }
        }
        //Checks Diagonally
        if (x > 2 && y > 2) {
            if (board[y - 1][x - 1] != color && board[y - 2][x - 2] != color && board[y - 3][x - 3] == color) {
                captures[turn % players]++;
                board[y - 1][x - 1] = Piece.EMPTY;
                board[y - 2][x - 2] = Piece.EMPTY;
                isCapture = true;
            }
        }
        if (x < 16 && y < 16) {
            if (board[y + 1][x + 1] != color && board[y + 2][x + 2] != color && board[y + 3][x + 3] == color) {
                captures[turn % players]++;
                board[y + 1][x + 1] = Piece.EMPTY;
                board[y + 2][x + 2] = Piece.EMPTY;
                isCapture = true;
            }
        }
        if (x > 2 && y < 16) {
            if (board[y + 1][x - 1] != color && board[y + 2][x - 2] != color && board[y + 3][x - 3] == color) {
                captures[turn % players]++;
                board[y + 1][x - 1] = Piece.EMPTY;
                board[y + 2][x - 2] = Piece.EMPTY;
                isCapture = true;
            }
        }
        if (x < 16 && y > 2) {
            if (board[y - 1][x + 1] != color && board[y + -2][x + 2] != color && board[y - 3][x + 3] == color) {
                captures[turn % players]++;
                board[y - 1][x + 1] = Piece.EMPTY;
                board[y - 2][x + 2] = Piece.EMPTY;
                isCapture = true;
            }
        }
        return isCapture;
    }
    private boolean checkFor(int y, int x, int num) {
        //check horizontal
        Piece color = board[y][x];

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
            return true;
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
            return true;
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
        return pieces >= num;
    }

    public boolean checkForWin(int y, int x) {
        if(captures[turn % players] >= 5){
            return true;
        } else {
            return checkFor(y, x, 5);
        }
    }
    public boolean checkForTria(int y, int x) {
        return checkFor(y, x, 3);
    }
    public boolean checkForTesera(int y, int x) {
        return checkFor(y, x, 4);
    }
    public int getCaptures(int player){
        return captures[player];
    }
    public boolean isPlayerAi(int player){
        return AIArray[player];
    }
    public int getPlayerTurn() {
        return (turn % players);
    }
    public int getTurn(){
        return turn;
    }
    public Piece[][] getBoard() {
        return board;
    }
}
