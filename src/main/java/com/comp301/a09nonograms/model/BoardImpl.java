package com.comp301.a09nonograms.model;

public class BoardImpl implements Board {
  private final int height;
  private final int width;
  private final cellStatus[][] status;

  public BoardImpl(int height, int width) {
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException();
    }
    this.height = height;
    this.width = width;
    status = new cellStatus[height][width];
    clear();
  }

  @Override
  public boolean isShaded(int row, int col) {
    if (checkValid(row, col)) {
      return status[row][col] == cellStatus.SHADED;
    } else {
      throw new IllegalArgumentException("Value for row and/or column is invalid");
    }
  }

  @Override
  public boolean isEliminated(int row, int col) {
    if (checkValid(row, col)) {
      return status[row][col] == cellStatus.ELIMINATED;
    } else {
      throw new IllegalArgumentException("Value for row and/or column is invalid");
    }
  }

  @Override
  public boolean isSpace(int row, int col) {
    if (checkValid(row, col)) {
      return status[row][col] == cellStatus.SPACE;
    } else {
      throw new IllegalArgumentException("Value for row and/or column is invalid");
    }
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (checkValid(row, col)) {
      if (status[row][col] == cellStatus.SHADED) {
        status[row][col] = cellStatus.SPACE;
      } else {
        status[row][col] = cellStatus.SHADED;
      }
    } else {
      throw new IllegalArgumentException("Value for row and/or column is invalid");
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (checkValid(row, col)) {
      if (status[row][col] == cellStatus.ELIMINATED) {
        status[row][col] = cellStatus.SPACE;
      } else {
        status[row][col] = cellStatus.ELIMINATED;
      }
    } else {
      throw new IllegalArgumentException("Value for row and/or column is invalid");
    }
  }

  @Override
  public void clear() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        status[i][j] = cellStatus.SPACE;
      }
    }
  }

  public boolean checkValid(int row, int col) {
    // true = valid coordinates
    if (row >= 0 && row <= height) {
      return col >= 0 && col <= width;
    }
    return false;
  }

  private enum cellStatus {
    SPACE,
    SHADED,
    ELIMINATED
  }
}
