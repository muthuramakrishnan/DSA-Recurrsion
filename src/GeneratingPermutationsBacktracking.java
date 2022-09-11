import java.util.Scanner;

public class GeneratingPermutationsBacktracking {
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
            generatePermutations(ipStringBuf, treeLevel + 1);
            swapChars(ipStringBuf, treeLevel, i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuffer sb = new StringBuffer(sc.next());

        int treeLevel = 0;
        generatePermutations(sb, treeLevel);
    }
}
