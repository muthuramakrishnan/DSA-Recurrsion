import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeetcodeCombinations77 {
    public static void generateCombinations(int n, int k, List<Integer> auxList, int startIdx, int idx, List<List<Integer>> outputList) {
        if (idx == k) {
            outputList.add(new ArrayList<>(auxList.subList(0, k)));
            return;
        }
        for (int i = startIdx; i <= n; i++) {
            if (auxList.size() <= idx) {
                auxList.add(i);
            } else {
                auxList.set(idx, i);
            }
            generateCombinations(n, k, auxList, i + 1, idx + 1, outputList);
        }
    }

    public static void main(String[] args) {
        int n, k;
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        k = s.nextInt();

        List<List<Integer>> outputList = new ArrayList<>();
        List<Integer> auxList = new ArrayList<>(k + 1);
        int startIdx = 1, idx = 0;
        generateCombinations(n, k, auxList, startIdx, idx, outputList);
        System.out.println(outputList);
    }
}
