package leetcode.contest.biweekly;

import java.util.Arrays;
import java.util.List;

public class S1411 {
    public int[] minBitwiseArray(List<Integer> nums) {
        int[] ans = new int[nums.size()] ;
        Arrays.fill(ans , -1);
        for(int i = 0 ; i < ans.length ; i++) {
            int x = nums.get(i) ;
            for(int j = 0 ; j <= x ; j++) {
                if((j | (j+1)) == x) {
                    ans[i] = j ;
                    break ;
                }
            }

        }
        return ans ;
    }
}
