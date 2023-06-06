package org.openjfx.utils;

import javafx.scene.control.Alert;

public class Utils {

    public static void gameOverBotAlert(String playerSign) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Győzelem");
        alert.setHeaderText(null);

        if(playerSign.equals("X")) {
            alert.setContentText("Gratulálok! Legyőzted a Gépet!");
        } else if(playerSign.equals("|")) {
            alert.setContentText("Döntetlen!");
        } else {
            alert.setContentText("Sajnálom, de a Gép győzött!");
        }

        alert.showAndWait();
    }

    public static void gameOverAlert(String playerSign) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Győzelem");
        alert.setHeaderText(null);

        if(playerSign.equals("X")) {
            alert.setContentText("Az X játékosa nyert");
        } else if(playerSign.equals("|")) {
            alert.setContentText("Döntetlen");
        } else {
            alert.setContentText("Az O játékosa nyert");
        }


/*
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(alert.getClass().getResource("css/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("gameOverAlert");
*/
        alert.showAndWait();
    }




}
