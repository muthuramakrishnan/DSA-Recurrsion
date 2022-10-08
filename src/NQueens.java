import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NQueens {
  public static boolean hasCollision(boolean[][] arr, int n, int row, int col) {
    for (int i = 0; i < row; i++) {
      int diagonalCol1 = col - (row - i);
      int diagonalCol2 = col + (row - i);
      if (arr[i][col]
          || (diagonalCol1 > -1 && arr[i][diagonalCol1])
          || (diagonalCol2 < n && arr[i][diagonalCol2])) {
        return true;
      }
    }
    return false;
  }

  public static void solveNQueensProblem(
      int n, int row, int col, boolean[][] arr, List<List<List<Boolean>>> outputMatrixList) {
    if (row == n) {
      List<List<Boolean>> rowList = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        List<Boolean> colList = new ArrayList<>();
        for (int j = 0; j < n; j++) {
          colList.add(arr[i][j]);
        }
        rowList.add(colList);
      }
      outputMatrixList.add(rowList);
      return;
    }
    for (int j = col; j < n; j++) {
      if (!hasCollision(arr,n, row, j)) {
        arr[row][j] = true;
        solveNQueensProblem(n, row + 1, col, arr, outputMatrixList);
        arr[row][j] = false;
      }
    }
  }

  public static void main(String[] args) {
    int n;
    Scanner s = new Scanner(System.in);
    n = s.nextInt();
    List<List<List<Boolean>>> outputMatrixList = new ArrayList<>();
    boolean[][] tempMatrix = new boolean[n][n];

    int row = 0, col = 0;
    solveNQueensProblem(n, row, col, tempMatrix, outputMatrixList);
    for (List<List<Boolean>> matrix : outputMatrixList) {
      for (List<Boolean> rowList : matrix) {
        for (Boolean val : rowList) {
          System.out.print(val ? 1 + " " : 0 + " ");
        }
        System.out.println();
      }
      System.out.println();
    }
  }
}
