package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class View implements FXComponent {
  private final Controller controller;

  public View(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    layout.setPrefSize(550, 550);
    layout.setAlignment(Pos.CENTER);
    VBox buttonBar = new VBox();
    VBox topSpacer = new VBox();
    topSpacer.setPrefHeight(10);

    // Controls view
    ControlView cv = new ControlView(controller);
    buttonBar.getChildren().add(cv.render());
    buttonBar.setAlignment(Pos.TOP_CENTER);
    VBox.setVgrow(buttonBar, Priority.ALWAYS);

    // Puzzle view
    PuzzleView pv = new PuzzleView(controller);
    VBox botSpacer = new VBox();
    VBox.setVgrow(botSpacer, Priority.ALWAYS);

    layout.getChildren().addAll(topSpacer, buttonBar, pv.render(), botSpacer);
    return layout;
  }
}
