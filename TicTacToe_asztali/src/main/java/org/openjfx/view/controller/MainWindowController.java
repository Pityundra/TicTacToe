package org.openjfx.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {
    @FXML
    public Button pvpButton;
    @FXML
    public Button pveButton;

    public MainWindowController() {

    }

    @FXML
    private void choosePVP() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/TobbJatekosMod.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/tobbjatekosmod.css");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void choosePVE() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/EgyJatekosMod.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/gepellen.css");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
