package main.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void testHandleTurn() {
        GameController controller = new GameController();
        controller.createGame(2, false, false, false);
        controller.setPlayerNames(new String[] {"Player 1", "Player 2", "Player 3", "Player 4"});
        controller.handleTurn(9,9);

        assertTrue(controller.handleTurn(0,1));
        assertFalse(controller.handleTurn(9,9));

    }

}