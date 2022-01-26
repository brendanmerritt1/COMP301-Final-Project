package com.comp301.a09nonograms.controller;

import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.CluesImpl;
import com.comp301.a09nonograms.model.Model;

import java.util.Random;

public class ControllerImpl implements Controller {
  private final Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public Clues getClues() {
    return new CluesImpl(model.getRowCluesMatrix(), model.getColCluesMatrix());
  }

  @Override
  public boolean isSolved() {
    return model.isSolved();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return model.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return model.isEliminated(row, col);
  }

  @Override
  public void toggleShaded(int row, int col) {
    model.toggleCellShaded(row, col);
  }

  @Override
  public void toggleEliminated(int row, int col) {
    model.toggleCellEliminated(row, col);
  }

  @Override
  public void nextPuzzle() {
    if (model.getPuzzleIndex() + 1 != model.getPuzzleCount()) {
      model.setPuzzleIndex(model.getPuzzleIndex() + 1);
    }
  }

  @Override
  public void prevPuzzle() {
    if (model.getPuzzleIndex() != 0) {
      model.setPuzzleIndex(model.getPuzzleIndex() - 1);
    }
  }

  @Override
  public void firstPuzzle() {
    model.setPuzzleIndex(0);
  }

  @Override
  public void lastPuzzle() {
    model.setPuzzleIndex(model.getPuzzleCount() - 1);
  }

  @Override
  public void randPuzzle() {
    Random rand = new Random();
    int nextPuzzle = rand.nextInt(model.getPuzzleCount());
    while (nextPuzzle == model.getPuzzleIndex()) {
      nextPuzzle = rand.nextInt(model.getPuzzleCount());
    }
    model.setPuzzleIndex(nextPuzzle);
  }

  @Override
  public void clearBoard() {
    model.clear();
  }

  @Override
  public int getPuzzleIndex() {
    return model.getPuzzleIndex();
  }

  @Override
  public int getPuzzleCount() {
    return model.getPuzzleCount();
  }
}
