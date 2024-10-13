package leetcode;

import java.util.*;

public class S2406 {
    // nlogn solution
//    public int minGroups(int[][] intervals) {
//        // Just like Meeting Room II - we need to find maximum overlapping intervals , and that will the minimum group we need to form
//        // basically for every interval we are going to create 2 events , one starting event when it starts
//        // starting event will tell us that at that point of time an event has started .
//        // another event ending time will say that an event has ended at a time ,so decreasing the concurrency at that moment
//        // The maximum concurrent meeting is the maximum overlapping interval
//
//        int[][] events = new int[2 * intervals.length][];
//        int ans = 0;
//        int k = 0;
//        for (int i = 0; i < intervals.length; i++) {
//            events[k++] = new int[]{intervals[i][0], 1};
//            events[k++] = new int[]{intervals[i][1] + 1, -1};
//        }
//
//        Arrays.sort(events, (a, b) -> {
//            if (a[0] != b[0])
//                return Integer.compare(a[0], b[0]);
//            return Integer.compare(a[1], b[1]);
//        });
//        int curr = 0;
//        for (int[] event : events) {
//            curr += event[1];
//            ans = Math.max(ans, curr);
//        }
//
//        return ans ;
//    }

    // O(n) solution
    public int minGroups(int[][] intervals) {

        // find the maximum time we need to consider
        int max = 0 ;
        for(int[] a : intervals){
            max = Math.max(max , a[1]) ;
        }

        //create a timeline of max range
        int[] ans = new int[max+2] ;

        // adding 1 at start of event as 1 meeting is required at that time , and -1 at end of event as 1 room will be empty at that moment
        for (int[] interval : intervals) {
            ans[interval[0]] += 1;
            ans[interval[1] + 1] -= 1;
        }

        int finalAns = 0 ;
        // Line Sweep -- It counts concurrent room required at a moment
        for(int i = 1 ; i<= max+1 ; i++) {
            ans[i] = ans[i-1] + ans[i] ;
            finalAns = Math.max(finalAns , ans[i]) ;
        }
        return finalAns ;

    }

}
