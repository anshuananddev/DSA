package leetcode;

import java.util.HashMap;

public class S670 {
    public int maximumSwap(int num) {
        String str = String.valueOf(num) ;
        int n = str.length() ;
        int[] actual =new int[n];
        int i = 0 ;
        for(char c : str.toCharArray()) {
            actual[i++] = Integer.parseInt(String.valueOf(c)) ;
        }

        HashMap<Integer , int[]> currMax = new HashMap<>() ;

        // for ith index which is the max form n-1th to ith and what is the index of that max
        currMax.put(n-1 , new int[] {actual[n-1] , n-1}) ;
        // keeps max from right to left
        for(i = n - 2 ; i>= 0 ; i--) {
            int[] curr ;
            if(actual[i] > currMax.get(i+1)[0]) {
                curr = new int[] {actual[i] , i}  ;
            }else{
                curr = currMax.get(i+1) ;
            }
            currMax.put(i , curr) ;
        }

        for(i = 0 ; i < n-1 ; i++) {
            if(actual[i] == currMax.get(i)[0] ) continue; ;
            int temp = actual[i] ;
            actual[i] = currMax.get(i)[0] ;
            actual[currMax.get(i)[1]] = temp ;
            break ;
        }

        StringBuilder ans = new StringBuilder();
        for(int e : actual) {
            ans.append(e);
        }
        return Integer.parseInt(ans.toString()) ;


    }
}
