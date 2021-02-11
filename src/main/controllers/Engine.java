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
    //AI ai
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
    //passTurn() (return string - "It is now player 2's turn") (if player 2s turn and player 2 is an AI, call AI method)
//    public String passTurn(){
//        if(isPlayerOneTurn){
//            isPlayerOneTurn = false;
//            if(p2IsAI){
//                return "It is now the AI's Turn";
//            } else {
//                return "It is now " + p2Name + "'s Turn";
//            }
//        } else {
//            isPlayerOneTurn = true;
//            return "It is now " + p1Name + "'s Turn";
//        }
//    }
    //checkForCapture(y, x) (return boolean)
    public boolean checkForCapture(int x, int y) {
        Piece color = board[y][x];
        Piece oppColor = null;
        if (color == Piece.BLACK) {
            oppColor = Piece.WHITE;
        } else {
            oppColor = Piece.BLACK;
        }

        //Checks horizontally
        if (x > 2) {
            if (board[y][x - 1] == oppColor && board[y][x - 2] == oppColor && board[y][x - 3] == color) {
                return true;
            }
        } else if (x < 14) {
            if (board[y][x + 1] == oppColor && board[y][x + 2] == oppColor && board[y][x + 3] == color) {
                return true;
            }
        }
        //Checks Vertically
        if (y > 2) {
            if (board[y - 1][x] == oppColor && board[y - 2][x] == oppColor && board[y - 3][x] == color) {
                return true;
            }
        } else if (y < 14) {
            if (board[y + 1][x] == oppColor && board[y + 2][x] == oppColor && board[y + 3][x] == color) {
                return true;
            }
        }
        //Checks Horizontally
        if(x > 2 && y > 2){
            if (board[y - 1][x - 1] == oppColor && board[y - 2][x - 2] == oppColor && board[y - 3][x - 3] == color) {
                return true;
            }
        } else if(x < 14 && y < 14){
            if (board[y + 1][x + 1] == oppColor && board[y + 2][x + 2] == oppColor && board[y + 3][x + 3] == color) {
                return true;
            }
        }
        return false;
    }
    //checkForWin(y, x) (return boolean - whether player won or not)
    //endGame() (return string - "Player 1 won the game")

    //checkForTria(y, x) (return boolean)
    //checkForTesera(y, x) (return boolean)


    public Piece[][] getBoard(){
        return board;
    }
}
