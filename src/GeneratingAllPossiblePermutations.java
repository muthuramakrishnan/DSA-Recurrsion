import java.util.Scanner;

public class GeneratingAllPossiblePermutations {
    public static boolean containsChar(char[] auxArr, char c, int startIdx, int endIdx) {
        for (int i = startIdx; i < endIdx; i++) {
            if (auxArr[i] == c) {
                return true;
            }
        }
        return false;
    }

    public static void generatePermutations(String origString, int origStrIdx, int origArrSize, char[] auxArr, int auxArrIdx) {
        if (origArrSize == origStrIdx) {
            for (int i = 0; i < auxArrIdx; i++) {
                System.out.print(auxArr[i]);
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < origArrSize; i++) {
            char c = origString.charAt(i);
            boolean hasChar = containsChar(auxArr, c, 0, auxArrIdx);
            if (!hasChar) {
                auxArr[auxArrIdx] = c;
                generatePermutations(origString, origStrIdx + 1, origArrSize, auxArr, auxArrIdx + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();

        char[] auxArr = new char[n];
        int auxArrIdx = 0;
        int origStrIdx = 0;
        generatePermutations(s, origStrIdx, n, auxArr, auxArrIdx);
    }
}
