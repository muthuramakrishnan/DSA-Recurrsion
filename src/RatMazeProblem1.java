import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class RatMazeProblem1 {
    public static void getAllUniquePaths(int[][] arr, int m, int n, int i, int j, List<Entry<Integer, Integer>> auxList, List<List<Entry<Integer, Integer>>> outputList) {
        Entry<Integer, Integer> pair = new AbstractMap.SimpleEntry(i, j);
        auxList.add(pair);
        if (i == m - 1 && j == m - 1) {
            List<Entry<Integer, Integer>> tempList = new ArrayList<>(auxList);
            outputList.add(tempList);
            return;
        }
        if (i + 1 < m && arr[i + 1][j] == 0) {
            getAllUniquePaths(arr, m, n, i + 1, j, auxList, outputList);
            auxList.remove(auxList.size() - 1);
        }
        if (j + 1 < n && arr[i][j + 1] == 0) {
            getAllUniquePaths(arr, m, n, i, j + 1, auxList, outputList);
            auxList.remove(auxList.size() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        int m, n;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());
        m = Integer.parseInt(str.nextToken());
        n = Integer.parseInt(str.nextToken());

        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        List<Entry<Integer, Integer>> auxList = new ArrayList<>();
        List<List<Entry<Integer, Integer>>> outputList = new ArrayList<>();
        int i = 0, j = 0;
        getAllUniquePaths(arr, m, n, i, j, auxList, outputList);

        Iterator<List<Entry<Integer, Integer>>> it = outputList.iterator();
        System.out.print("[");
        while (it.hasNext()) {
            List<Entry<Integer, Integer>> innerList = it.next();
            Iterator<Entry<Integer, Integer>> innerIt = innerList.iterator();
            System.out.print("[");
            while (innerIt.hasNext()) {
                Entry<Integer, Integer> pair = innerIt.next();
                System.out.print("(" + pair.getKey() + ", " + pair.getValue() + ")");
                if (innerIt.hasNext()) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            if (it.hasNext()) {
                System.out.print(", ");
            }
        }
    }
}
