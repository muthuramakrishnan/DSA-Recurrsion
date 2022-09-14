import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeetcodeCombinations216 {
    public static void generateCombinations(int n, int k, List<Integer> auxList, int auxSum, int startIdx, int idx, List<List<Integer>> outputList) {
        if (idx == k) {
            if (auxSum == n)
                outputList.add(new ArrayList<>(auxList.subList(0, k)));
            return;
        }
        for (int i = startIdx; i <= Math.min(n, 9); i++) {
            if (auxList.size() <= idx) {
                auxList.add(i);
            } else {
                auxList.set(idx, i);
            }
            generateCombinations(n, k, auxList, auxSum + i, i + 1, idx + 1, outputList);
        }
    }

    public static void main(String[] args) {
        int n, k;
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        k = s.nextInt();

        List<List<Integer>> outputList = new ArrayList<>();
        List<Integer> auxList = new ArrayList<>(k + 1);
        int startIdx = 1, idx = 0, auxSum = 0;
        generateCombinations(n, k, auxList, auxSum, startIdx, idx, outputList);
        System.out.println(outputList);
    }
}
