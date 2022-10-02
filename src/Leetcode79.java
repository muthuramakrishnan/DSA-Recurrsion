import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Leetcode79 {
    public static boolean containsWord = false;

    public static void computeContainsWord(char[][] arr, int m, int n, int i, int j, Set<Integer> visitedIdxs, StringBuilder ipString, int strlen, int strIdx) {
        if (strIdx == strlen || containsWord) {
            containsWord = true;
            return;
        }

        char nxtStrChar='\0';
        if(strIdx<strlen){
            nxtStrChar = ipString.charAt(strIdx);
        }
        //i+1, i-1, j+1, j-1
        if(i+1<m && arr[i+1][j]==nxtStrChar && !visitedIdxs.contains(Integer.parseInt((i+1)+""+j))){
            visitedIdxs.add(Integer.parseInt((i+1)+""+j));
            computeContainsWord(arr, m, n, i+1, j, visitedIdxs, ipString, strlen, strIdx+1);
            visitedIdxs.remove(Integer.parseInt((i+1)+""+j));
        }
        if(i-1>-1 && arr[i-1][j]==nxtStrChar && !visitedIdxs.contains(Integer.parseInt((i-1)+""+j))){
            visitedIdxs.add(Integer.parseInt((i-1)+""+j));
            computeContainsWord(arr, m, n, i-1, j, visitedIdxs, ipString, strlen, strIdx+1);
            visitedIdxs.remove(Integer.parseInt((i-1)+""+j));
        }
        if(j+1<n && arr[i][j+1]==nxtStrChar && !visitedIdxs.contains(Integer.parseInt(i+""+(j+1)))){
            visitedIdxs.add(Integer.parseInt(i+""+(j+1)));
            computeContainsWord(arr, m, n, i, j+1, visitedIdxs, ipString, strlen, strIdx+1);
            visitedIdxs.remove(Integer.parseInt(i+""+(j+1)));
        }
        if(j-1>-1 && arr[i][j-1]==nxtStrChar && !visitedIdxs.contains(Integer.parseInt(i+""+(j-1)))){
            visitedIdxs.add(Integer.parseInt(i+""+(j-1)));
            computeContainsWord(arr, m, n, i, j-1, visitedIdxs, ipString, strlen, strIdx+1);
            visitedIdxs.remove(Integer.parseInt(i+""+(j-1)));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ipString = new StringBuilder(br.readLine());
        StringTokenizer str = new StringTokenizer(br.readLine());
        int m, n;
        m = Integer.parseInt(str.nextToken());
        n = Integer.parseInt(str.nextToken());

        char[][] arr = new char[m][n];
        List<Map.Entry<Integer, Integer>> startPoints = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = str.nextToken().charAt(0);
                if (arr[i][j] == ipString.charAt(0)) {
                    startPoints.add(new AbstractMap.SimpleEntry(i, j));
                }
            }
        }
        Set<Integer> visitedIdxs = new HashSet<>();
        for (int i = 0; i < startPoints.size(); i++) {
            Map.Entry<Integer, Integer> startPoint = startPoints.get(i);
            int startI = startPoint.getKey();
            int startJ = startPoint.getValue();
            visitedIdxs.add(Integer.parseInt(startI + "" + startJ));
            computeContainsWord(arr, m, n, startI, startJ, visitedIdxs, ipString, ipString.length(), 1 );
            visitedIdxs.clear();
            if (containsWord) {
                System.out.println(true);
                return;
            }
        }
        System.out.println(containsWord);
    }
}
