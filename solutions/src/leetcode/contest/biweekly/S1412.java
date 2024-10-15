package leetcode.contest.biweekly;

import java.util.Arrays;
import java.util.List;

public class S1412 {
    public int[] minBitwiseArray(List<Integer> nums) {

        int[] ans = new int[nums.size()];
        Arrays.fill(ans, -1);
        for (int i = 0; i < ans.length; i++) {
            int k = (int) (Math.log(nums.get(i) + 1) / Math.log(2));
            int n = (1 << (k - 1)) - 1;
            if ((n | (n + 1)) == nums.get(i)){
                ans[i] = n;
            }
            else{
                String bn = Integer.toBinaryString(nums.get(i)) ;
                int len = bn.length() - 1;
                for(int j = (int)Math.pow(2 , len) ; j <= nums.get(i) ; j++) {
                    if((j | ( j+1)) == nums.get(i)) ans[i] = j ;
                }
            }


        }
        return ans;
    }

}
