package com.comp301.a09nonograms.view;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MessageView implements FXComponent {
  @Override
  public Parent render() {
    Alert solved = new Alert(Alert.AlertType.NONE);
    solved.setTitle("Congratulations!");
    solved.setHeaderText(null);
    solved.setContentText("You have solved the puzzle!");
    solved.getButtonTypes().add(ButtonType.CLOSE);
    solved.showAndWait();
    return solved.getDialogPane();
  }
}
