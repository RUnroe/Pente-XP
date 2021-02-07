package main.controllers;

import main.models.Piece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AITest {

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
    void determineMoveIsOnBoardIsCorrect() {
        AI ai = new AI();

        //Test if AI makes a move on the board
        Piece[][] board = getInitBoard();

        //Test 10 times
        for(int j = 0; j < 10; j++) {
            int[] aiMove = ai.determineMove(board);
            int yPos = aiMove[0];
            int xPos = aiMove[1];
            assertTrue(yPos < 19 && yPos >= 0);
            assertTrue(xPos < 19 && xPos >= 0);
        }
    }



    @Test
    void determineMoveOnEmptyBoardIsCorrect() {
        AI ai = new AI();

        //Test if AI makes valid move on empty board
        Piece[][] board = getInitBoard();
        int[] aiMove = ai.determineMove(board);
        int yPos = aiMove[0];
        int xPos = aiMove[1];
        assertEquals(Piece.EMPTY, board[yPos][xPos]);
    }



    @Test
    void determineMoveOnPopulatedBoardIsCorrect() {
        AI ai = new AI();

        //Test if AI makes valid move on populated board
        //Test 10 times
        for(int j = 0; j < 10; j++) {
            Piece[][] board = getInitBoard();
            for (int i = 0; i < 15; i++) {
                int x = ((int) (Math.random() * 19));
                int y = ((int) (Math.random() * 19));
                board[y][x] = Piece.PLAYER_ONE;
            }

            int[] aiMove = ai.determineMove(board);
            int yPos = aiMove[0];
            int xPos = aiMove[1];
            assertEquals(Piece.EMPTY, board[yPos][xPos]);
        }
    }


}