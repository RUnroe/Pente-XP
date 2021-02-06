package main.controllers;

import main.models.Piece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    private Piece[][] getInitBoard() {
        Piece[][] board = new Piece[19][19];
        for(int y = 0; y < board.length; y++) {
            for(int x = 0; x < board[0].length; x++) {
                board[y][x] = Piece.EMPTY;
            }
        }
        return board;
    }


    @Test
    void checkForWinHorizontalIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for win in x,y

        //Top left corner
        engine.board = getInitBoard();
        for(int x = 0; x < 5; x++) {
            engine.board[0][x] = Piece.PLAYER_ONE;
        }
        assertEquals(true, engine.checkForWin(0,4));
        assertEquals(true, engine.checkForWin(0,3));
        assertEquals(true, engine.checkForWin(0,2));
        assertEquals(true, engine.checkForWin(0,1));
        assertEquals(true, engine.checkForWin(0,0));

        assertEquals(false, engine.checkForWin(1,1));
        assertEquals(false, engine.checkForWin(1,0));
        assertEquals(false, engine.checkForWin(0,5));


        //Bottom right corner
        engine.board = getInitBoard();
        for(int x = 18; x >= 13; x--) {
            engine.board[18][x] = Piece.PLAYER_TWO;
        }
        assertEquals(true, engine.checkForWin(18,18));
        assertEquals(true, engine.checkForWin(18,17));
        assertEquals(true, engine.checkForWin(18,16));
        assertEquals(true, engine.checkForWin(18,15));
        assertEquals(true, engine.checkForWin(18,14));

        assertEquals(false, engine.checkForWin(18,13));
        assertEquals(false, engine.checkForWin(17,17));
        assertEquals(false, engine.checkForWin(5,5));



        //Not a win
        engine.board = getInitBoard();
        for(int x = 0; x < 2; x++) {
            engine.board[0][x] = Piece.PLAYER_ONE;
        }
        assertEquals(false, engine.checkForWin(0,0));
        assertEquals(false, engine.checkForWin(0,1));
        assertEquals(false, engine.checkForWin(0,2));
    }

    @Test
    void checkForWinVerticalIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for win in x,y

        //Top left corner
        engine.board = getInitBoard();
        for(int y = 0; y < 5; y++) {
            engine.board[y][0] = Piece.PLAYER_ONE;
        }
        assertEquals(true, engine.checkForWin(4,0));
        assertEquals(true, engine.checkForWin(3,0));
        assertEquals(true, engine.checkForWin(2,0));
        assertEquals(true, engine.checkForWin(1,0));
        assertEquals(true, engine.checkForWin(0,0));

        assertEquals(false, engine.checkForWin(1,1));
        assertEquals(false, engine.checkForWin(0,1));
        assertEquals(false, engine.checkForWin(5,0));


        //Bottom right corner
        engine.board = getInitBoard();
        for(int y = 18; y >= 13; y--) {
            engine.board[y][18] = Piece.PLAYER_TWO;
        }
        assertEquals(true, engine.checkForWin(18,18));
        assertEquals(true, engine.checkForWin(17,18));
        assertEquals(true, engine.checkForWin(16,18));
        assertEquals(true, engine.checkForWin(15,18));
        assertEquals(true, engine.checkForWin(14,18));

        assertEquals(false, engine.checkForWin(13,18));
        assertEquals(false, engine.checkForWin(17,17));
        assertEquals(false, engine.checkForWin(5,5));


        //Not a win
        engine.board = getInitBoard();
        for(int y = 0; y < 2; y++) {
            engine.board[y][0] = Piece.PLAYER_ONE;
        }
        assertEquals(false, engine.checkForWin(0,0));
        assertEquals(false, engine.checkForWin(1,0));
        assertEquals(false, engine.checkForWin(2,0));
    }

    //Top left to bottom right diagonal check
    @Test
    void checkForWinDiagonalTLtoBRIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for win in x,y

        //Top left corner
        engine.board = getInitBoard();
        for (int xy = 0; xy < 5; xy++) {
            //xy: x and y values. Same value for diagonal
            engine.board[xy][xy] = Piece.PLAYER_ONE;
        }
        assertEquals(true, engine.checkForWin(0,0));
        assertEquals(true, engine.checkForWin(1,1));
        assertEquals(true, engine.checkForWin(2,2));
        assertEquals(true, engine.checkForWin(3,3));
        assertEquals(true, engine.checkForWin(4,4));

        assertEquals(false, engine.checkForWin(5,5));
        assertEquals(false, engine.checkForWin(0,1));
        assertEquals(false, engine.checkForWin(1,0));


        //Not a win
        engine.board = getInitBoard();
        for (int xy = 0; xy < 2; xy++) {
            //xy: x and y values. Same value for diagonal
            engine.board[xy][xy] = Piece.PLAYER_ONE;
        }
        assertEquals(false, engine.checkForWin(0,0));
        assertEquals(false, engine.checkForWin(1,1));
        assertEquals(false, engine.checkForWin(2,2));
    }

    //Bottom left to top right diagonal check
    @Test
    void checkForWinDiagonalBLtoTRIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for win in x,y

        //Bottom left corner
        engine.board = getInitBoard();
        for (int xy = 0; xy < 5; xy++) {
            //y and x values are inversely related
            engine.board[18-xy][xy] = Piece.PLAYER_TWO;
        }
        assertEquals(true, engine.checkForWin(18,0));
        assertEquals(true, engine.checkForWin(17,1));
        assertEquals(true, engine.checkForWin(16,2));
        assertEquals(true, engine.checkForWin(15,3));
        assertEquals(true, engine.checkForWin(14,4));

        assertEquals(false, engine.checkForWin(13,5));
        assertEquals(false, engine.checkForWin(0,1));
        assertEquals(false, engine.checkForWin(1,0));


        //Not a win
        engine.board = getInitBoard();
        for (int xy = 0; xy < 2; xy++) {
            //y and x values are inversely related
            engine.board[18-xy][xy] = Piece.PLAYER_TWO;
        }
        assertEquals(false, engine.checkForWin(18,0));
        assertEquals(false, engine.checkForWin(17,1));
        assertEquals(false, engine.checkForWin(16,2));
    }



    @Test
    void validateMoveIsCorrect() {

    }

    @Test
    void makeMoveIsCorrect() {

    }

    @Test
    void passTurnIsCorrect() {

    }

    @Test
    void checkForCaptureIsCorrect() {

    }

    @Test
    void checkForTriaHorizontalIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for tria in x,y

        //Top left corner
        engine.board = getInitBoard();
        for(int x = 0; x < 3; x++) {
            engine.board[0][x] = Piece.PLAYER_ONE;
        }
        assertEquals(true, engine.checkForTria(0,2));
        assertEquals(true, engine.checkForTria(0,1));
        assertEquals(true, engine.checkForTria(0,0));

        assertEquals(false, engine.checkForTria(1,1));
        assertEquals(false, engine.checkForTria(1,0));
        assertEquals(false, engine.checkForTria(0,3));


        //Bottom right corner
        engine.board = getInitBoard();
        for(int x = 18; x > 15; x--) {
            engine.board[18][x] = Piece.PLAYER_TWO;
        }
        assertEquals(true, engine.checkForTria(18,18));
        assertEquals(true, engine.checkForTria(18,17));
        assertEquals(true, engine.checkForTria(18,16));

        assertEquals(false, engine.checkForTria(18,15));
        assertEquals(false, engine.checkForTria(17,17));
        assertEquals(false, engine.checkForTria(5,5));


        //Not a tria
        engine.board = getInitBoard();
        for(int x = 0; x < 2; x++) {
            engine.board[0][x] = Piece.PLAYER_ONE;
        }
        assertEquals(false, engine.checkForTria(0,0));
        assertEquals(false, engine.checkForTria(0,1));
        assertEquals(false, engine.checkForTria(0,2));
    }

    @Test
    void checkForTriaVerticalIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for tria in x,y

        //Top left corner
        engine.board = getInitBoard();
        for(int y = 0; y < 3; y++) {
            engine.board[y][0] = Piece.PLAYER_ONE;
        }
        assertEquals(true, engine.checkForTria(2,0));
        assertEquals(true, engine.checkForTria(1,0));
        assertEquals(true, engine.checkForTria(0,0));

        assertEquals(false, engine.checkForTria(1,1));
        assertEquals(false, engine.checkForTria(0,1));
        assertEquals(false, engine.checkForTria(3,0));


        //Bottom right corner
        engine.board = getInitBoard();
        for(int y = 18; y > 15; y--) {
            engine.board[y][18] = Piece.PLAYER_TWO;
        }
        assertEquals(true, engine.checkForTria(18,18));
        assertEquals(true, engine.checkForTria(17,18));
        assertEquals(true, engine.checkForTria(16,18));

        assertEquals(false, engine.checkForTria(15,18));
        assertEquals(false, engine.checkForTria(17,17));
        assertEquals(false, engine.checkForTria(5,5));


        //Not a tria
        engine.board = getInitBoard();
        for(int y = 0; y < 2; y++) {
            engine.board[y][0] = Piece.PLAYER_ONE;
        }
        assertEquals(false, engine.checkForTria(0,0));
        assertEquals(false, engine.checkForTria(1,0));
        assertEquals(false, engine.checkForTria(2,0));
    }

    //Top left to bottom right diagonal check
    @Test
    void checkForTriaDiagonalTLtoBRIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for win in x,y

        //Top left corner
        engine.board = getInitBoard();
        for (int xy = 0; xy < 3; xy++) {
            //xy: x and y values. Same value for diagonal
            engine.board[xy][xy] = Piece.PLAYER_ONE;
        }
        assertEquals(true, engine.checkForTria(0,0));
        assertEquals(true, engine.checkForTria(1,1));
        assertEquals(true, engine.checkForTria(2,2));

        assertEquals(false, engine.checkForTria(3,3));
        assertEquals(false, engine.checkForTria(0,1));
        assertEquals(false, engine.checkForTria(1,0));


        //Not a tria
        engine.board = getInitBoard();
        for (int xy = 0; xy < 2; xy++) {
            //xy: x and y values. Same value for diagonal
            engine.board[xy][xy] = Piece.PLAYER_ONE;
        }
        assertEquals(false, engine.checkForTria(0,0));
        assertEquals(false, engine.checkForTria(1,1));
        assertEquals(false, engine.checkForTria(2,2));
    }

    //Bottom left to top right diagonal check
    @Test
    void checkForTriaDiagonalBLtoTRIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for win in x,y

        //Bottom left corner
        engine.board = getInitBoard();
        for (int xy = 0; xy < 3; xy++) {
            //y and x values are inversely related
            engine.board[18-xy][xy] = Piece.PLAYER_TWO;
        }
        assertEquals(true, engine.checkForTria(18,0));
        assertEquals(true, engine.checkForTria(17,1));
        assertEquals(true, engine.checkForTria(16,2));

        assertEquals(false, engine.checkForTria(15,3));
        assertEquals(false, engine.checkForTria(0,1));
        assertEquals(false, engine.checkForTria(1,0));


        //Not a tria
        engine.board = getInitBoard();
        for (int xy = 0; xy < 2; xy++) {
            //y and x values are inversely related
            engine.board[18-xy][xy] = Piece.PLAYER_TWO;
        }
        assertEquals(false, engine.checkForTria(18,0));
        assertEquals(false, engine.checkForTria(17,1));
        assertEquals(false, engine.checkForTria(16,2));
    }





    @Test
    void checkForTeseraHorizontalIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for tesera in x,y

        //Top left corner
        engine.board = getInitBoard();
        for(int x = 0; x < 4; x++) {
            engine.board[0][x] = Piece.PLAYER_ONE;
        }
        assertEquals(true, engine.checkForTesera(0,3));
        assertEquals(true, engine.checkForTesera(0,2));
        assertEquals(true, engine.checkForTesera(0,1));
        assertEquals(true, engine.checkForTesera(0,0));

        assertEquals(false, engine.checkForTesera(1,1));
        assertEquals(false, engine.checkForTesera(1,0));
        assertEquals(false, engine.checkForTesera(0,4));


        //Bottom right corner
        engine.board = getInitBoard();
        for(int x = 18; x >= 14; x--) {
            engine.board[18][x] = Piece.PLAYER_TWO;
        }
        assertEquals(true, engine.checkForTesera(18,18));
        assertEquals(true, engine.checkForTesera(18,17));
        assertEquals(true, engine.checkForTesera(18,16));
        assertEquals(true, engine.checkForTesera(18,15));

        assertEquals(false, engine.checkForTesera(18,14));
        assertEquals(false, engine.checkForTesera(17,17));
        assertEquals(false, engine.checkForTesera(5,5));



        //Not a win
        engine.board = getInitBoard();
        for(int x = 0; x < 2; x++) {
            engine.board[0][x] = Piece.PLAYER_ONE;
        }
        assertEquals(false, engine.checkForTesera(0,0));
        assertEquals(false, engine.checkForTesera(0,1));
        assertEquals(false, engine.checkForTesera(0,2));
    }

    @Test
    void checkForTeseraVerticalIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for tesera in x,y

        //Top left corner
        engine.board = getInitBoard();
        for(int y = 0; y < 4; y++) {
            engine.board[y][0] = Piece.PLAYER_ONE;
        }
        assertEquals(true, engine.checkForTesera(3,0));
        assertEquals(true, engine.checkForTesera(2,0));
        assertEquals(true, engine.checkForTesera(1,0));
        assertEquals(true, engine.checkForTesera(0,0));

        assertEquals(false, engine.checkForTesera(1,1));
        assertEquals(false, engine.checkForTesera(0,1));
        assertEquals(false, engine.checkForTesera(4,0));


        //Bottom right corner
        engine.board = getInitBoard();
        for(int y = 18; y >= 14; y--) {
            engine.board[y][18] = Piece.PLAYER_TWO;
        }
        assertEquals(true, engine.checkForTesera(18,18));
        assertEquals(true, engine.checkForTesera(17,18));
        assertEquals(true, engine.checkForTesera(16,18));
        assertEquals(true, engine.checkForTesera(15,18));

        assertEquals(false, engine.checkForTesera(14,18));
        assertEquals(false, engine.checkForTesera(17,17));
        assertEquals(false, engine.checkForTesera(5,5));


        //Not a tesera
        engine.board = getInitBoard();
        for(int y = 0; y < 2; y++) {
            engine.board[y][0] = Piece.PLAYER_ONE;
        }
        assertEquals(false, engine.checkForTesera(0,0));
        assertEquals(false, engine.checkForTesera(1,0));
        assertEquals(false, engine.checkForTesera(2,0));
    }

    //Top left to bottom right diagonal check
    @Test
    void checkForTeseraDiagonalTLtoBRIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for Tesera in x,y

        //Top left corner
        engine.board = getInitBoard();
        for (int xy = 0; xy < 4; xy++) {
            //xy: x and y values. Same value for diagonal
            engine.board[xy][xy] = Piece.PLAYER_ONE;
        }
        assertEquals(true, engine.checkForTesera(0,0));
        assertEquals(true, engine.checkForTesera(1,1));
        assertEquals(true, engine.checkForTesera(2,2));
        assertEquals(true, engine.checkForTesera(3,3));

        assertEquals(false, engine.checkForTesera(5,5));
        assertEquals(false, engine.checkForTesera(4,4));
        assertEquals(false, engine.checkForTesera(1,0));


        //Not a tesera
        engine.board = getInitBoard();
        for (int xy = 0; xy < 2; xy++) {
            //xy: x and y values. Same value for diagonal
            engine.board[xy][xy] = Piece.PLAYER_ONE;
        }
        assertEquals(false, engine.checkForTesera(0,0));
        assertEquals(false, engine.checkForTesera(1,1));
        assertEquals(false, engine.checkForTesera(2,2));
    }

    //Bottom left to top right diagonal check
    @Test
    void checkForTeseraDiagonalBLtoTRIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for win in x,y

        //Bottom left corner
        engine.board = getInitBoard();
        for (int xy = 0; xy < 4; xy++) {
            //y and x values are inversely related
            engine.board[18-xy][xy] = Piece.PLAYER_TWO;
        }
        assertEquals(true, engine.checkForTesera(18,0));
        assertEquals(true, engine.checkForTesera(17,1));
        assertEquals(true, engine.checkForTesera(16,2));
        assertEquals(true, engine.checkForTesera(15,3));

        assertEquals(false, engine.checkForTesera(13,5));
        assertEquals(false, engine.checkForTesera(14,4));
        assertEquals(false, engine.checkForTesera(1,0));


        //Not a tesera
        engine.board = getInitBoard();
        for (int xy = 0; xy < 2; xy++) {
            //y and x values are inversely related
            engine.board[18-xy][xy] = Piece.PLAYER_TWO;
        }
        assertEquals(false, engine.checkForTesera(18,0));
        assertEquals(false, engine.checkForTesera(17,1));
        assertEquals(false, engine.checkForTesera(16,2));
    }
}