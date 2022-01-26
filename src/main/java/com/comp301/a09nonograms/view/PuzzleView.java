package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PuzzleView implements FXComponent {
  private final Controller controller;
  private final int colClueLen;

  public PuzzleView(Controller controller) {
    this.controller = controller;
    colClueLen = controller.getClues().getColCluesLength();
  }

  @Override
  public Parent render() {
    GridPane grid = createGrid();
    HBox hb = new HBox();
    hb.getChildren().add(createRowClues()); // separate method for row clues
    hb.getChildren().add(grid);
    hb.setAlignment(Pos.CENTER);

    if (controller.isSolved()) {
      MessageView mv = new MessageView();
      mv.render();
    }
    return hb;
  }

  private GridPane createGrid() {
    GridPane grid = new GridPane();
    int height = controller.getClues().getHeight();
    int width = controller.getClues().getWidth();

    // create column clues
    for (int a = 0; a < colClueLen; a++) {
      for (int b = 0; b < width; b++) {
        grid.add(clueSquare(a, b, "col"), b, a);
      }
    }

    // create main grid
    for (int i = colClueLen; i < height + colClueLen; i++) {
      for (int j = 0; j < width; j++) {
        grid.add(square(i - colClueLen, j), j, i);
      }
    }
    return grid;
  }

  private StackPane square(int row, int column) {
    StackPane square = new StackPane();
    Rectangle border = new Rectangle(30, 30);
    Text status = new Text();
    border.setStroke(Color.BLACK);
    if (controller.isShaded(row, column)) {
      border.setFill(Color.AQUAMARINE);
      status.setText("");
    } else if (controller.isEliminated(row, column)) {
      border.setFill(null);
      status.setText("X");
    } else {
      border.setFill(null);
      status.setText("");
    }
    square.getChildren().addAll(border, status);
    square.setOnMouseClicked(
        event -> {
          if (event.getButton() == MouseButton.PRIMARY) {
            if (border.getFill() == Color.AQUAMARINE) {
              border.setFill(null);
            } else {
              border.setFill(Color.AQUAMARINE);
            }
            controller.toggleShaded(row, column);
          } else if (event.getButton() == MouseButton.SECONDARY) {
            if (status.getText().equals("X")) {
              status.setText("");
            } else {
              status.setText("X");
            }
            controller.toggleEliminated(row, column);
          }
        });
    return square;
  }

  private StackPane clueSquare(int row, int column, String flag) {
    StackPane square = new StackPane();
    Rectangle border = new Rectangle(30, 30);
    border.setStroke(Color.BLACK);
    border.setFill(null);
    Text clue = new Text();
    clue.setFont(Font.font(18));
    if (flag.equals("row")) {
      clue.setText(String.valueOf(controller.getClues().getRowClues(column)[row]));
    } else {
      clue.setText(String.valueOf(controller.getClues().getColClues(column)[row]));
    }
    square.getChildren().addAll(border, clue);
    return square;
  }

  private GridPane createRowClues() {
    GridPane grid = new GridPane();

    int rowClueLen = controller.getClues().getRowCluesLength();

    for (int i = 0; i < rowClueLen; i++) {
      for (int j = 0; j < controller.getClues().getHeight() + colClueLen; j++) {
        if (j < colClueLen) {
          Rectangle square = new Rectangle(30, 30);
          square.setStroke(Color.WHITESMOKE);
          square.setFill(null);
          grid.add(square, i, j);
        } else {
          grid.add(clueSquare(i, j - colClueLen, "row"), i, j);
        }
      }
    }
    return grid;
  }
}
