import java.util.Scanner;

public class GeneratingPermutationsBacktrackingLexico {
    final static String CLOCK_WISE_ROTATION = "CLOCK_WISE_ROTATION";
    final static String ANTI_CLOCK_WISE_ROTATION = "ANTI_CLOCK_WISE_ROTATION";

    public static void rotate(StringBuffer sb, int startIdx, int endIdx, String rotationType) {
        if (startIdx > endIdx) {
            return;
        }
        if (rotationType == CLOCK_WISE_ROTATION) {
            char tempChar = sb.charAt(endIdx);
            for (int i = endIdx - 1; i >= startIdx; i--) {
                sb.setCharAt(i + 1, sb.charAt(i));
            }
            sb.setCharAt(startIdx, tempChar);
        } else if (rotationType == ANTI_CLOCK_WISE_ROTATION) {
            char tempChar = sb.charAt(startIdx);
            for (int i = startIdx; i < endIdx; i++) {
                sb.setCharAt(i, sb.charAt(i + 1));
            }
            sb.setCharAt(endIdx, tempChar);
        }
    }

    public static void swapChars(StringBuffer sb, int index1, int index2) {
        char tempChar = sb.charAt(index1);
        sb.setCharAt(index1, sb.charAt(index2));
        sb.setCharAt(index2, tempChar);
    }

    public static void generatePermutations(StringBuffer ipStringBuf, int treeLevel) {
        if (treeLevel == ipStringBuf.length()) {
            System.out.println(ipStringBuf);
            return;
        }
        for (int i = treeLevel; i < ipStringBuf.length(); i++) {
            //swap chars at i index & treelevel index
            swapChars(ipStringBuf, treeLevel, i);
            rotate(ipStringBuf, treeLevel + 1, i, CLOCK_WISE_ROTATION);
            generatePermutations(ipStringBuf, treeLevel + 1);
            swapChars(ipStringBuf, treeLevel, i);
            rotate(ipStringBuf, treeLevel + 1, i, ANTI_CLOCK_WISE_ROTATION);
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        StringBuffer ipStringBuf = new StringBuffer(s.next());

        int treeLevel = 0;
        generatePermutations(ipStringBuf, treeLevel);

    }
}
