import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RatMazeProblem2 {
    private static int numPaths = 0;
    public static boolean canVisit(int[][] arr, Set<Integer> visitedIdxs, int i, int j, int m, int n) {
        if (i < 0 || j < 0 || i == m || j == n) {
            return false;
        }
        int temp = Integer.parseInt(i + "" + j);
        return (arr[i][j] == 2 || arr[i][j] == 0) && !visitedIdxs.contains(temp);
    }

        public static void getAllPermutations(int[][] arr, int m, int n, int i, int j, Set<Integer> visitedIdxs, List<Map.Entry<Integer, Integer>> auxList, List<List<Map.Entry<Integer, Integer>>> outputList) {
        Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(i, j);
        auxList.add(entry);

        if (arr[i][j] == 2) {
            numPaths++;
            List<Map.Entry<Integer, Integer>> tempList = new ArrayList<>(auxList);
            outputList.add(tempList);
            return;
        }

        //i+1, i-1, j+1, j-1
        if (canVisit(arr, visitedIdxs, i + 1, j, m, n)) {
            visitedIdxs.add(Integer.parseInt((i + 1) + "" + j));
            getAllPermutations(arr, m, n, i + 1, j, visitedIdxs, auxList, outputList);
            auxList.remove(auxList.size() - 1);
            visitedIdxs.remove(Integer.parseInt((i + 1) + "" + j));
        }
        if (canVisit(arr, visitedIdxs, i - 1, j, m, n)) {
            visitedIdxs.add(Integer.parseInt((i - 1) + "" + j));
            getAllPermutations(arr, m, n, i - 1, j, visitedIdxs, auxList, outputList);
            auxList.remove(auxList.size() - 1);
            visitedIdxs.remove(Integer.parseInt((i - 1) + "" + j));
        }
        if (canVisit(arr, visitedIdxs, i, j + 1, m, n)) {
            visitedIdxs.add(Integer.parseInt(i + "" + (j + 1)));
            getAllPermutations(arr, m, n, i, j + 1, visitedIdxs, auxList, outputList);
            auxList.remove(auxList.size() - 1);
            visitedIdxs.remove(Integer.parseInt(i + "" + (j + 1)));
        }
        if (canVisit(arr, visitedIdxs, i, j - 1, m, n)) {
            visitedIdxs.add(Integer.parseInt(i + "" + (j - 1)));
            getAllPermutations(arr, m, n, i, j - 1, visitedIdxs, auxList, outputList);
            auxList.remove(auxList.size() - 1);
            visitedIdxs.remove(Integer.parseInt(i + "" + (j - 1)));
        }
    }

    public static void main(String[] args) throws IOException {
        int m, n;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());
        m = Integer.parseInt(str.nextToken());
        n = Integer.parseInt(str.nextToken());

        int startI = 0;
        int startJ = 0;
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(str.nextToken());
                if (arr[i][j] == 1) {
                    startI = i;
                    startJ = j;
                }
            }
        }

        List<Map.Entry<Integer, Integer>> auxList = new ArrayList<>();
        List<List<Map.Entry<Integer, Integer>>> outputList = new ArrayList<>();
        Set<Integer> visitedIdxs = new HashSet<>();
        visitedIdxs.add(Integer.parseInt(startI + "" + startJ));
        getAllPermutations(arr, m, n, startI, startJ, visitedIdxs, auxList, outputList);

        Iterator<List<Map.Entry<Integer, Integer>>> it = outputList.iterator();
        System.out.println("[");
        while (it.hasNext()) {
            List<Map.Entry<Integer, Integer>> innerList = it.next();
            Iterator<Map.Entry<Integer, Integer>> innerIt = innerList.iterator();
            System.out.print("[");
            while (innerIt.hasNext()) {
                Map.Entry<Integer, Integer> pair = innerIt.next();
                System.out.print("(" + pair.getKey() + ", " + pair.getValue() + ")");
                if (innerIt.hasNext()) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            if (it.hasNext()) {
                System.out.println(", ");
            }
        }
        System.out.println();
        System.out.println("Total number of paths = "+ numPaths);
    }
}
