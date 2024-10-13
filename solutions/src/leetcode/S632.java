package leetcode;

import java.util.*;

public class S632 {
    public int[] smallestRange(List<List<Integer>> nums) {
        if(nums.size() == 1) {
            return new int[] {nums.get(0).get(0) ,nums.get(0).get(0) } ;
        }
        // group number and elements in sorted order ASC
        HashMap<Integer , PriorityQueue<Integer>> grps = new HashMap<>() ;
        for(int i = 0 ; i < nums.size() ; i++) {
            List<Integer> num = nums.get(i) ;
            grps.put(i , new PriorityQueue<>(num)) ;
        }

        // PQ to get the least amount at a certain time
        PriorityQueue<int[]> minPq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])) ;

        //initialize ans
        int[] ans = new int[2] ;
        int currEndOfRange = Integer.MIN_VALUE;

        for(Map.Entry<Integer , PriorityQueue<Integer>> keyVal : grps.entrySet() ) {
            int key = keyVal.getKey() ;
            PriorityQueue<Integer> val = keyVal.getValue() ;
            currEndOfRange = Math.max(currEndOfRange , val.peek()) ;
            minPq.add(new int[] {val.poll() , key}) ;

        }

        ans = new int[]{minPq.peek()[0] , currEndOfRange} ;

        while(true) {
            int[] currMin = minPq.poll() ;
            int grpNumber = currMin[1] ;
            if(grps.get(grpNumber).isEmpty()) break ;

            currEndOfRange = Math.max(currEndOfRange , grps.get(grpNumber).peek()) ;
            minPq.add(new int[]{grps.get(grpNumber).poll() , grpNumber}) ;

            if(ans[1] - ans[0] > currEndOfRange - minPq.peek()[0]) {
                ans[1] = currEndOfRange ;
                ans[0] = minPq.peek()[0] ;
            }

        }
        return ans ;
    }
}
