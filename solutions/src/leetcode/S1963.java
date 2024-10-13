package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class S1963 {
    public int minSwaps(String s) {
        // main intuition is that one swap balances 2 unbalanced pair at max .
        //after processing string through stack , we will always be left with all closing brackets first and then all opening brackets
        // eg :- ][ , ]][[
        //for both of the above examples we need only one swap to balance whole string - [] and  [][]
        Deque<Character> stack = new ArrayDeque<>() ;
        int n = s.length() , i = 0 ;
        while(i < n) {
            char c = s.charAt(i) ;
            if(c == '[') {
                stack.add(c) ;
            }else{
                if(stack.isEmpty() || stack.peekLast() == ']') stack.addLast(c) ;
                else stack.pollLast() ;

            }
            i++ ;
        }

        return (stack.size()/2 + 1)/2;

    }
}
