package main.controllers;

////////////////////////////
// NOTE:
// Y is vertical, X is horizontal
////////////////////////////


import main.models.Piece;

public class Engine {

    private Piece[][] board;
    private Boolean isPlayerOneTurn;
    private Boolean p2IsAI;
    private byte p1Caps = 0, p2Caps = 0;
    //int turnCount = 0

    public Engine(){
        isPlayerOneTurn = true;
        createBoard();
    }

    private void createBoard(){
        board = new Piece[19][19];
        for(int y = 0; y < board.length; y++) {
            for(int x = 0; x < board[0].length; x++) {
                board[y][x] = Piece.EMPTY;
            }
        }
    }

    //aiTurn() (get move from AI class. Make move. PassTurn)

    public boolean validateMove(int x, int y){
        if(board[y][x] != Piece.EMPTY){
            return false;
        } else {
            return true;
        }
    }

    public boolean makeMove(int x, int y){
        if(validateMove(x,y)){
            if (isPlayerOneTurn) {
                board[y][x] = Piece.WHITE;
            } else {
                board[y][x] = Piece.BLACK;
            }
            return true;
        } else {
            return false;
        }
    }
    public String passTurn(){
        if(isPlayerOneTurn){
            isPlayerOneTurn = false;
            if(p2IsAI){
                return "It is now the AI's Turn";
            } else {
                return "It is now " + Controller.p2Name + "'s Turn";
            }
        } else {
            isPlayerOneTurn = true;
            return "It is now " + Controller.p1Name + "'s Turn";
        }
    }
    public boolean checkForCapture(int x, int y) {
        Piece color = board[y][x];
        boolean pTurn;
        Piece oppColor = null;
        if (color == Piece.BLACK) {
            oppColor = Piece.WHITE;
            pTurn = true;
        } else {
            oppColor = Piece.BLACK;
            pTurn = false;
        }

        //Checks horizontally
        if (x > 2) {
            if (board[y][x - 1] == oppColor && board[y][x - 2] == oppColor && board[y][x - 3] == color) {
                if(pTurn){
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                return true;
            }
        } else if (x < 14) {
            if (board[y][x + 1] == oppColor && board[y][x + 2] == oppColor && board[y][x + 3] == color) {
                if(pTurn){
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                return true;
            }
        }
        //Checks Vertically
        if (y > 2) {
            if (board[y - 1][x] == oppColor && board[y - 2][x] == oppColor && board[y - 3][x] == color) {
                if(pTurn){
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                return true;
            }
        } else if (y < 14) {
            if (board[y + 1][x] == oppColor && board[y + 2][x] == oppColor && board[y + 3][x] == color) {
                if(pTurn){
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                return true;
            }
        }
        //Checks Horizontally
        if(x > 2 && y > 2){
            if (board[y - 1][x - 1] == oppColor && board[y - 2][x - 2] == oppColor && board[y - 3][x - 3] == color) {
                if(pTurn){
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                return true;
            }
        } else if(x < 14 && y < 14){
            if (board[y + 1][x + 1] == oppColor && board[y + 2][x + 2] == oppColor && board[y + 3][x + 3] == color) {
                if(pTurn){
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                return true;
            }
        }
        return false;
    }

    public boolean checkFor(int x, int y, int num) {
     //check horizontal
        Piece color = board[y][x];

        int pieces = 1;
        try{
            for(int i = x - 1; pieces < 5; i--){
                if(board[y][i] == color){
                    pieces++;
                } else {
                    throw new Exception();
                }
            }
        } catch (Exception e){}
        try{
            for(int i = x + 1; pieces < 5; i++){
                if(board[y][i] == color){
                    pieces++;
                } else {
                    throw new Exception();
                }
            }
        } catch (Exception e){}
        if(pieces >= num){
            return true;
        }
        pieces = 1;
        try{
            for(int i = y - 1; pieces < 5; i--){
                if(board[i][x] == color){
                    pieces++;
                } else {
                    throw new Exception();
                }
            }
        } catch (Exception e){}
        try{
            for(int i = y + 1; pieces < num; i++){
                if(board[i][x] == color){
                    pieces++;
                } else {
                    throw new Exception();
                }
            }
        } catch (Exception e){}
        if(pieces >= num){
            return true;
        }
        pieces = 1;
        try{
            int j = y - 1;
            for(int i = x - 1; pieces < num; i--){
                    if (board[j][i] == color) {
                        pieces++;
                    } else {
                        throw new Exception();
                    }
                    j--;

            }
        } catch (Exception e){}
        try{
            int j = y + 1;
            for(int i = x + 1; pieces < num; i++){
                if(board[j][i] == color){
                    pieces++;
                } else {
                    throw new Exception();
                }
                j++;
            }
        } catch (Exception e){}
        if(pieces >= num){
            return true;
        }
        return false;
    }

    //If this class goes static, this method will be responsible for wiping everything
    public String endGame(){
        if(isPlayerOneTurn){
            return Controller.p1Name + " wins!";
        } else {
            return Controller.p2Name + " wins!";
        }
    }


    public Piece[][] getBoard(){
        return board;
    }
}
