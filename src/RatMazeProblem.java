import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RatMazeProblem {

    public static void getAllUniquePaths(int[][] maz, int i, int j, int m, int n, List<Pair<Integer, Integer>> auxList, List<List<Pair<Integer, Integer>>> outputList) {
        Pair<Integer, Integer> p = new ImmutablePair<>(i, j);
        auxList.add(p);
        if (i == m - 1 && j == n - 1) {
            outputList.add(new ArrayList<>(auxList));
            return;
        }
        if (i + 1 < m && maz[i + 1][j] == 0) {
            getAllUniquePaths(maz, i + 1, j, m, n, auxList, outputList);
            auxList.remove(auxList.size() - 1);
        }
        if (j + 1 < n && maz[i][j + 1] == 0) {
            getAllUniquePaths(maz, i, j + 1, m, n, auxList, outputList);
            auxList.remove(auxList.size() - 1);
        }
    }

    public static void main(String[] args) {
        int m, n;
        Scanner s = new Scanner(System.in);
        m = s.nextInt();
        n = s.nextInt();
        int[][] maz = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maz[i][j] = s.nextInt();
            }
        }
        List<Pair<Integer, Integer>> auxList = new ArrayList<>();
        List<List<Pair<Integer, Integer>>> outputList = new ArrayList<>();
        getAllUniquePaths(maz, 0, 0, m, n, auxList, outputList);
        System.out.println(outputList);
    }
}
