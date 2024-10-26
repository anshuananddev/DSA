package leetcode.contest.biweekly;

public class S1421 {
    public int possibleStringCount(String word) {
        int ans = 0 ;
        char[] chars = word.toCharArray() ;
        int curr = 0 ;
        for(int i = 1 ; i < chars.length ; i++) {
            if(chars[i] == chars[i-1]) curr++ ;
            else if(curr > 0) {
                ans += curr ;
                curr = 0 ;
            }
        }

        return ans + 1 + curr  ;
    }
}
