package com.comp301.a09nonograms.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelImpl implements Model {
  private final List<Clues> clues;
  private final List<ModelObserver> observers;
  private CluesImpl puzzle;
  private BoardImpl board;
  private int puzzleIndex;

  public ModelImpl(List<Clues> clues) {
    if (clues == null) {
      throw new IllegalArgumentException();
    }
    this.clues = clues;
    observers = new ArrayList<>();
    setPuzzleIndex(0);
  }

  @Override
  public boolean isShaded(int row, int col) {
    return board.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return board.isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    return board.isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    board.toggleCellShaded(row, col);
    for (ModelObserver ob : observers) {
      ob.update(this);
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    board.toggleCellEliminated(row, col);
    for (ModelObserver ob : observers) {
      ob.update(this);
    }
  }

  @Override
  public void clear() {
    board.clear();
    for (ModelObserver ob : observers) {
      ob.update(this);
    }
  }

  @Override
  public int getWidth() {
    return puzzle.getWidth();
  }

  @Override
  public int getHeight() {
    return puzzle.getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return puzzle.getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    return puzzle.getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return puzzle.getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return puzzle.getColCluesLength();
  }

  @Override
  public int[][] getRowCluesMatrix() {
    return puzzle.getRowCluesMatrix();
  }

  @Override
  public int[][] getColCluesMatrix() {
    return puzzle.getColCluesMatrix();
  }

  @Override
  public int getPuzzleCount() {
    return clues.size();
  }

  @Override
  public int getPuzzleIndex() {
    return puzzleIndex;
  }

  @Override
  public void setPuzzleIndex(int index) {
    if (index < 0) {
      throw new IllegalArgumentException("There are no previous puzzles");
    }
    puzzle =
        new CluesImpl(clues.get(index).getRowCluesMatrix(), clues.get(index).getColCluesMatrix());
    board = new BoardImpl(getHeight(), getWidth());
    puzzleIndex = index;
    for (ModelObserver ob : observers) {
      ob.update(this);
    }
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  @Override
  public boolean isSolved() {
    return rowSolution() && colSolution();
  }

  public boolean rowSolution() {
    // check if all rows have valid solution
    for (int i = 0; i < getHeight(); i++) {
      int shadedCounter = 0;
      int[] rowClues = getRowClues(i);
      List<Integer> tempClues = new ArrayList<>();
      for (int clue : rowClues) {
        if (clue == 0) {
          tempClues.add(0);
        }
      }

      for (int j = 0; j < getWidth(); j++) {
        if (isShaded(i, j)) {
          shadedCounter++;
        } else if (isSpace(i, j) || isEliminated(i, j)) {
          if (shadedCounter != 0) {
            tempClues.add(shadedCounter);
            shadedCounter = 0;
          }
        }
      }

      if (shadedCounter > 0) {
        tempClues.add(shadedCounter);
      }
      // convert List<Integer> -> int[] for clue list
      int[] newClues = tempClues.stream().mapToInt(k -> k).toArray();
      if (!Arrays.equals(rowClues, newClues)) {
        return false;
      }
    }
    return true;
  }

  public boolean colSolution() {
    // check if all columns have valid solution
    for (int i = 0; i < getWidth(); i++) {
      int shadedCounter = 0;
      int[] colClues = getColClues(i);
      List<Integer> tempClues = new ArrayList<>();
      for (int clue : colClues) {
        if (clue == 0) {
          tempClues.add(0);
        }
      }

      for (int j = 0; j < getHeight(); j++) {
        if (isShaded(j, i)) {
          shadedCounter++;
        } else if (isSpace(j, i) || isEliminated(j, i)) {
          if (shadedCounter != 0) {
            tempClues.add(shadedCounter);
            shadedCounter = 0;
          }
        }
      }

      if (shadedCounter > 0) {
        tempClues.add(shadedCounter);
      }
      // convert List<Integer> -> int[] for clue list
      int[] newClues = tempClues.stream().mapToInt(k -> k).toArray();
      if (!Arrays.equals(colClues, newClues)) {
        return false;
      }
    }
    return true;
  }
}
