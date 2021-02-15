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
    public ToggleGroup playerCount;
    public RadioButton playerCount2;
    public RadioButton playerCount3;
    public RadioButton playerCount4;
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
    public CheckBox player2isAI;
    public CheckBox player3isAI;
    public CheckBox player4isAI;
    public Label lblP4Name;
    public Label lblP3Name;
    public Text player4CaptureCount;
    public Text player3CaptureCount;
    public Text player1CaptureCount;
    public Text player2CaptureCount;
    public TextField PlayerThreeName;
    public TextField PlayerFourName;

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

        
        gameController.setPlayerNames(new String[] {PlayerOneName.getText(), PlayerTwoName.getText(), PlayerThreeName.getText(), PlayerFourName.getText()});
        
        boolean isSecondPlayerAi = playerCount.selectedToggleProperty().get().equals(playerCount3);

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
        int numOfPlayers = playerCount.selectedToggleProperty().get().equals(playerCount2) ? 2 :
                playerCount.selectedToggleProperty().get().equals(playerCount3) ? 3 :
                        playerCount.selectedToggleProperty().get().equals(playerCount4) ? 4 : 2;
        gameController.createGame(numOfPlayers, player2isAI.isSelected(), player3isAI.isSelected(), player4isAI.isSelected());
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
        if (secondaryOutputTxt.getText().toLowerCase().contains("win")) {
            handleWin();
        }
    }

    private void updateOutput() {
        updatePlayerDisplay();
        updateSecondaryOutputBox();


        int previousPlayerTurn = gameController.getEngine().getPlayerTurn() - 1;
        if (previousPlayerTurn < 0) {
            previousPlayerTurn = gameController.getEngine().getNumOfPlayers();
        }
        String previousPlayerName = gameController.getPlayerNames()[previousPlayerTurn];
        String currentPlayerName = gameController.getPlayerNames()[gameController.getEngine().getPlayerTurn()];
        String currentPlayerColor = getPlayerPieceColor(gameController.getEngine().getPlayerTurn());
        outputTxt.setText(previousPlayerName + " made their move. It is now " + currentPlayerName + "'s (" + currentPlayerColor + ") turn!");
//        if (gameController.getEngine().isPlayerOneTurn()) {
//            outputTxt.setText(pTwoName + " made their move. It is now " + pOneName + "'s (white) turn!");
//        } else {
//            outputTxt.setText(pOneName + " made their move. It is now " + pTwoName + "'s (black) turn!");
//        }
    }

    private String getPlayerPieceColor (int playerIndex) {
        switch(playerIndex) {
            case 1: return "Black";
            case 2: return "Red";
            case 3: return "Blue";
            default: return "White";
        }
    }



    private void updatePlayerDisplay() {
        Text[] playerCaptureCountText = new Text[]{player1CaptureCount, player2CaptureCount, player3CaptureCount, player4CaptureCount};
        Label[] playerNameLabels = new Label[]{lblP1Name, lblP2Name, lblP3Name, lblP4Name};
        for(int indexOfPlayer = 0; indexOfPlayer < gameController.getEngine().getNumOfPlayers(); indexOfPlayer++) {
            playerNameLabels[indexOfPlayer].setText(gameController.getPlayerNames()[indexOfPlayer]);

            playerCaptureCountText[indexOfPlayer].setText("Captures: " + gameController.getEngine().getCaptures(indexOfPlayer));
        }

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
