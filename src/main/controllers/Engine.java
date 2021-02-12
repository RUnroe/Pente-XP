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

    public Engine(Boolean secondPlayerIsAI) {
        p2IsAI = secondPlayerIsAI;
        isPlayerOneTurn = true;
        createBoard();
    }

    private void createBoard(){
        board = new Piece[19][19];
<<<<<<< Updated upstream
        for(int y = 0; y < board.length; y++) {
            for(int x = 0; x < board[0].length; x++) {
=======
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
>>>>>>> Stashed changes
                board[y][x] = Piece.EMPTY;
            }
        }
    }

    //aiTurn() (get move from AI class. Make move. PassTurn)
<<<<<<< Updated upstream
    public void aiTurn() {

    public boolean validateMove(int y, int x){
        if(board[y][x] != Piece.EMPTY){
            return false;
        } else {
            return true;
        }
=======
    public int[] aiTurn() {
        boolean isValid = false;
        int[] move;
        do {
            move = new int[]{new Random().nextInt(19), new Random().nextInt(19)};
            isValidMove(move[0], move[1]);
        } while (!isValid);
        return move;
    }

    public boolean isValidMove(int y, int x) {
        return (y > -1 && y < 19 &&
                x > -1 && x < 19 &&
                board[y][x] == Piece.EMPTY);
>>>>>>> Stashed changes
    }

    public boolean makeMove(int y, int x){
        if(validateMove(x,y)){
            if (isPlayerOneTurn) {
                board[y][x] = Piece.WHITE;
            } else {
                board[y][x] = Piece.BLACK;
            }
<<<<<<< Updated upstream
            return true;
        } else {
            return false;
=======
>>>>>>> Stashed changes
        }
    }
<<<<<<< Updated upstream
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
=======

    public void passTurn() {
        isPlayerOneTurn = !isPlayerOneTurn();
>>>>>>> Stashed changes
    }

    public boolean checkForCapture(int y, int x) {
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
                if (pTurn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y][x-1] = Piece.EMPTY;
                board[y][x-2] = Piece.EMPTY;
                return true;
            }
        } else if (x < 14) {
            if (board[y][x + 1] == oppColor && board[y][x + 2] == oppColor && board[y][x + 3] == color) {
                if (pTurn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y][x+1] = Piece.EMPTY;
                board[y][x+2] = Piece.EMPTY;
                return true;
            }
        }
        //Checks Vertically
        if (y > 2) {
            if (board[y - 1][x] == oppColor && board[y - 2][x] == oppColor && board[y - 3][x] == color) {
                if (pTurn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y-1][x] = Piece.EMPTY;
                board[y-2][x] = Piece.EMPTY;
                return true;
            }
        } else if (y < 14) {
            if (board[y + 1][x] == oppColor && board[y + 2][x] == oppColor && board[y + 3][x] == color) {
                if (pTurn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y+1][x] = Piece.EMPTY;
                board[y+2][x] = Piece.EMPTY;
                return true;
            }
        }
        //Checks Diagonally
        if (x > 2 && y > 2) {
            if (board[y - 1][x - 1] == oppColor && board[y - 2][x - 2] == oppColor && board[y - 3][x - 3] == color) {
                if (pTurn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y-1][x-1] = Piece.EMPTY;
                board[y-2][x-2] = Piece.EMPTY;
                return true;
            }
        } else if (x < 14 && y < 14) {
            if (board[y + 1][x + 1] == oppColor && board[y + 2][x + 2] == oppColor && board[y + 3][x + 3] == color) {
                if (pTurn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y+1][x+1] = Piece.EMPTY;
                board[y+2][x+2] = Piece.EMPTY;
                return true;
            }
        }
        return false;
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
<<<<<<< Updated upstream
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

    public Boolean isPlayerOneTurn() {
        return isPlayerOneTurn;
    }

    public Boolean getP2IsAI() {
        return p2IsAI;
=======
        } catch (Exception e) {
        }
        return pieces >= num;
    }

    public boolean checkForWin(int y, int x) {
        if (isPlayerOneTurn && p1Caps >= 5) {
            return true;
        } else if (!isPlayerOneTurn && p2Caps >= 5) {
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

    public int getP1Captures() {
        return p1Caps;
    }
    public int getP2Captures() {
        return p2Caps;
    }
    public void setP1Captures(int p1Captures) {
        this.p1Caps = (byte) p1Captures;
    }
    public void setP2Captures(int p2Captures) {
        this.p2Caps = (byte) p2Captures;
    }

    public Boolean isP2Ai() {
        return isP2Ai;
    }
    public Boolean isPlayerOneTurn() {
        return isPlayerOneTurn;
    }
    public Piece[][] getBoard() {
        return board;
    }
    public void setPlayerOneTurn(Boolean playerOneTurn) {
        isPlayerOneTurn = playerOneTurn;
>>>>>>> Stashed changes
    }
}
