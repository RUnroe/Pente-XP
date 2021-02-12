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
            isValidMove(move[0], move[1]);
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
        }
        if (x < 14) {
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
        }
        if (y < 16) {
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
        if (x > 3 && y > 2) {
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
        }
        if (x < 14 && y < 16) {
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
        if (x > 2 && y < 16) {
            if (board[y + 1][x - 1] == oppColor && board[y + 2][x - 2] == oppColor && board[y + 3][x - 3] == color) {
                if (pTurn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y+1][x-1] = Piece.EMPTY;
                board[y+2][x-2] = Piece.EMPTY;
                return true;
            }
        }
        if (x < 16 && y > 2) {
            if (board[y - 1][x + 1] == oppColor && board[y + -2][x + 2] == oppColor && board[y - 3][x + 3] == color) {
                if (pTurn) {
                    p1Caps++;
                } else {
                    p2Caps++;
                }
                board[y-1][x+1] = Piece.EMPTY;
                board[y-2][x+2] = Piece.EMPTY;
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
        } catch (Exception e){}
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
    }
}
