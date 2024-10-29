package leetcode;

import java.util.Arrays;

public class S2684 {
    public int maxMoves(int[][] grid) {
        int m = grid.length , n = grid[0].length ;
        int[][] dp = new int[m][n] ;
        for(int[] dpBits : dp) {
            Arrays.fill(dpBits , -1) ;
        }
        int ans = 0 ;
        for(int row = 0 ; row < m ; row++) {
            ans = Math.max(ans , findMax(dp , row , 0 , m , n , grid)) ;
        }
        return Math.max(ans - 1, 0);
    }

    private int findMax(int[][] dp, int i, int j, int m, int n , int[][] grid) {
        if(dp[i][j] != -1) return dp[i][j] ;
        int[] dr = new int[]{-1 , 0 , 1} ;
        int[] dc = new int[]{ 1, 1 , 1} ;
        for(int k = 0 ; k < 3 ; k++) {
            int rr = i+dr[k] , cc = j+dc[k] ;
            if(rr < 0 || rr >= m || cc == n || grid[rr][cc] <= grid[i][j]) {
                dp[i][j] = Math.max(dp[i][j] , 1) ;
                continue ;
            }


            dp[i][j] = Math.max(dp[i][j] , findMax(dp , rr , cc , m , n , grid) + 1) ;
        }
        return dp[i][j] ;
    }
}
