package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class S2530 {
    public long maxKelements(int[] nums, int k) {

        //max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(nums.length, Comparator.reverseOrder());
        pq.addAll(Arrays.stream(nums).boxed().toList());
        int ans = 0;
        while (k > 0) {
            int curr = pq.poll();
            ans += curr;
            pq.add((int) Math.ceil(curr / 3));
            k--;
        }
        return ans;


    }
}
