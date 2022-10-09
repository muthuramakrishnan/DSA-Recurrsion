import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SudokuSolver {
  public static int getSubMatrixNum(int i, int j) {
    return ((i / 3) * 3) + ((j / 3));
  }

  public static void solveSudoku(
      int[][] board,
      int boardSize,
      int row,
      int col,
      int[][] rowFreq,
      int[][] colFreq,
      int[][] subMatrixFreq,
      List<List<List<Integer>>> output) {
    if (row >= boardSize) {
      List<List<Integer>> rowList = new ArrayList<>();
      for (int i = 0; i < boardSize; i++) {
        List<Integer> colList = new ArrayList<>();
        for (int j = 0; j < boardSize; j++) {
          colList.add(board[i][j]);
        }
        rowList.add(colList);
      }
      output.add(rowList);
      return;
    }
    int subMatrix = getSubMatrixNum(row, col);
    if (board[row][col] == 0) {
      for (int i = 1; i <= boardSize; i++) {
        if (rowFreq[row][i] == 0 && colFreq[col][i] == 0 && subMatrixFreq[subMatrix][i] == 0) {
          board[row][col] = i;
          rowFreq[row][i]++;
          colFreq[col][i]++;
          subMatrixFreq[subMatrix][i]++;
          solveSudoku(
              board,
              boardSize,
              col + 1 >= boardSize ? row + 1 : row,
              col + 1 >= boardSize ? 0 : col + 1,
              rowFreq,
              colFreq,
              subMatrixFreq,
              output);
          board[row][col] = 0;
          rowFreq[row][i]--;
          colFreq[col][i]--;
          subMatrixFreq[subMatrix][i]--;
        }
      }
    } else {
      solveSudoku(
          board,
          boardSize,
          col + 1 >= boardSize ? row + 1 : row,
          col + 1 >= boardSize ? 0 : col + 1,
          rowFreq,
          colFreq,
          subMatrixFreq,
          output);
    }
  }

  public static void main(String[] args) throws IOException {
    final int boardSize = 9;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[][] board = new int[boardSize][boardSize];

    for (int i = 0; i < boardSize; i++) {
      String[] tempStr = br.readLine().split(" ");
      for (int j = 0; j < boardSize; j++) {
        board[i][j] = Integer.parseInt(tempStr[j]);
      }
    }

    int[][] rowFreq = new int[boardSize][boardSize + 1];
    int[][] colFreq = new int[boardSize][boardSize + 1];
    int[][] subMatrixFreq = new int[boardSize][boardSize + 1];

    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        int subMatrixNum = getSubMatrixNum(row, col);
        int num = board[row][col];
        if (num != 0) {
          rowFreq[row][num]++;
          colFreq[col][num]++;
          subMatrixFreq[subMatrixNum][num]++;
        }
      }
    }
    int row = 0, col = 0;
    List<List<List<Integer>>> output = new ArrayList<>();
    solveSudoku(board, boardSize, row, col, rowFreq, colFreq, subMatrixFreq, output);

    for (List<List<Integer>> resultItem : output) {
      for (List<Integer> rowList : resultItem) {
        for (Integer item : rowList) {
          System.out.print(item + " ");
        }
        System.out.println();
      }
      System.out.println();
    }
  }
}
