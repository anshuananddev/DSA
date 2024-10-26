package leetcode.contest.biweekly;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Arrays;

public class S1423 {
    public int maxScore(int n, int k, int[][] stayScore, int[][] travelScore) {
        int ans = 0 ;

        // currDay , currCity
        int[][] dp = new int[k][n] ;
        for(int[] arr : dp) {
            Arrays.fill(arr , -1) ;
        }
        for(int i = 0 ; i < n ; i++) {
            ans = Math.max(ans , findMax(n , k , stayScore , travelScore , i , 0 , dp) );
        }
        return ans;

    }

    private int findMax(int n, int k, int[][] stayScore, int[][] travelScore , int currCity , int currDay , int[][] dp) {
        if(k <= 0 ) return 0 ;
        if(dp[currDay][currCity] != -1) return dp[currDay][currCity] ;
        int curr = 0 ;
        for(int i = 0 ; i < n ; i++) {
            if(currCity == i) {
                curr = Math.max(curr , findMax(n , k-1 , stayScore , travelScore, i , currDay + 1 , dp) + stayScore[currDay][currCity]) ;
            }else{
                curr = Math.max(curr , findMax(n , k-1 , stayScore , travelScore, i , currDay + 1 , dp) + travelScore[currCity][i]) ;
            }
        }
        dp[currDay][currCity] = curr ;
        return curr ;
    }
}
