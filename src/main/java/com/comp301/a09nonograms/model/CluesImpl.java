package com.comp301.a09nonograms.model;

public class CluesImpl implements Clues {
  private final int height;
  private final int width;
  private final int[][] rowClues;
  private final int[][] colClues;

  public CluesImpl(int[][] rowClues, int[][] colClues) {
    if (rowClues == null || colClues == null) {
      throw new IllegalArgumentException();
    }
    this.rowClues = rowClues;
    this.colClues = colClues;
    width = colClues.length;
    height = rowClues.length;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int[] getRowClues(int index) {
    return rowClues[index];
  }

  @Override
  public int[] getColClues(int index) {
    return colClues[index];
  }

  @Override
  public int getRowCluesLength() {
    return rowClues[0].length;
  }

  @Override
  public int getColCluesLength() {
    return colClues[0].length;
  }

  @Override
  public int[][] getRowCluesMatrix() {
    return rowClues;
  }

  @Override
  public int[][] getColCluesMatrix() {
    return colClues;
  }
}
