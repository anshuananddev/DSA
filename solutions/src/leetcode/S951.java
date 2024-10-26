package leetcode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

public class S951 {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return true ;
        if(root1 == null || root2 == null) return false ;
        if(root1.val != root2.val) return false ;
        Queue<TreeNode> l1 = new ArrayDeque<>() ;
        Queue<TreeNode> l2 = new ArrayDeque<>() ;
        HashMap<Integer , Integer> map ;
        l1.offer(root1) ;
        l2.offer(root2) ;


        while(!l1.isEmpty() && !l2.isEmpty()) {
            map = new HashMap<>() ;
            int len = l1.size() ;
            for(int i = 0 ; i < len ; i++) {
                TreeNode curr = l1.poll() ;
                if(curr.left != null) {
                    l1.offer(curr.left) ;
                    map.put(curr.left.val , curr.val) ;
                }
                if(curr.right != null){
                    l1.offer(curr.right) ;
                    map.put(curr.right.val , curr.val) ;
                }
            }

            len = l2.size() ;
            for(int i = 0 ; i < len ; i++) {
                TreeNode curr = l2.poll() ;
                if(curr.left != null) {
                    if(!map.containsKey(curr.left.val) || map.get(curr.left.val) != curr.val) return false ;
                    map.remove(curr.left.val) ;
                    l2.offer(curr.left) ;
                }
                if(curr.right != null) {
                    if(!map.containsKey(curr.right.val) || map.get(curr.right.val) != curr.val) return false ;
                    map.remove(curr.right.val) ;
                    l2.offer(curr.right) ;
                }
            }

            if(!map.isEmpty()) return false ;
        }

        if(!l1.isEmpty() || !l2.isEmpty()) return false ;

        return true ;
    }
}
