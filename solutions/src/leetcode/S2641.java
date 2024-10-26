package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class S2641 {
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>() ;
        List<Integer> levelSumList = new ArrayList<>() ;
        queue.add(root) ;
        while(!queue.isEmpty()){
            int levelSum = 0 ;
            int levelSize = queue.size() ;
            for(int i = 0 ; i < levelSize ; i++) {
                TreeNode curr = queue.poll() ;
                levelSum += curr.val ;
                if(curr.left != null) queue.offer(curr.left) ;
                if(curr.right != null) queue.offer(curr.right) ;
            }
            levelSumList.add(levelSum) ;
        }

        root.val = 0 ;
        int level = 1 ;
        queue.offer(root) ;
        while(!queue.isEmpty()) {
            int levelSize = queue.size() ;
            for(int i = 0 ; i < levelSize ; i++) {
                TreeNode curr = queue.poll() ;
                int left = 0 ;
                if(curr.left != null) {
                    left = curr.left.val ;
                    curr.left.val = levelSumList.get(level) - curr.left.val - (curr.right == null ? 0 : curr.right.val);
                    queue.offer(curr.left) ;
                }

                if(curr.right != null) {
                    curr.right.val = levelSumList.get(level) - curr.right.val - left;
                    queue.offer(curr.right) ;
                }
            }
            level++ ;
        }
        return root ;
    }
}
