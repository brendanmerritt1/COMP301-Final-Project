package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControlView implements FXComponent {
  private final Controller controller;

  public ControlView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox composite = new VBox();
    HBox hb = new HBox();
    HBox hb2 = new HBox();
    HBox hbLeft = new HBox();
    HBox hbRight = new HBox();
    hb.setAlignment(Pos.CENTER);
    hb.setSpacing(30);
    hb2.setAlignment(Pos.CENTER);
    hb2.setSpacing(30);
    composite.setAlignment(Pos.CENTER);
    composite.setSpacing(10);

    Button jumpFirst = new Button("\u00AB");
    Button jumpPrev = new Button("\u2039");
    Button jumpNext = new Button("\u203A");
    Button jumpLast = new Button("\u00BB");
    Button puzzleNum = new Button();
    puzzleNum.setText(
        "Puzzle #" + (controller.getPuzzleIndex() + 1) + " of " + controller.getPuzzleCount());
    Button clear = new Button();
    clear.setText("Clear");
    Button random = new Button();
    random.setText("Random puzzle");

    jumpFirst.setOnAction((ActionEvent e) -> controller.firstPuzzle());
    jumpPrev.setOnAction((ActionEvent e) -> controller.prevPuzzle());
    jumpNext.setOnAction((ActionEvent e) -> controller.nextPuzzle());
    jumpLast.setOnAction((ActionEvent e) -> controller.lastPuzzle());
    clear.setOnAction((ActionEvent e) -> controller.clearBoard());
    random.setOnAction((ActionEvent e) -> controller.randPuzzle());

    hbLeft.getChildren().addAll(jumpFirst, jumpPrev);
    hbRight.getChildren().addAll(jumpNext, jumpLast);
    hb.getChildren().addAll(hbLeft, puzzleNum, hbRight);
    hb2.getChildren().addAll(random, clear);
    composite.getChildren().addAll(hb, hb2);
    return composite;
  }
}
