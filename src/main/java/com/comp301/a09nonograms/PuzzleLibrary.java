package com.comp301.a09nonograms;

import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.CluesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PuzzleLibrary {
  private static List<Clues> clues;

  public static List<Clues> create() {
    if (clues == null) {
      createPuzzleLibrary();
    }
    return clues;
  }

  private static void createPuzzleLibrary() {
    clues = new ArrayList<>();

    clues.add(
        new CluesImpl(
            new int[][] {
              new int[] {0, 0},
              new int[] {0, 4},
              new int[] {0, 6},
              new int[] {2, 2},
              new int[] {2, 2},
              new int[] {0, 6},
              new int[] {0, 4},
              new int[] {0, 2},
              new int[] {0, 2},
              new int[] {0, 2},
              new int[] {0, 0},
            },
            new int[][] {
              new int[] {0, 0},
              new int[] {0, 9},
              new int[] {0, 9},
              new int[] {2, 2},
              new int[] {2, 2},
              new int[] {0, 4},
              new int[] {0, 4},
              new int[] {0, 0},
            }));

    clues.add(
        new CluesImpl(
            new int[][] {
              new int[] {1, 1, 1},
              new int[] {0, 1, 3},
              new int[] {0, 0, 2},
              new int[] {0, 0, 2},
              new int[] {0, 0, 2},
            },
            new int[][] {
              new int[] {2, 2},
              new int[] {0, 2},
              new int[] {0, 2},
              new int[] {0, 2},
              new int[] {0, 3},
            }));

    clues.add(
        new CluesImpl(
            new int[][] {
              new int[] {0, 4, 2},
              new int[] {0, 0, 4},
              new int[] {0, 0, 6},
              new int[] {0, 2, 2},
              new int[] {0, 2, 3},
              new int[] {0, 1, 4},
              new int[] {1, 1, 1},
              new int[] {0, 1, 4},
              new int[] {0, 3, 2},
              new int[] {0, 0, 7},
            },
            new int[][] {
              new int[] {0, 0, 0, 3},
              new int[] {0, 0, 0, 4},
              new int[] {0, 0, 0, 9},
              new int[] {0, 3, 1, 2},
              new int[] {0, 0, 1, 5},
              new int[] {1, 4, 1, 1},
              new int[] {0, 1, 3, 3},
              new int[] {0, 0, 0, 6},
              new int[] {0, 0, 0, 1},
              new int[] {0, 0, 0, 1},
            }));

    clues.add(
        new CluesImpl(
            new int[][] {
              new int[] {0, 3},
              new int[] {0, 4},
              new int[] {0, 3},
              new int[] {1, 1},
              new int[] {0, 1},
            },
            new int[][] {
              new int[] {2, 1},
              new int[] {0, 2},
              new int[] {0, 4},
              new int[] {0, 2},
              new int[] {0, 2},
            }));

    clues.add(
        new CluesImpl(
            new int[][] {
              new int[] {0, 0, 5},
              new int[] {0, 0, 6},
              new int[] {0, 0, 8},
              new int[] {0, 2, 3},
              new int[] {0, 0, 3},
              new int[] {0, 0, 5},
              new int[] {0, 0, 7},
              new int[] {0, 0, 2},
              new int[] {2, 1, 3},
              new int[] {0, 2, 1},
            },
            new int[][] {
              new int[] {0, 1, 2},
              new int[] {1, 1, 2},
              new int[] {0, 4, 1},
              new int[] {4, 2, 1},
              new int[] {0, 3, 3},
              new int[] {0, 0, 9},
              new int[] {0, 5, 1},
              new int[] {4, 1, 1},
              new int[] {0, 1, 1},
              new int[] {0, 1, 1},
            }));
  }

  public static void createRandPuzzle() {
    clues = new ArrayList<>();
    Random rand = new Random();
    int gridSizeLen = rand.nextInt(10);
    int gridSizeWid = rand.nextInt(10);
    boolean[][] randGrid = new boolean[gridSizeLen][gridSizeWid];
    int[][] randCluesLen = new int[gridSizeLen][gridSizeWid];
    int[][] randCluesWid = new int[gridSizeLen][gridSizeWid];

    // generate randomly shaded grid
    for (int i = 0; i < gridSizeLen; i++) {
      for (int j = 0; j < gridSizeWid; j++) {
        int maybeShaded = rand.nextInt(10);
        randGrid[i][j] = maybeShaded <= 4;
      }
    }

    // find number of clues to initialize array
    int[] numClues = findNumClues(randGrid, gridSizeLen, gridSizeWid);

    // determine row clues
    for (int i = 0; i < gridSizeLen; i++) {
      int[] rowClues = new int[numClues[0]];
      for (int j = 0; j < gridSizeWid; j++) {
        if (randGrid[i][j]) {}
      }
    }
  }

  public static int[] findNumClues(boolean[][] grid, int len, int wid) {
    int[] numClues = new int[2];

    // check all rows
    for (int i = 0; i < len; i++) {
      int spaceCounter = 0;
      for (int j = 0; j < wid; j++) {
        if (!grid[i][j]) {
          spaceCounter++;
        }
      }
      if (numClues[0] < spaceCounter + 1) {
        numClues[0] = spaceCounter + 1;
      }
    }

    // check all columns
    for (int i = 0; i < wid; i++) {
      int spaceCounter = 0;
      for (int j = 0; j < len; j++) {
        if (!grid[j][i]) {
          spaceCounter++;
        }
      }
      if (numClues[1] < spaceCounter + 1) {
        numClues[1] = spaceCounter + 1;
      }
    }

    return numClues;
  }
}
