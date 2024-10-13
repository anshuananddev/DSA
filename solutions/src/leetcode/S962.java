package leetcode;

import java.util.Stack;

public class S962 {

    //As we iterate over the array, we push indices onto a stack only if the value at
    // the current index is smaller than or equal to the value at the index on top of
    // the stack. This ensures that the stack contains a list of potential starting points
    // for ramps, in decreasing order of value. The key insight is that when we encounter
    // a larger value, we begin popping indices from the stack. For each index popped,
    // we calculate the ramp width formed with the current index and check if it exceeds
    // the maximum width we have tracked. Since the values on the stack are always decreasing,
    // popping an index means that we have found a ramp where the condition nums[i]≤nums[j]
    // is satisfied.
    public int maxWidthRamp(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;

        // First make all potential starting points .
        // We can't go on popping it on the go . -- Try to pop on the go fot [9,8,1,9,4,0,4,1] , even before we reach the last '4' , first '1' will be popped out
        // due to last '9' , so we will never be able to consider the that window .

        // Instead first store all potential starting points , and start popping elements from stack by traversing nums from backwards .
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty() || nums[i] < stack.peek())
                stack.push(i);
        }

        for(int i = nums.length -1 ; i >= 0 ; i--) {
            while(!stack.isEmpty() && stack.peek() <= nums[i]) {
                ans = Math.max(ans , i - stack.pop() + 1) ;
            }
        }
        return ans;
    }
}
