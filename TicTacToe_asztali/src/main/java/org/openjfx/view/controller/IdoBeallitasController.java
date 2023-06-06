package org.openjfx.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class IdoBeallitasController implements Initializable {
    @FXML
    public TextField timerChooseField;

    @FXML
    public Button timerButton;




    public IdoBeallitasController() {
    }

    @FXML
    public void loadGame(ActionEvent event) {
        if(timerChooseField.getText().equals("3") || timerChooseField.getText().equals("4") || timerChooseField.getText().equals("5")
            || timerChooseField.getText().equals("6") || timerChooseField.getText().equals("7") || timerChooseField.getText().equals("8")
              ||  timerChooseField.getText().equals("9") || timerChooseField.getText().equals("10")
            ) {
                TobbJatekosModController.gondolkozasiIdo = Integer.parseInt(timerChooseField.getText());
                Stage stage = (Stage) timerButton.getScene().getWindow();
                stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nem megfelekő érték");
            alert.setHeaderText("Gondolkozási idő beállítása");
            alert.setContentText("3 és 10 között állíthatod be a gondolkodási időt!");


            alert.showAndWait();
            return;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Állítsd be a gondolkozási időt is!");
        alert.setHeaderText("Gondolkozási idő beállítása");
        alert.setContentText("3 és 10 között állíthatod be a gondolkodási időt!");


        alert.showAndWait();
    }

}
