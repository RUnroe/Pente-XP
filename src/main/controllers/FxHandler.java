package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.models.Piece;

import java.io.IOException;
import java.util.Objects;

//Class linked to fxml objects and methods
public class FxHandler {
    public GridPane gridGame;
    private static final GameController gameController = new GameController();

    public Button PlayGameBtn;
    public Button btnBack;
    public Button InstructionBtn;
    public Button SettingsBtn;
    public TextField PlayerOneName;
    public TextField PlayerTwoName;
    public RadioButton gameTypePVP;
    public ToggleGroup gameType;
    public RadioButton gameTypePVC;
    public Label lblP1Name;
    public Label lblP2Name;
    public Text outputTxt;
    public Text secondaryOutputTxt;
    public Button quitGameBtn;
    public Text playerOneCaptureCount;
    public Text playerTwoCaptureCount;

    static Scene settingsScene;
    static Scene instructionsScene;
    static Scene gameScene;
    public Button btnGameToHelp;
    public Button btnHelpToGame;

    public static Scene getSettingsScene() {
        return settingsScene;
    }

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

    private Scene createScene(String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
        return new Scene(root);
    }

    public void changeScene(Stage stage, Scene scene) {
        stage.setScene(scene);
    }

    public void attemptToPreloadScenes() {
        try {
            settingsScene = createScene("../resources/settings.fxml");
            gameScene = createScene("../resources/game.fxml");
            instructionsScene = createScene("../resources/instructions.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GoToInstruction(ActionEvent actionEvent) {
        changeScene((Stage) InstructionBtn.getScene().getWindow(), "../resources/instructions.fxml");
    }

    public void GoToGame(ActionEvent actionEvent) {

//        System.out.println(PlayerOneName.getText());

        gameController.setPlayerOneName(PlayerOneName.getText());
        gameController.setPlayerTwoName(PlayerTwoName.getText());
        boolean isSecondPlayerAi = gameType.selectedToggleProperty().get().equals(gameTypePVC);

        Stage stage = (Stage) PlayGameBtn.getScene().getWindow();
//        changeScene(stage, "../resources/game.fxml");
//        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
//        pauseTransition.setOnFinished(evt -> updatePlayerNames());
        try {
            gameScene = createScene("../resources/game.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        changeScene(stage, gameScene);
//        Worker worker = g
//        stage.getScene().rootProperty().addListener(new ChangeListener<>() {
//            @Override
//            public void changed(ObservableValue<? extends Parent> observableValue, Parent parent, Parent t1) {
//                updatePlayerNames();
//            }
//        });
        gameController.createGame(isSecondPlayerAi);
        gameController.conditionStr = "Tria/Tesera/Win will be announced here.";
    }

    public void onBackClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) btnBack.getScene().getWindow();

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../resources/settings.fxml")));
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
        String btnId = button.getId();

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

        button.setStyle("-fx-background-color: black;");
        System.out.println("Coords: " + x + "x, " + y + "y");

        gameController.userClick(y, x);
        updateBoard();
        updateOutput();
        checkForWin();
    }

    private void checkForWin() {
//        if (secondaryOutputTxt.getText().toLowerCase().contains("win")) {
//            handleWin();
//        }
        if (gameController.isWin()) {
            handleWin();
        }
    }

    private void updateOutput() {
        updatePlayerNames();

        updatePlayerCaptureCount();
        updateSecondaryOutputBox();


        String pOneName = gameController.getPlayerOneName();
        String pTwoName = gameController.getPlayerTwoName();

        if (gameController.getEngine().getTurnCounter() > 0) {
            if (gameController.getEngine().isPlayerOneTurn()) {
                outputTxt.setText(pTwoName + " made their move. It is now " + pOneName + "'s (white) turn!");
            } else {
                outputTxt.setText(pOneName + " made their move. It is now " + pTwoName + "'s (black) turn!");
            }
        } else {
            outputTxt.setText(pOneName + " (white) must start the game by placing stone on center-most intersection!");
        }
    }

    private void updatePlayerCaptureCount() {
//        playerOneCaptureCount.setText(String.valueOf(gameController.getEngine().getP1Captures()));
//        playerTwoCaptureCount.setText(String.valueOf(gameController.getEngine().getP2Captures()));
        playerOneCaptureCount.setText("Captures: " + gameController.getEngine().getP1Captures());
        playerTwoCaptureCount.setText("Captures: " + gameController.getEngine().getP2Captures());
    }

    private void updatePlayerNames() {
        lblP1Name.setText(gameController.getPlayerOneName());
        lblP2Name.setText(gameController.getPlayerTwoName());
    }


    //    private void updatePlayerCaptureCount() {
//        playerOneCaptureCount.setText(gameController.getEngine().getPlayerOneCaptureCount().toString());
//        playerTwoCaptureCount.setText(gameController.getEngine().getPlayerTwoCaptureCount().toString());
//    }
    private void updateSecondaryOutputBox() {
        secondaryOutputTxt.setText(gameController.conditionStr);
    }

    private void handleWin() {
        gridGame.setDisable(true);
    }

    private void updateBoard() {
        Piece[][] board = gameController.getEngine().getBoard();
        for (Object object : gridGame.getChildren().toArray()) {
            Button button = (Button) object;
            String btnId = button.getId();
            //Separates string into [<grid>,<y>,<x>] and stores [<y>,<x>]
            String[] pos = btnId.split("grid")[1].split("_");
            int y = Integer.parseInt(pos[0]);
            int x = Integer.parseInt(pos[1]);
            switch (board[y][x]) {
                case EMPTY -> button.setStyle("-fx-background-color: transparent; -fx-background-radius: 50%;");
                case WHITE -> button.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 100%;");
                case BLACK -> button.setStyle("-fx-background-color: #000000; -fx-background-radius: 50%;");
            }
        }
    }

    public void onPvCClicked(ActionEvent actionEvent) {
        PlayerTwoName.setText("Computer");
        PlayerTwoName.setEditable(false);
    }

    public void onPvPClicked(ActionEvent actionEvent) {
        PlayerTwoName.setText("Player 2");
        PlayerTwoName.setEditable(true);
    }

    public void GoToSettings(ActionEvent actionEvent) {
//        changeScene((Stage) SettingsBtn.getScene().getWindow(), "../resources/settings.fxml");
        changeScene((Stage) SettingsBtn.getScene().getWindow(), settingsScene);
    }

    public void quitGame(ActionEvent actionEvent) {
//        changeScene((Stage) quitGameBtn.getScene().getWindow(), "../resources/settings.fxml");
        changeScene((Stage) quitGameBtn.getScene().getWindow(), settingsScene);
//        handleExistingNames();
    }

    private void handleExistingNames() {
//        if (gameController.getPlayerOneName() != null) {
//            PlayerOneName.setText(gameController.getPlayerOneName());
//        }
//        if (gameController.getPlayerTwoName() != null) {
//            PlayerTwoName.setText(gameController.getPlayerTwoName());
//        }
    }

    public void onGameToHelpClick(ActionEvent actionEvent) {
        changeScene((Stage) btnGameToHelp.getScene().getWindow(), instructionsScene);
    }

    public void onHelpToGameClick(ActionEvent actionEvent) {
        changeScene((Stage) btnHelpToGame.getScene().getWindow(), gameScene);
    }



    public void saveGame(ActionEvent actionEvent) {
    }
}
