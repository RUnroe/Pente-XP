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
//        boolean isValid = false;
//        int[] move;
//        do {
//            move = new int[]{new Random().nextInt(19), new Random().nextInt(19)};
//            isValid = isValidMove(move[0], move[1]);
//        } while (!isValid);
//        return move;
        Piece color;
        switch (turn % players) {
            case 2:
                color = Piece.BLACK;
                break;
            case 3:
                color = Piece.RED;
                break;
            case 4:
                color = Piece.BLUE;
                break;
            default:
                throw new RuntimeException();
        }
        int[][] scores = new int[19][19];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < 19; x++) {
                if (board[y][x] != color && board[y][x] != Piece.EMPTY) {
                    int[] tria = checkForTria(y, x);
                    if (tria[0] != 0) {
                        int[] tesera = checkForTesera(y, x);
                        if (tesera[0] != 0) {
                            for (int i = 0; i < tesera.length; i++) {
                                switch (tesera[i]) {
                                    case (1):
                                        try {
                                            if (board[y][x - 1] == board[y][x]) {
                                                if (board[y][x - 2] == board[y][x]) {
                                                    if (board[y][x - 3] == board[y][x]) {
                                                        if (board[y][x - 4] != Piece.EMPTY) {
                                                            scores[y][x - 4] += 4;
                                                        }
                                                    } else {
                                                        scores[y][x + 2] += 4;
                                                    }
                                                } else {
                                                    scores[y][x + 3] += 4;
                                                }
                                            }
                                        } catch (IndexOutOfBoundsException e){}
                                            break;
                                        case (2):
                                            try {
                                                if (board[y - 1][x] == board[y][x]) {
                                                    if (board[y - 2][x] == board[y][x]) {
                                                        if (board[y - 3][x] == board[y][x]) {
                                                            if (board[y - 4][x] != Piece.EMPTY) {
                                                                scores[y - 4][x] += 4;
                                                            }
                                                        } else {
                                                            scores[y + 2][x] += 4;
                                                        }
                                                    } else {
                                                        scores[y + 3][x] += 4;
                                                    }
                                                }
                                            } catch (IndexOutOfBoundsException e){}
                                            break;
                                            case (3):
                                                try {
                                                    if (board[y - 1][x - 1] == board[y][x]) {
                                                        if (board[y - 2][x - 2] == board[y][x]) {
                                                            if (board[y - 3][x - 3] == board[y][x]) {
                                                                if (board[y - 4][x - 4] != Piece.EMPTY) {
                                                                    scores[y - 4][x - 4] += 4;
                                                                }
                                                            } else {
                                                                scores[y + 2][x + 2] += 4;
                                                            }
                                                        } else {
                                                            scores[y + 3][x + 3] += 4;
                                                        }
                                                    }
                                                } catch (IndexOutOfBoundsException e){}
                                                break;
                                            case (4):
                                                try {
                                                    if (board[y + 1][x - 1] == board[y][x]) {
                                                        if (board[y + 2][x - 2] == board[y][x]) {
                                                            if (board[y + 3][x - 3] == board[y][x]) {
                                                                if (board[y + 4][x - 4] != Piece.EMPTY) {
                                                                    scores[y + 4][x - 4] += 4;
                                                                }
                                                            } else {
                                                                scores[y - 2][x + 2] += 4;
                                                            }
                                                        } else {
                                                            scores[y - 3][x + 3] += 4;
                                                        }
                                                    }
                                                } catch (IndexOutOfBoundsException e){}
                                                break;
                                        }
                                }
                            } else{
                            for (int i = 0; i < tria.length; i++) {
                                switch (tria[i]) {
                                    case (1):
                                        try {
                                            if (board[y][x - 1] == board[y][x]) {
                                                if (board[y][x - 2] == board[y][x]) {
                                                    if (board[y][x - 3] != Piece.EMPTY) {
                                                        scores[y][x - 3] += 3;
                                                    }
                                                } else {
                                                    scores[y][x + 1] += 3;
                                                }
                                            }
                                        } catch (IndexOutOfBoundsException e){}
                                        break;
                                    case (2):
                                        try {
                                            if (board[y - 1][x] == board[y][x]) {
                                                if (board[y - 2][x] == board[y][x]) {
                                                    if (board[y - 3][x] != Piece.EMPTY) {
                                                        scores[y - 3][x] += 3;
                                                    }
                                                } else {
                                                    scores[y + 1][x] += 3;
                                                }
                                            }
                                        } catch (IndexOutOfBoundsException e){}
                                        break;
                                    case (3):
                                        try {
                                            if (board[y - 1][x - 1] == board[y][x]) {
                                                if (board[y - 2][x - 2] == board[y][x]) {
                                                    if (board[y - 3][x - 3] != Piece.EMPTY) {
                                                        scores[y - 3][x - 3] += 3;
                                                    }
                                                } else {
                                                    scores[y + 1][x + 1] += 3;
                                                }
                                            }
                                        } catch (IndexOutOfBoundsException e){}
                                        break;
                                    case (4):
                                        try {
                                            if (board[y + 1][x - 1] == board[y][x]) {
                                                if (board[y + 2][x - 2] == board[y][x]) {
                                                    if (board[y + 3][x - 3] != Piece.EMPTY) {
                                                        scores[y + 3][x - 3] += 3;
                                                    }
                                                } else {
                                                    scores[y - 1][x + 1] += 3;
                                                }
                                            }
                                        } catch (IndexOutOfBoundsException e){}
                                        break;
                                }
                            }
                            }
                        }
                    } else if(board[y][x] == color){
                        int[] capture = checkSpaceForAi(y, x, 3);
                        if(capture[0] != 0){
                            scores[y][x] += 2 * captures.length;
                        }
                    if(checkSpaceForAi(y,x,3)[0] != 0){
                        scores[y][x] = 8675309;
                    } else {
                        int[] tesera = checkSpaceForAi(y,x,1);
                        if(tesera[0] != 0){
                            scores[y][x] += tesera.length * 4;
                        } else {
                            int[] tria = checkSpaceForAi(x,y,0);
                            if(tria[0] != 0){
                                scores[y][x] += 3 * tesera.length;
                            }
                        }
                    }
                    }
                }
            }
        int[] move = new int[]{0,0,0};
        for(int y = 0; y < 19; y++){
            for(int x = 0; x < 19; x++){
                if(scores[y][x] > move[2]){
                    move[0] = y;
                    move[1] = x;
                    move[2] = scores[y][x];
                } else if(scores[y][x] == move[2]){
                    Random r = new Random();
                    if(r.nextBoolean()){
                        move[0] = y;
                        move[1] = x;
                        move[2] = scores[y][x];
                    }
                }
            }
        }
        makeMove(move[0], move[1]);
        return new int[]{move[0], move[1]};
    }

    public int[] checkSpaceForAi(int y, int x, int type){
        Piece original = board[y][x];
        Piece color;
        switch (turn % players) {
            case 2:
                color = Piece.BLACK;
                break;
            case 3:
                color = Piece.RED;
                break;
            case 4:
                color = Piece.BLUE;
                break;
            default:
                throw new RuntimeException();
        }
        board[y][x] = color;
        int[] result = new int[0];
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
            if (board[y][x - 1] != Piece.EMPTY && board[y][x - 2] != Piece.EMPTY && board[y][x - 1] != color && board[y][x - 2] != color && board[y][x - 3] == color) {
                captures[turn % players]++;
                captureList.add(1);
            }
        }
        if (x < 16) {
            if (board[y][x + 1] != Piece.EMPTY && board[y][x + 2] != Piece.EMPTY && board[y][x + 1] != color && board[y][x + 2] != color && board[y][x + 3] == color) {
                captures[turn % players]++;
                captureList.add(2);
            }
        }
        //Checks Vertically
        if (y > 2) {
            if (board[y - 1][x] != Piece.EMPTY && board[y - 2][x] != Piece.EMPTY && board[y - 1][x] != color && board[y - 2][x] != color && board[y - 3][x] == color) {
                captures[turn % players]++;
                captureList.add(3);
            }
        }
        if (y < 16) {
            if (board[y + 1][x] != Piece.EMPTY && board[y + 2][x] != Piece.EMPTY && board[y + 1][x] != color && board[y + 2][x] != color && board[y + 3][x] == color) {
                captures[turn % players]++;
                captureList.add(4);
            }
        }
        //Checks Diagonally
        if (x > 2 && y > 2) {
            if (board[y - 1][x - 1] != Piece.EMPTY && board[y - 2][x - 2] != Piece.EMPTY && board[y - 1][x - 1] != color && board[y - 2][x - 2] != color && board[y - 3][x - 3] == color) {
                captures[turn % players]++;
                captureList.add(5);
            }
        }
        if (x < 16 && y < 16) {
            if (board[y + 1 ][x + 1] != Piece.EMPTY && board[y + 2][x + 2] != Piece.EMPTY && board[y + 1][x + 1] != color && board[y + 2][x + 2] != color && board[y + 3][x + 3] == color) {
                captures[turn % players]++;
                captureList.add(6);
            }
        }
        if (x > 2 && y < 16) {
            if (board[y + 1][x - 1] != Piece.EMPTY && board[y + 2][x - 2] != Piece.EMPTY && board[y + 1][x - 1] != color && board[y + 2][x - 2] != color && board[y + 3][x - 3] == color) {
                captures[turn % players]++;
                captureList.add(7);
            }
        }
        if (x < 16 && y > 2) {
            if (board[y - 1][x + 1] != Piece.EMPTY && board[y - 2][x + 2] != Piece.EMPTY &&board[y - 1][x + 1] != color && board[y + -2][x + 2] != color && board[y - 3][x + 3] == color) {
                captures[turn % players]++;
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
        if(captures[turn % players] >= 5){
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
        return (turn % players);
    }
    public int getTurn(){
        return turn;
    }
    public Piece[][] getBoard() {
        return board;
    }
}
