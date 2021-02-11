package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.AccessibleRole;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.models.Piece;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Class linked to fxml objects and methods
public class FxHandler {
    public GridPane gridGame;
    private static final GameController gameController = new GameController();

    public Button PlayGameBtn;
    public Button btnBack;
    public Button InstructionBtn;
    public Button SettingsBtn;
    public TextField PlayerOneName;
    public String pOneName;
    public TextField PlayerTwoName;
    public String pTwoName;
    public RadioButton gameTypePVP;
    public ToggleGroup gameType;
    public RadioButton gameTypePVC;
    public Label lblP1Name;
    public Label lblP2Name;

    //Can't use this with JavaFX
//    private FxHandler() {}

    void changeScene(Stage stage, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GoToGame(ActionEvent actionEvent) {

//        System.out.println(PlayerOneName.getText());

//        pOneName = PlayerOneName.getText().toString();
//        pTwoName = PlayerTwoName.getText().toString();

        Stage stage = (Stage) PlayGameBtn.getScene().getWindow();
        changeScene(stage, "../resources/game.fxml");
        gameController.createGame(false);
//        Button[] buttons = (Button[]) gridGame.getChildren().toArray();
//
//        for (Button button : buttons) {
//            button.setPadding(new Insets(5));
//        }

//        lblP1Name.setText(pOneName);
//        lblP2Name.setText(pTwoName);

    }

    public void GoToInstruction(ActionEvent actionEvent) {
        changeScene((Stage) InstructionBtn.getScene().getWindow(), "../resources/instructions.fxml");
    }

    public void onBackClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) btnBack.getScene().getWindow();

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../resources/penteTemplateTest.fxml")));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Pente-XP");
        stage.show();
    }

    public void UserClick(ActionEvent actionEvent) {

        //Gets the button that was clicked
        Button button = (Button) actionEvent.getSource();
        String btnId = button.getId().toString();

        //Separates string into [<grid>,<y>,<x>] and stores [<y>,<x>]
        String[] pos = btnId.split("grid")[1].split("_");

        int y = Integer.parseInt(pos[0]);
        int x = Integer.parseInt(pos[1]);

        //set color based on which player's turn it is
//        if (gameController.getEngine().isPlayerOneTurn()) {
//            button.setStyle("-fx-background-color: #ffffff; ");
//        } else {
//            button.setStyle("-fx-background-color: #000000; ");
//        }
        //button.setDisable(true);

        button.setBackground(Background.EMPTY);
        System.out.println("Coords: " + x + "x, " + y + "y");

        gameController.userClick(y, x);
        updateBoard();
    }

    public void updateBoard() {
        Piece[][] board = gameController.getEngine().getBoard();
        for (Object object : gridGame.getChildren().toArray()) {
            Button button = (Button) object;
            String btnId = button.getId().toString();
            //Separates string into [<grid>,<y>,<x>] and stores [<y>,<x>]
            String[] pos = btnId.split("grid")[1].split("_");
            int y = Integer.parseInt(pos[0]);
            int x = Integer.parseInt(pos[1]);
            switch (board[y][x]) {
                case EMPTY: button.setStyle("-fx-background-color: transparent; ");
                break;
                case WHITE: button.setStyle("-fx-background-color: #ffffff; ");
                break;
                case BLACK: button.setStyle("-fx-background-color: #000000; ");
                break;
            }
        }
    }

    public void GoToSettings(ActionEvent actionEvent) {
        changeScene((Stage) SettingsBtn.getScene().getWindow(), "../resources/penteTemplateTest.fxml");
    }

    public void onPvCClicked(ActionEvent actionEvent) {
        PlayerTwoName.setText("Computer");
        PlayerTwoName.setEditable(false);
    }

    public void onPvPClicked(ActionEvent actionEvent) {
        PlayerTwoName.setText("Player 2");
        PlayerTwoName.setEditable(true);
    }
}
