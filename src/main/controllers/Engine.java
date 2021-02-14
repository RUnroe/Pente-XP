package main.controllers;

////////////////////////////
// NOTE:
// Y is vertical, X is horizontal
////////////////////////////


import main.models.Piece;

import java.util.Random;

public class Engine {

    private Piece[][] board;
    private Boolean isPlayerOneTurn;
    private final Boolean isP2Ai;
    private byte p1Caps = 0, p2Caps = 0;
    private int turnCounter = 0;


    public Engine(Boolean secondPlayerIsAI) {
        isP2Ai = secondPlayerIsAI;
        isPlayerOneTurn = true;
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
        boolean isValidMove;
        if (getTurnCounter() == 0) {
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
            if (isPlayerOneTurn) {
                board[y][x] = Piece.WHITE;
            } else {
                board[y][x] = Piece.BLACK;
            }
        }
            return isValidMove;
    }


    public void passTurn() {
        isPlayerOneTurn = !isPlayerOneTurn();
    }

    public boolean checkForCapture(int y, int x) {
        boolean isCapture = false;
        Piece color = board[y][x];
        boolean p1Turn;
        Piece oppColor = null;

        p1Turn = (color == Piece.WHITE);
        oppColor = (p1Turn ? Piece.BLACK : Piece.WHITE);

        //Commented out because player 1 takes white piece.
        //Ternary above solves issue
//        if (color == Piece.BLACK) {
//            oppColor = Piece.WHITE;
//            p1Turn = true;
//        } else {
//            oppColor = Piece.BLACK;
//            p1Turn = false;
//        }

        //Checks horizontally
        if (x > 2) {
            if (board[y][x - 1] == oppColor && board[y][x - 2] == oppColor && board[y][x - 3] == color) {
                if (p1Turn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y][x - 1] = Piece.EMPTY;
                board[y][x - 2] = Piece.EMPTY;
//                return true;
                isCapture = true;
            }
        }
        if (x < 16) {
            if (board[y][x + 1] == oppColor && board[y][x + 2] == oppColor && board[y][x + 3] == color) {
                if (p1Turn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y][x + 1] = Piece.EMPTY;
                board[y][x + 2] = Piece.EMPTY;
//                return true;
                isCapture = true;
            }
        }
        //Checks Vertically
        if (y > 2) {
            if (board[y - 1][x] == oppColor && board[y - 2][x] == oppColor && board[y - 3][x] == color) {
                if (p1Turn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y - 1][x] = Piece.EMPTY;
                board[y - 2][x] = Piece.EMPTY;
//                return true;
                isCapture = true;
            }
        }
        if (y < 16) {
            if (board[y + 1][x] == oppColor && board[y + 2][x] == oppColor && board[y + 3][x] == color) {
                if (p1Turn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y + 1][x] = Piece.EMPTY;
                board[y + 2][x] = Piece.EMPTY;
//                return true;
                isCapture = true;
            }
        }
        //Checks Diagonally
        if (x > 2 && y > 2) {
            if (board[y - 1][x - 1] == oppColor && board[y - 2][x - 2] == oppColor && board[y - 3][x - 3] == color) {
                if (p1Turn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y - 1][x - 1] = Piece.EMPTY;
                board[y - 2][x - 2] = Piece.EMPTY;
//                return true;
                isCapture = true;
            }
        }
        if (x < 16 && y < 16) {
            if (board[y + 1][x + 1] == oppColor && board[y + 2][x + 2] == oppColor && board[y + 3][x + 3] == color) {
                if (p1Turn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y + 1][x + 1] = Piece.EMPTY;
                board[y + 2][x + 2] = Piece.EMPTY;
//                return true;
                isCapture = true;
            }
        }
        if (x > 2 && y < 16) {
            if (board[y + 1][x - 1] == oppColor && board[y + 2][x - 2] == oppColor && board[y + 3][x - 3] == color) {
                if (p1Turn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y + 1][x - 1] = Piece.EMPTY;
                board[y + 2][x - 2] = Piece.EMPTY;
//                return true;
                isCapture = true;
            }
        }
        if (x < 16 && y > 2) {
            if (board[y - 1][x + 1] == oppColor && board[y + -2][x + 2] == oppColor && board[y - 3][x + 3] == color) {
                if (p1Turn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y - 1][x + 1] = Piece.EMPTY;
                board[y - 2][x + 2] = Piece.EMPTY;
//                return true;
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
        if (isPlayerOneTurn && p1Caps >= 5) {
            System.out.println("P1 capture win");
            return true;
        } else if (!isPlayerOneTurn && p2Caps >= 5) {
            System.out.println("P2 capture win");
            return true;
        } else {
            System.out.println("Consecutive win: " + checkFor(y, x, 5));
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
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }
}
