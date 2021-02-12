package main.controllers;

import main.models.Piece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {


    @Test
    void checkForWinHorizontalIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for win in x,y

        //Top left corner
//        engine.board = getInitBoard();
        for (int x = 0; x < 5; x++) {
            engine.getBoard()[0][x] = Piece.WHITE;
        }
        assertTrue(engine.checkForWin(0, 4));
        assertTrue(engine.checkForWin(0, 3));
        assertTrue(engine.checkForWin(0, 2));
        assertTrue(engine.checkForWin(0, 1));
        assertTrue(engine.checkForWin(0, 0));

        assertFalse(engine.checkForWin(1, 1));
        assertFalse(engine.checkForWin(1, 0));
        assertFalse(engine.checkForWin(0, 5));


        //Bottom right corner
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int x = 18; x >= 13; x--) {
            engine.getBoard()[18][x] = Piece.BLACK;
        }
        assertTrue(engine.checkForWin(18, 18));
        assertTrue(engine.checkForWin(18, 17));
        assertTrue(engine.checkForWin(18, 16));
        assertTrue(engine.checkForWin(18, 15));
        assertTrue(engine.checkForWin(18, 14));

        assertFalse(engine.checkForWin(18, 13));
        assertFalse(engine.checkForWin(17, 17));
        assertFalse(engine.checkForWin(5, 5));


        //Not a win
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int x = 0; x < 2; x++) {
            engine.getBoard()[0][x] = Piece.WHITE;
        }
        assertFalse(engine.checkForWin(0, 0));
        assertFalse(engine.checkForWin(0, 1));
        assertFalse(engine.checkForWin(0, 2));
    }

    @Test
    void checkForWinVerticalIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for win in x,y

        //Top left corner
//        engine.board = getInitBoard();
        for (int y = 0; y < 5; y++) {
            engine.getBoard()[y][0] = Piece.WHITE;
        }
        assertTrue(engine.checkForWin(4, 0));
        assertTrue(engine.checkForWin(3, 0));
        assertTrue(engine.checkForWin(2, 0));
        assertTrue(engine.checkForWin(1, 0));
        assertTrue(engine.checkForWin(0, 0));

        assertFalse(engine.checkForWin(1, 1));
        assertFalse(engine.checkForWin(0, 1));
        assertFalse(engine.checkForWin(5, 0));


        //Bottom right corner
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int y = 18; y >= 13; y--) {
            engine.getBoard()[y][18] = Piece.BLACK;
        }
        assertTrue(engine.checkForWin(18, 18));
        assertTrue(engine.checkForWin(17, 18));
        assertTrue(engine.checkForWin(16, 18));
        assertTrue(engine.checkForWin(15, 18));
        assertTrue(engine.checkForWin(14, 18));

        assertFalse(engine.checkForWin(13, 18));
        assertFalse(engine.checkForWin(17, 17));
        assertFalse(engine.checkForWin(5, 5));


        //Not a win
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int y = 0; y < 2; y++) {
            engine.getBoard()[y][0] = Piece.WHITE;
        }
        assertFalse(engine.checkForWin(0, 0));
        assertFalse(engine.checkForWin(1, 0));
        assertFalse(engine.checkForWin(2, 0));
    }

    //Top left to bottom right diagonal check
    @Test
    void checkForWinDiagonalTLtoBRIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for win in x,y

        //Top left corner
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int xy = 0; xy < 5; xy++) {
            //xy: x and y values. Same value for diagonal
            engine.getBoard()[xy][xy] = Piece.WHITE;
        }
        assertTrue(engine.checkForWin(0, 0));
        assertTrue(engine.checkForWin(1, 1));
        assertTrue(engine.checkForWin(2, 2));
        assertTrue(engine.checkForWin(3, 3));
        assertTrue(engine.checkForWin(4, 4));

//        assertFalse(engine.checkForWin(5, 5));
//        assertFalse(engine.checkForWin(0, 1));
//        assertFalse(engine.checkForWin(1, 0));


        //Not a win
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int xy = 0; xy < 2; xy++) {
            //xy: x and y values. Same value for diagonal
            engine.getBoard()[xy][xy] = Piece.WHITE;
        }
        assertFalse(engine.checkForWin(0, 0));
        assertFalse(engine.checkForWin(1, 1));
        assertFalse(engine.checkForWin(2, 2));
    }

    //Bottom left to top right diagonal check
    @Test
    void checkForWinDiagonalBLtoTRIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for win in x,y

        //Bottom left corner
//        engine.board = getInitBoard();
        for (int xy = 0; xy < 5; xy++) {
            //y and x values are inversely related
            engine.getBoard()[18 - xy][xy] = Piece.BLACK;
        }
        assertTrue(engine.checkForWin(18, 0));
        assertTrue(engine.checkForWin(17, 1));
        assertTrue(engine.checkForWin(16, 2));
        assertTrue(engine.checkForWin(15, 3));
        assertTrue(engine.checkForWin(14, 4));

        assertFalse(engine.checkForWin(13, 5));
        assertFalse(engine.checkForWin(0, 1));
        assertFalse(engine.checkForWin(1, 0));


        //Not a win
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int xy = 0; xy < 2; xy++) {
            //y and x values are inversely related
            engine.getBoard()[18 - xy][xy] = Piece.BLACK;
        }
        assertFalse(engine.checkForWin(18, 0));
        assertFalse(engine.checkForWin(17, 1));
        assertFalse(engine.checkForWin(16, 2));
    }


    @Test
    void makeMoveIsCorrect() {
        Engine engine = new Engine(false);

        //Validate moves
//        engine.getBoard() = getInitBoard();
//        engine.setPlayerOneTurn(true);
        assertTrue(engine.makeMove(0, 0));
        assertTrue(engine.makeMove(0, 1));
        assertTrue(engine.makeMove(2, 2));

        //Check that player cannot place piece on another piece
        assertFalse(engine.makeMove(0, 0));

        //Verify the board was updated
        assertEquals(Piece.WHITE, engine.getBoard()[0][1]);

        //Verify other board positions are unchanged
        assertEquals(Piece.EMPTY, engine.getBoard()[1][0]);

        //Verify piece cannot be placed out of bounds
        assertFalse(engine.makeMove(-1, -1));
        assertFalse(engine.makeMove(19, 19));
        assertFalse(engine.makeMove(19, 0));
        assertFalse(engine.makeMove(0, -1));


    }

    @Test
    void passTurnIsCorrect() {
        Engine engine = new Engine(false);
        engine.setPlayerOneTurn(true);
        engine.passTurn();
        assertFalse(engine.isPlayerOneTurn());
        engine.passTurn();
        assertTrue(engine.isPlayerOneTurn());
    }

    //
//
//
    @Test
    void checkForCaptureHorizontalIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for capture in x,y

        //Valid capture
//        engine.board = getInitBoard();
        engine.getBoard()[0][0] = Piece.WHITE;
        engine.getBoard()[0][1] = Piece.BLACK;
        engine.getBoard()[0][2] = Piece.BLACK;

        //Place piece
        engine.getBoard()[0][3] = Piece.WHITE;

        assertTrue(engine.checkForCapture(0, 3));
        //Check from other side as well
        assertTrue(engine.checkForCapture(0, 0));


        //Missing enemy piece
//        engine.board = getInitBoard();
        engine = new Engine(false);
        engine.getBoard()[0][0] = Piece.WHITE;
        engine.getBoard()[0][1] = Piece.EMPTY;
        engine.getBoard()[0][2] = Piece.BLACK;

        //Place piece
        engine.getBoard()[0][3] = Piece.WHITE;

        assertFalse(engine.checkForCapture(0, 3));
        //Check from other side
        assertFalse(engine.checkForCapture(0, 0));


        //Missing other player_one piece
//        engine.board = getInitBoard();
        engine = new Engine(false);
        engine.getBoard()[0][0] = Piece.EMPTY;
        engine.getBoard()[0][1] = Piece.BLACK;
        engine.getBoard()[0][2] = Piece.BLACK;

        //Place piece
        engine.getBoard()[0][3] = Piece.WHITE;

        assertFalse(engine.checkForCapture(0, 3));


    }

    @Test
    void checkForCaptureVerticalIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for capture in x,y

        //Valid capture
//        engine.board = getInitBoard();
        engine = new Engine(false);
        engine.getBoard()[0][0] = Piece.WHITE;
        engine.getBoard()[1][0] = Piece.BLACK;
        engine.getBoard()[2][0] = Piece.BLACK;

        //Place piece
        engine.getBoard()[3][0] = Piece.WHITE;

        assertTrue(engine.checkForCapture(3, 0));
        //Check from other side as well
        assertTrue(engine.checkForCapture(0, 0));


        //Missing enemy piece
//        engine.board = getInitBoard();
        engine = new Engine(false);
        engine.getBoard()[0][0] = Piece.WHITE;
        engine.getBoard()[1][0] = Piece.EMPTY;
        engine.getBoard()[2][0] = Piece.BLACK;

        //Place piece
        engine.getBoard()[3][0] = Piece.WHITE;

        assertFalse(engine.checkForCapture(3, 0));
        //Check from other side
        assertFalse(engine.checkForCapture(0, 0));


        //Missing other player_one piece
//        engine.board = getInitBoard();
        engine = new Engine(false);
        engine.getBoard()[0][0] = Piece.EMPTY;
        engine.getBoard()[1][0] = Piece.BLACK;
        engine.getBoard()[2][0] = Piece.BLACK;

        //Place piece
        engine.getBoard()[3][0] = Piece.WHITE;

        assertFalse(engine.checkForCapture(3, 0));


    }

    //Top left to bottom right diagonal check
    @Test
    void checkForCaptureDiagonalTLtoBRIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for capture in x,y

        //Valid capture
//        engine.board = getInitBoard();
        engine = new Engine(false);
        engine.getBoard()[0][0] = Piece.WHITE;
        engine.getBoard()[1][1] = Piece.BLACK;
        engine.getBoard()[2][2] = Piece.BLACK;

        //Place piece
        engine.getBoard()[3][3] = Piece.WHITE;

        assertTrue(engine.checkForCapture(3, 3));
        //Check from other side as well
        assertTrue(engine.checkForCapture(0, 0));


        //Missing enemy piece
//        engine.board = getInitBoard();
        engine = new Engine(false);
        engine.getBoard()[0][0] = Piece.WHITE;
        engine.getBoard()[1][1] = Piece.EMPTY;
        engine.getBoard()[2][2] = Piece.BLACK;

        //Place piece
        engine.getBoard()[3][3] = Piece.WHITE;

        assertFalse(engine.checkForCapture(3, 3));
        //Check from other side
        assertFalse(engine.checkForCapture(0, 0));


        //Missing other player_one piece
//        engine.board = getInitBoard();
        engine = new Engine(false);
        engine.getBoard()[0][0] = Piece.EMPTY;
        engine.getBoard()[1][1] = Piece.BLACK;
        engine.getBoard()[2][2] = Piece.BLACK;

        //Place piece
        engine.getBoard()[3][3] = Piece.WHITE;

        assertFalse(engine.checkForCapture(3, 3));


    }

    //Bottom left to top right diagonal check
    @Test
    void checkForCaptureDiagonalBLtoTRIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for capture in x,y

        //Valid capture
//        engine.board = getInitBoard();
        engine = new Engine(false);
        engine.getBoard()[18][0] = Piece.WHITE;
        engine.getBoard()[17][1] = Piece.BLACK;
        engine.getBoard()[16][2] = Piece.BLACK;

        //Place piece
        engine.getBoard()[15][3] = Piece.WHITE;

        assertTrue(engine.checkForCapture(15, 3));
        //Check from other side as well
        assertTrue(engine.checkForCapture(18, 0));


        //Missing enemy piece
//        engine.board = getInitBoard();
        engine = new Engine(false);
        engine.getBoard()[18][0] = Piece.WHITE;
        engine.getBoard()[17][1] = Piece.EMPTY;
        engine.getBoard()[16][2] = Piece.BLACK;

        //Place piece
        engine.getBoard()[15][3] = Piece.WHITE;

        assertFalse(engine.checkForCapture(15, 3));
        //Check from other side
        assertFalse(engine.checkForCapture(18, 0));


        //Missing other player_one piece
//        engine.board = getInitBoard();
        engine = new Engine(false);
        engine.getBoard()[18][0] = Piece.EMPTY;
        engine.getBoard()[17][1] = Piece.BLACK;
        engine.getBoard()[16][2] = Piece.BLACK;

        //Place piece
        engine.getBoard()[15][3] = Piece.WHITE;

        assertFalse(engine.checkForCapture(15, 3));


    }


    @Test
    void checkForTriaHorizontalIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for tria in x,y

        //Top left corner
//        engine.board = getInitBoard();
        for (int x = 0; x < 3; x++) {
            engine.getBoard()[0][x] = Piece.WHITE;
        }
        assertTrue(engine.checkForTria(0, 2));
        assertTrue(engine.checkForTria(0, 1));
        assertTrue(engine.checkForTria(0, 0));

        assertFalse(engine.checkForTria(1, 1));
        assertFalse(engine.checkForTria(1, 0));
        assertFalse(engine.checkForTria(0, 3));


        //Bottom right corner
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int x = 18; x > 15; x--) {
            engine.getBoard()[18][x] = Piece.BLACK;
        }
        assertTrue(engine.checkForTria(18, 18));
        assertTrue(engine.checkForTria(18, 17));
        assertTrue(engine.checkForTria(18, 16));

        assertFalse(engine.checkForTria(18, 15));
        assertFalse(engine.checkForTria(17, 17));
        assertFalse(engine.checkForTria(5, 5));


        //Not a tria
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int x = 0; x < 2; x++) {
            engine.getBoard()[0][x] = Piece.WHITE;
        }
        assertFalse(engine.checkForTria(0, 0));
        assertFalse(engine.checkForTria(0, 1));
        assertFalse(engine.checkForTria(0, 2));
    }

    @Test
    void checkForTriaVerticalIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for tria in x,y

        //Top left corner
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int y = 0; y < 3; y++) {
            engine.getBoard()[y][0] = Piece.WHITE;
        }
        assertTrue(engine.checkForTria(2, 0));
        assertTrue(engine.checkForTria(1, 0));
        assertTrue(engine.checkForTria(0, 0));

        assertFalse(engine.checkForTria(1, 1));
        assertFalse(engine.checkForTria(0, 1));
        assertFalse(engine.checkForTria(3, 0));


        //Bottom right corner
//        engine.board = getInitBoard();
        for (int y = 18; y > 15; y--) {
            engine.getBoard()[y][18] = Piece.BLACK;
        }
        assertTrue(engine.checkForTria(18, 18));
        assertTrue(engine.checkForTria(17, 18));
        assertTrue(engine.checkForTria(16, 18));

        assertFalse(engine.checkForTria(15, 18));
        assertFalse(engine.checkForTria(17, 17));
        assertFalse(engine.checkForTria(5, 5));


        //Not a tria
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int y = 0; y < 2; y++) {
            engine.getBoard()[y][0] = Piece.WHITE;
        }
        assertFalse(engine.checkForTria(0, 0));
        assertFalse(engine.checkForTria(1, 0));
        assertFalse(engine.checkForTria(2, 0));
    }

    //Top left to bottom right diagonal check
    @Test
    void checkForTriaDiagonalTLtoBRIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for win in x,y

        //Top left corner
//        engine.board = getInitBoard();
        for (int xy = 0; xy < 3; xy++) {
            //xy: x and y values. Same value for diagonal
            engine.getBoard()[xy][xy] = Piece.WHITE;
        }
        assertTrue(engine.checkForTria(0, 0));
        assertTrue(engine.checkForTria(1, 1));
        assertTrue(engine.checkForTria(2, 2));

        assertFalse(engine.checkForTria(3, 3));
        assertFalse(engine.checkForTria(0, 1));
        assertFalse(engine.checkForTria(1, 0));


        //Not a tria
//        engine.board = getInitBoard();
        for (int xy = 0; xy < 2; xy++) {
            //xy: x and y values. Same value for diagonal
            engine.getBoard()[xy][xy] = Piece.WHITE;
        }
        assertFalse(engine.checkForTria(0, 0));
        assertFalse(engine.checkForTria(1, 1));
        assertFalse(engine.checkForTria(2, 2));
    }

    //Bottom left to top right diagonal check
    @Test
    void checkForTriaDiagonalBLtoTRIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for win in x,y

        //Bottom left corner
//        engine.board = getInitBoard();
        for (int xy = 0; xy < 3; xy++) {
            //y and x values are inversely related
            engine.getBoard()[18 - xy][xy] = Piece.BLACK;
        }
        assertTrue(engine.checkForTria(18, 0));
        assertTrue(engine.checkForTria(17, 1));
        assertTrue(engine.checkForTria(16, 2));

        assertFalse(engine.checkForTria(15, 3));
        assertFalse(engine.checkForTria(0, 1));
        assertFalse(engine.checkForTria(1, 0));


        //Not a tria
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int xy = 0; xy < 2; xy++) {
            //y and x values are inversely related
            engine.getBoard()[18 - xy][xy] = Piece.BLACK;
        }
        assertFalse(engine.checkForTria(18, 0));
        assertFalse(engine.checkForTria(17, 1));
        assertFalse(engine.checkForTria(16, 2));
    }


    @Test
    void checkForTeseraHorizontalIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for tesera in x,y

        //Top left corner
//        engine.board = getInitBoard();
        for (int x = 0; x < 4; x++) {
            engine.getBoard()[0][x] = Piece.WHITE;
        }
        assertTrue(engine.checkForTesera(0, 3));
        assertTrue(engine.checkForTesera(0, 2));
        assertTrue(engine.checkForTesera(0, 1));
        assertTrue(engine.checkForTesera(0, 0));

        assertFalse(engine.checkForTesera(1, 1));
        assertFalse(engine.checkForTesera(1, 0));
        assertFalse(engine.checkForTesera(0, 4));


        //Bottom right corner
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int x = 18; x >= 14; x--) {
            engine.getBoard()[18][x] = Piece.BLACK;
        }
        assertTrue(engine.checkForTesera(18, 18));
        assertTrue(engine.checkForTesera(18, 17));
        assertTrue(engine.checkForTesera(18, 16));
        assertTrue(engine.checkForTesera(18, 15));

        assertFalse(engine.checkForTesera(18, 14));
        assertFalse(engine.checkForTesera(17, 17));
        assertFalse(engine.checkForTesera(5, 5));


        //Not a win
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int x = 0; x < 2; x++) {
            engine.getBoard()[0][x] = Piece.WHITE;
        }
        assertFalse(engine.checkForTesera(0, 0));
        assertFalse(engine.checkForTesera(0, 1));
        assertFalse(engine.checkForTesera(0, 2));
    }

    @Test
    void checkForTeseraVerticalIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for tesera in x,y

        //Top left corner
//        engine.b.oard = getInitBoard();
        for (int y = 0; y < 4; y++) {
            engine.getBoard()[y][0] = Piece.WHITE;
        }
        assertTrue(engine.checkForTesera(3, 0));
        assertTrue(engine.checkForTesera(2, 0));
        assertTrue(engine.checkForTesera(1, 0));
        assertTrue(engine.checkForTesera(0, 0));

        assertFalse(engine.checkForTesera(1, 1));
        assertFalse(engine.checkForTesera(0, 1));
        assertFalse(engine.checkForTesera(4, 0));


        //Bottom right corner
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int y = 18; y >= 14; y--) {
            engine.getBoard()[y][18] = Piece.BLACK;
        }
        assertTrue(engine.checkForTesera(18, 18));
        assertTrue(engine.checkForTesera(17, 18));
        assertTrue(engine.checkForTesera(16, 18));
        assertTrue(engine.checkForTesera(15, 18));

        assertFalse(engine.checkForTesera(14, 18));
        assertFalse(engine.checkForTesera(17, 17));
        assertFalse(engine.checkForTesera(5, 5));


        //Not a tesera
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int y = 0; y < 2; y++) {
            engine.getBoard()[y][0] = Piece.WHITE;
        }
        assertFalse(engine.checkForTesera(0, 0));
        assertFalse(engine.checkForTesera(1, 0));
        assertFalse(engine.checkForTesera(2, 0));
    }

    //Top left to bottom right diagonal check
    @Test
    void checkForTeseraDiagonalTLtoBRIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for Tesera in x,y

        //Top left corner
//        engine.board = getInitBoard();
        for (int xy = 0; xy < 4; xy++) {
            //xy: x and y values. Same value for diagonal
            engine.getBoard()[xy][xy] = Piece.WHITE;
        }
        assertTrue(engine.checkForTesera(0, 0));
        assertTrue(engine.checkForTesera(1, 1));
        assertTrue(engine.checkForTesera(2, 2));
        assertTrue(engine.checkForTesera(3, 3));

        assertFalse(engine.checkForTesera(5, 5));
        assertFalse(engine.checkForTesera(4, 4));
        assertFalse(engine.checkForTesera(1, 0));


        //Not a tesera
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int xy = 0; xy < 2; xy++) {
            //xy: x and y values. Same value for diagonal
            engine.getBoard()[xy][xy] = Piece.WHITE;
        }
        assertFalse(engine.checkForTesera(0, 0));
        assertFalse(engine.checkForTesera(1, 1));
        assertFalse(engine.checkForTesera(2, 2));
    }

    //Bottom left to top right diagonal check
    @Test
    void checkForTeseraDiagonalBLtoTRIsCorrect() {
        Engine engine = new Engine(false);
        //Set board state
        //check for win in x,y

        //Bottom left corner
//        engine.board = getInitBoard();
        for (int xy = 0; xy < 4; xy++) {
            //y and x values are inversely related
            engine.getBoard()[18 - xy][xy] = Piece.BLACK;
        }
        assertTrue(engine.checkForTesera(18, 0));
        assertTrue(engine.checkForTesera(17, 1));
        assertTrue(engine.checkForTesera(16, 2));
        assertTrue(engine.checkForTesera(15, 3));

        assertFalse(engine.checkForTesera(13, 5));
        assertFalse(engine.checkForTesera(14, 4));
        assertFalse(engine.checkForTesera(1, 0));


        //Not a tesera
//        engine.board = getInitBoard();
        engine = new Engine(false);
        for (int xy = 0; xy < 2; xy++) {
            //y and x values are inversely related
            engine.getBoard()[18 - xy][xy] = Piece.BLACK;
        }
        assertFalse(engine.checkForTesera(18, 0));
        assertFalse(engine.checkForTesera(17, 1));
        assertFalse(engine.checkForTesera(16, 2));
    }
}