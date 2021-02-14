package main.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {

    @Test
    void testHandleTurn() {
        GameController controller = new GameController();
//        controller.createGame(2, false, false, false);
        controller.createGame(false);
        controller.getEngine().makeMove(0, 0);

        assertTrue(controller.handleTurn(0, 1));
        assertFalse(controller.handleTurn(0, 0));

    }

}