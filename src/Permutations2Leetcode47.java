import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Permutations2Leetcode47 {
    public static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public static void getAllPermutations(int[] nums, int n, int idx, List<List<Integer>> outputList) {
        if (n == idx) {
            List<Integer> tempList = new ArrayList<>(n);
            for (int i : nums) {
                tempList.add(i);
            }
            outputList.add(tempList);
            return;
        }
        Set<Integer> elemUsed = new HashSet<>();
        for (int i = idx; i < n; i++) {
            if (!elemUsed.contains(nums[i])) {
                elemUsed.add(nums[i]);
                swap(nums, idx, i);
                getAllPermutations(nums, n, idx + 1, outputList);
                swap(nums, idx, i);
            }
        }
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> outputList = new ArrayList<>();
        int idx = 0;
        getAllPermutations(nums, nums.length, idx, outputList);
        return outputList;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());
        int n = str.countTokens();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(str.nextToken());
        }
        System.out.println(permuteUnique(nums));
    }
}
