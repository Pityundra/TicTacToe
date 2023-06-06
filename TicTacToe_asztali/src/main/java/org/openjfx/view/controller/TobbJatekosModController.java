package org.openjfx.view.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.openjfx.controller.EredmenyController;
import org.openjfx.utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class TobbJatekosModController implements Initializable{
    @FXML
    public Button button1_1;
    @FXML
    public Button button1_2;
    @FXML
    public Button button1_3;
    @FXML
    public Button button1_4;
    @FXML
    public Button button1_5;
    @FXML
    public Button button2_1;
    @FXML
    public Button button2_2;
    @FXML
    public Button button2_3;
    @FXML
    public Button button2_4;
    @FXML
    public Button button2_5;
    @FXML
    public Button button3_1;
    @FXML
    public Button button3_2;
    @FXML
    public Button button3_3;
    @FXML
    public Button button3_4;
    @FXML
    public Button button3_5;
    @FXML
    public Button button4_1;
    @FXML
    public Button button4_2;
    @FXML
    public Button button4_3;
    @FXML
    public Button button4_4;
    @FXML
    public Button button4_5;
    @FXML
    public Button button5_1;
    @FXML
    public Button button5_2;
    @FXML
    public Button button5_3;
    @FXML
    public Button button5_4;
    @FXML
    public Button button5_5;

    @FXML
    public Label labelP1;
    @FXML
    public Label labelP2;
    @FXML
    public Label labelTimer;

    @FXML
    public Button startButton;

    public boolean wichPlayerTurn; //P1 - true, P2 - false
    public Button[][] buttons = new Button[5][5];
    public static int gondolkozasiIdo;
    public int startTime;

    public String result;

    public TobbJatekosModController() {
    }


    private void idoBeallitasa() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/IdoBeallitas.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void idozito(int localStartTime) {

        startTime = localStartTime;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (startTime == -1) {
                        labelTimer.setText("Játék vége!");
                        timer.purge();
                        timer.cancel();
                        return;
                    } else if(startTime == 0) {
                        labelTimer.setText(String.valueOf(startTime));
                        jatekosValtas();
                    } else {
                        labelTimer.setText(String.valueOf(startTime));
                        startTime--;
                    }
                });}}, 0, 1000);
    }

    private void jatekosValtas() {
        if(wichPlayerTurn) {
            labelP2.setTextFill(Color.web("#990000"));
            labelP2.setFont(new Font(24));

            labelP1.setTextFill(Color.web("#c6e2ff"));
            labelP1.setFont(new Font(20));
            startTime = gondolkozasiIdo;

            wichPlayerTurn = false;
        } else {
            labelP1.setTextFill(Color.web("#990000"));
            labelP1.setFont(new Font(24));

            labelP2.setTextFill(Color.web("#c6e2ff"));
            labelP2.setFont(new Font(20));
            startTime = gondolkozasiIdo;

            wichPlayerTurn = true;
        }
    }

    private void gombBinding() {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                buttons[i][j].disableProperty().bind(labelTimer.textProperty().isEqualTo("")
                        .or(labelTimer.textProperty().isEqualTo("Játék vége!")));
            }
        }
    }


    private boolean szabadHely() {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(buttons[i][j].getText().equals("")) return false;
            }
        }
        return true;
    }

    @FXML
    private void gombvalasztas(Button button) {
        if(wichPlayerTurn) {
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if (button == buttons[i][j]) {
                        result += Integer.toString(i+1) + "_" + Integer.toString(j+1) + "(X);";
                    }
                }
            }
            if(button.getText().equals("")) {
                button.setText("X");
            }else {
                return;
            }
            wichPlayerTurn = !wichPlayerTurn;
            if(szabadHely()) {
                result += "{Döntetlen}";
                EredmenyController.getInstance().addResult(result);
                result = "";
                startTime = -1;
                idozito(startTime);
                Utils.gameOverAlert("|");
                labelP1.setTextFill(Color.web("#c6e2ff"));
                labelP1.setFont(new Font(20));
                labelP2.setTextFill(Color.web("#c6e2ff"));
                labelP2.setFont(new Font(20));
                wichPlayerTurn = true;
                startButton.setDisable(false);
                gombokUresitese();
            } else if(jatekVegeEllenorzo("X")) {
                result += "{X-nyert}";
                EredmenyController.getInstance().addResult(result);
                result = "";
                startTime = -1;
                idozito(startTime);
                Utils.gameOverAlert("X");
                labelP1.setTextFill(Color.web("#c6e2ff"));
                labelP1.setFont(new Font(20));
                labelP2.setTextFill(Color.web("#c6e2ff"));
                labelP2.setFont(new Font(20));
                wichPlayerTurn = true;
                startButton.setDisable(false);
                gombokUresitese();
            } else {
                startTime = gondolkozasiIdo;
                labelP2.setTextFill(Color.web("#990000"));
                labelP2.setFont(new Font(24));
                labelP1.setTextFill(Color.web("#c6e2ff"));
                labelP1.setFont(new Font(20));
            }
        } else if(!wichPlayerTurn){
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if (button == buttons[i][j]) {
                        result += Integer.toString(i+1) + "_" + Integer.toString(j+1) + "(O);";
                    }
                }
            }
            if(button.getText().equals("")) {
                button.setText("O");
            }else {
                return;
            }
            wichPlayerTurn = !wichPlayerTurn;
            if(szabadHely()) {
                result += "{Döntetlen}";
                EredmenyController.getInstance().addResult(result);
                result = "";
                startTime = -1;
                idozito(startTime);
                Utils.gameOverAlert("|");
                labelP1.setTextFill(Color.web("#c6e2ff"));
                labelP1.setFont(new Font(20));
                labelP2.setTextFill(Color.web("#c6e2ff"));
                labelP2.setFont(new Font(20));
                wichPlayerTurn = true;
                startButton.setDisable(false);
                gombokUresitese();
            } else if(jatekVegeEllenorzo("O")) {
                result += "{O-nyert}";
                EredmenyController.getInstance().addResult(result);
                result = "";
                startTime = -1;
                idozito(startTime);
                Utils.gameOverAlert("O");
                labelP1.setTextFill(Color.web("#c6e2ff"));
                labelP1.setFont(new Font(20));
                labelP2.setTextFill(Color.web("#c6e2ff"));
                labelP2.setFont(new Font(20));
                wichPlayerTurn = true;
                startButton.setDisable(false);
                gombokUresitese();
            } else {
                startTime = gondolkozasiIdo;
                labelP1.setTextFill(Color.web("#990000"));
                labelP1.setFont(new Font(24));
                labelP2.setTextFill(Color.web("#c6e2ff"));
                labelP2.setFont(new Font(20));
            }
        }
    }

    private void gombokBeallitasa() {
        buttons[0][0] = button1_1;
        buttons[0][1] = button1_2;
        buttons[0][2] = button1_3;
        buttons[0][3] = button1_4;
        buttons[0][4] = button1_5;

        buttons[1][0] = button2_1;
        buttons[1][1] = button2_2;
        buttons[1][2] = button2_3;
        buttons[1][3] = button2_4;
        buttons[1][4] = button2_5;

        buttons[2][0] = button3_1;
        buttons[2][1] = button3_2;
        buttons[2][2] = button3_3;
        buttons[2][3] = button3_4;
        buttons[2][4] = button3_5;

        buttons[3][0] = button4_1;
        buttons[3][1] = button4_2;
        buttons[3][2] = button4_3;
        buttons[3][3] = button4_4;
        buttons[3][4] = button4_5;

        buttons[4][0] = button5_1;
        buttons[4][1] = button5_2;
        buttons[4][2] = button5_3;
        buttons[4][3] = button5_4;
        buttons[4][4] = button5_5;
    }

    private void gombokUresitese() {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setFont(new Font(20));
            }
        }
    }


    private boolean jatekVegeEllenorzo(String betu) {
        int szamlalo = 0;

        for (int i = 0; i < 5; i++) {
            if (buttons[i][i].getText().equals(betu)) {
                szamlalo++;
                if(szamlalo == 5) {
                    return true;
                }
            }
        }

        if((button1_5.getText().equals("X") || button1_5.getText().equals("O")) && button1_5.getText().equals(button2_4.getText()) && button1_5.getText().equals(button3_3.getText()) && button1_5.getText().equals(button4_2.getText()) && button1_5.getText().equals(button5_1.getText())) {
            return true;
        }

        for (int i = 0; i < 5; i++) {
            szamlalo = 0;
            for (int j = 0; j < 5; j++) {
                if (buttons[j][i].getText().equals(betu)) {
                    szamlalo++;
                    if(szamlalo == 5) {
                        return true;
                    }
                }
            }
        }


        for (int i = 0; i < 5; i++) {
            szamlalo = 0;
            for (int j = 0; j < 5; j++) {
                if (buttons[i][j].getText().equals(betu)) {
                    szamlalo++;
                    if(szamlalo == 5) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    @FXML
    private void onClickHandler(ActionEvent event) {
        gombvalasztas((Button) event.getTarget());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idoBeallitasa();
        gombokBeallitasa();
        gombokUresitese();
        gombBinding();
    }

    @FXML
    public void startGame() {
        result = "";
        gombokUresitese();
        startButton.setDisable(true);
        labelP1.setTextFill(Color.web("#990000"));
        labelP1.setFont(new Font(24));
        labelP2.setTextFill(Color.web("#c6e2ff"));
        labelP2.setFont(new Font(20));
        wichPlayerTurn = true;
        idozito(gondolkozasiIdo);
    }


}
