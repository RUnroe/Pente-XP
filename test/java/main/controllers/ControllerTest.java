package main.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void setupIsCorrect() {
        Controller controller = new Controller();
        controller.setup();

        assertNotNull(controller.engine);
    }

}