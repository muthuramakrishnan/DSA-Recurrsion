class SoldierRowListComparator implements Comparator<Map.Entry<Integer, Integer>> {

    public int compare(Map.Entry<Integer, Integer> row1, Map.Entry<Integer, Integer> row2) {
        int row1Soldier = row1.getKey();
        int row1Num = row1.getValue();
        int row2Soldier = row2.getKey();
        int row2Num = row2.getValue();

        if (row1Soldier > row2Soldier) {
            return 1;
        }
        if (row1Soldier == row2Soldier) {
            if (row1Num > row2Num) {
                return 1;
            }
        }
        return -1;
    }
}

class Solution {

    public int getNumSoldiers(int[] arr) {
        int len = arr.length;
        int low = 0, high = len - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == 1) {
                if (mid + 1 > len - 1 || arr[mid + 1] == 0) {
                    ans = mid;
                    break;
                } else {
                    ans = mid;
                    low = mid + 1;
                }
            } else high = mid - 1;
        }
        return ans;
    }

    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        List<Map.Entry<Integer, Integer>> soldierRowList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int numSoldiers = getNumSoldiers(mat[i]);
            System.out.println(numSoldiers + "-->" + i);
            Map.Entry<Integer, Integer> soldierRowPair = new HashMap.SimpleEntry(numSoldiers, i);
            soldierRowList.add(soldierRowPair);
        }
        Comparator<Map.Entry<Integer, Integer>> c = new SoldierRowListComparator();
        Collections.sort(soldierRowList, c);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = soldierRowList.get(i).getValue().intValue();
        }
        return result;
    }
}
