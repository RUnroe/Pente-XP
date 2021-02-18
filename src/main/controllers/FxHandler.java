package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.models.Piece;

import java.awt.*;
import java.io.File;
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
    public Label fileName;
    public Button setupGameBtn;
    public Rectangle clickCaptureScreen;


    FileChooser fileChooser = new FileChooser();
    Desktop desktop = Desktop.getDesktop();
    File loadedFile;

    public static Scene getSettingsScene() {
        return settingsScene;
    }



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
        gameController.setPlayerNames(new String[] {PlayerOneName.getText(), PlayerTwoName.getText(), PlayerThreeName.getText(), PlayerFourName.getText()});

        Stage stage = (Stage) PlayGameBtn.getScene().getWindow();

        try {
            gameScene = createScene("../resources/game.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        changeScene(stage, gameScene);

        int numOfPlayers = playerCount.selectedToggleProperty().get().equals(playerCount2) ? 2 :
                playerCount.selectedToggleProperty().get().equals(playerCount3) ? 3 :
                        playerCount.selectedToggleProperty().get().equals(playerCount4) ? 4 : 2;
        gameController.setWin(false);
        gameController.createGame(numOfPlayers, player2isAI.isSelected(), player3isAI.isSelected(), player4isAI.isSelected());

    }

    public void playLoadedGame(ActionEvent actionEvent) {
        gameController.setWin(false);
        gameController.loadBoard(loadedFile);
        Stage stage = (Stage) PlayGameBtn.getScene().getWindow();
        try {
            gameScene = createScene("../resources/game.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        changeScene(stage, gameScene);

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

        if (gameController.getEngine().getTurn() > 0) {
            int previousPlayerTurn = gameController.getEngine().getPlayerTurn() - 1;
            if (previousPlayerTurn < 0) {
                previousPlayerTurn = gameController.getEngine().getNumOfPlayers() - 1;
            }
            String previousPlayerName = gameController.getPlayerNames()[previousPlayerTurn];
            String currentPlayerName = gameController.getPlayerNames()[gameController.getEngine().getPlayerTurn()];
            String currentPlayerColor = getPlayerPieceColor(gameController.getEngine().getPlayerTurn());
            outputTxt.setText(previousPlayerName + " made their move. It is now " + currentPlayerName + "'s (" + currentPlayerColor + ") turn!");
        } else {
            outputTxt.setText(gameController.getPlayerNames()[0] + " (white) must start the game by placing stone on center-most intersection!");
        }
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
        for(int indexOfPlayer = 0; indexOfPlayer < gameController.getEngine().getNumOfPlayers(); indexOfPlayer++) {
            playerCaptureCountText[indexOfPlayer].setText("Captures: " + gameController.getEngine().getCaptures(indexOfPlayer));
        }
    }



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
                case RED -> button.setStyle("-fx-background-color: #ff0000; -fx-background-radius: 100%;");
                case BLUE -> button.setStyle("-fx-background-color: #0000ff; -fx-background-radius: 50%;");
            }
        }
    }


    public void GoToSettings(ActionEvent actionEvent) {
        changeScene((Stage) SettingsBtn.getScene().getWindow(), settingsScene);
    }

    public void quitGame(ActionEvent actionEvent) {
        changeScene((Stage) quitGameBtn.getScene().getWindow(), settingsScene);
    }


    public void onGameToHelpClick(ActionEvent actionEvent) {
        changeScene((Stage) btnGameToHelp.getScene().getWindow(), instructionsScene);
    }

    public void onHelpToGameClick(ActionEvent actionEvent) {
        changeScene((Stage) btnHelpToGame.getScene().getWindow(), gameScene);
    }



    public void saveGame(ActionEvent actionEvent) {
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PXP files (*.pxp)", "*.pxp");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog((Stage) quitGameBtn.getScene().getWindow());

        if (file != null) {
            gameController.saveBoard(file);
        }
    }
    public void findGameFile(ActionEvent actionEvent) {
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PXP files (*.pxp)", "*.pxp");
        fileChooser.getExtensionFilters().add(extFilter);

        loadedFile = fileChooser.showOpenDialog((Stage) PlayGameBtn.getScene().getWindow());
        if (loadedFile != null) {
            openFile(loadedFile);
        }
        try {
            fileName.setText(loadedFile.getName());
        }catch(Exception e) {
            fileName.setText("No file chosen");
        }
    }


    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
        }
    }


    public void changePlayerCount(ActionEvent actionEvent) {
        RadioButton radioBtn = (RadioButton) actionEvent.getSource();
        int numOfPlayers = Integer.parseInt(radioBtn.getId().split("playerCount")[1]);

        switch (numOfPlayers) {
            case 2:
                player3isAI.setDisable(true);
                PlayerThreeName.setDisable(true);
                player4isAI.setDisable(true);
                PlayerFourName.setDisable(true);
                break;
            case 3:
                player3isAI.setDisable(false);
                PlayerThreeName.setDisable(false);
                player4isAI.setDisable(true);
                PlayerFourName.setDisable(true);
                break;
            case 4:
                player3isAI.setDisable(false);
                PlayerThreeName.setDisable(false);
                player4isAI.setDisable(false);
                PlayerFourName.setDisable(false);
                break;
        }
    }

    public void playerIsAICheck(ActionEvent actionEvent) {
        CheckBox checkBox = (CheckBox) actionEvent.getSource();
        int playerNumber = Integer.parseInt(checkBox.getId().split("player")[1].split("isAI")[0]);
        TextField[] playerNames = new TextField[]{PlayerOneName, PlayerTwoName, PlayerThreeName, PlayerFourName};
        if(checkBox.isSelected()) {
            playerNames[playerNumber-1].setEditable(false);
            playerNames[playerNumber-1].setText("Computer " + playerNumber);
        }else {
            playerNames[playerNumber-1].setEditable(true);
            playerNames[playerNumber-1].setText("Player " + playerNumber);
        }

    }


    public void setupGameView(ActionEvent actionEvent) {

        Text[] playerCaptureCountText = new Text[]{player1CaptureCount, player2CaptureCount, player3CaptureCount, player4CaptureCount};
        Label[] playerNameLabels = new Label[]{lblP1Name, lblP2Name, lblP3Name, lblP4Name};
        for(int indexOfPlayer = 0; indexOfPlayer < gameController.getEngine().getNumOfPlayers(); indexOfPlayer++) {
            playerNameLabels[indexOfPlayer].setText(gameController.getPlayerNames()[indexOfPlayer]);

            playerCaptureCountText[indexOfPlayer].setText("Captures: " + gameController.getEngine().getCaptures(indexOfPlayer));
        }
        int maxNumOfPlayers = 4;
        for(int indexOfPlayer = maxNumOfPlayers; indexOfPlayer > gameController.getEngine().getNumOfPlayers(); indexOfPlayer--) {
            playerNameLabels[indexOfPlayer - 1].setVisible(false);
            playerCaptureCountText[indexOfPlayer - 1].setVisible(false);
        }
        updateBoard();
        updateOutput();

        setupGameBtn.setVisible(false);
        clickCaptureScreen.setVisible(false);
    }
}
