import java.util.Scanner;

public class PermutationSequenceLeetcode60 {
    //THROWS TLE
    static int currentCount = 0;
    final static String CLOCK_WISE_ROTATION = "CLOCK_WISE_ROTATION";
    final static String ANTI_CLOCK_WISE_ROTATION = "ANTI_CLOCK_WISE_ROTATION";

    public static void rotate(StringBuffer ipStringBuf, int startIdx, int endIdx, String rotationType){
        if(startIdx>endIdx){
            return;
        }
        if(rotationType == CLOCK_WISE_ROTATION){
            char tempChar = ipStringBuf.charAt(endIdx);
            for(int i=endIdx-1; i>=startIdx; i--){
                ipStringBuf.setCharAt(i+1, ipStringBuf.charAt(i));
            }
            ipStringBuf.setCharAt(startIdx, tempChar);
        }
        else if(rotationType == ANTI_CLOCK_WISE_ROTATION){
            char tempChar = ipStringBuf.charAt(startIdx);
            for(int i=startIdx; i<endIdx; i++){
                ipStringBuf.setCharAt(i, ipStringBuf.charAt(i + 1));
            }
            ipStringBuf.setCharAt(endIdx, tempChar);
        }
    }

    public static void swapChars(StringBuffer ipStringBuf, int idx1, int idx2){
        char c = ipStringBuf.charAt(idx1);
        ipStringBuf.setCharAt(idx1, ipStringBuf.charAt(idx2));
        ipStringBuf.setCharAt(idx2, c);
    }

    public static void getKthPermutation(StringBuffer ipStringBuf, int n, int k, int treeIdx){
        if(treeIdx==n){
//            System.out.println(ipStringBuf);
            currentCount++;
        }
        if(currentCount == k){
            return;
        }
        for(int i=treeIdx; i<n; i++){
            swapChars(ipStringBuf, treeIdx, i);
            rotate(ipStringBuf, treeIdx+1, i, CLOCK_WISE_ROTATION);
            getKthPermutation(ipStringBuf, n, k, treeIdx+1);
            if(currentCount==k){
                return;
            }
            rotate(ipStringBuf, treeIdx+1, i, ANTI_CLOCK_WISE_ROTATION);
            swapChars(ipStringBuf, treeIdx, i);
            if(currentCount==k){
                return;
            }
        }
    }

    public static String getPermutation(int n, int k) {
        StringBuffer ipStringBuf = new StringBuffer(n);
        for(int i=1; i<=n; i++){
            ipStringBuf.append(i);
        }
        int treeIdx = 0;
        getKthPermutation(ipStringBuf, n, k, treeIdx);
        return ipStringBuf.toString();
    }

    public static void main(String[] args){
        int n, k;
        Scanner s= new Scanner(System.in);
        n = s.nextInt();
        k = s.nextInt();
        System.out.println(getPermutation(n, k));
    }
}
