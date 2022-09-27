import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LetterTilePossibilities {
    public static int tileCount = 0;
    public static void swap(StringBuffer ipString, int idx1, int idx2){
        char tempChar = ipString.charAt(idx1);
        ipString.setCharAt(idx1, ipString.charAt(idx2));
        ipString.setCharAt(idx2, tempChar);
    }
    public static void getTilePossibilities(StringBuffer ipString, int n, int idx){
        // for(int i=0; i<idx; i++){
        //     System.out.print(auxArr[i]);
        // }
        // System.out.println();
        if(n==idx){
            return;
        }
        Set<Character> elemUsed = new HashSet<>();
        for(int i=idx; i<n; i++){
            char tempChar = ipString.charAt(i);
            if(!elemUsed.contains(tempChar)){
                elemUsed.add(tempChar);
                // auxArr[idx] = tempChar;
                tileCount++;
                swap(ipString, idx, i);
                getTilePossibilities(ipString, n, idx+1);
                swap(ipString, idx, i);
            }
        }
    }
    public static int numTilePossibilities(String tiles) {
        StringBuffer ipString = new StringBuffer(tiles);
        int idx=0;
        // char[] auxArr = new char[ipString.length()];
        getTilePossibilities(ipString, ipString.length(), idx);
        return tileCount;
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        numTilePossibilities(s.next());
        System.out.println(tileCount);
    }
}
