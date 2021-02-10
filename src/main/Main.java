package main;

import main.controllers.Controller;
import main.views.PenteView;

import static javafx.application.Application.launch;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run(args);
    }
}
