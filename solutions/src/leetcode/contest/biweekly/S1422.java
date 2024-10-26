package leetcode.contest.biweekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class S1422 {
    static class TreeNode{
        int val ;
        List<TreeNode> childs ;
        TreeNode parent ;

        TreeNode(int val ) {
            this.val = val ;
            childs = new ArrayList<>() ;
        }
    }

    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length ;
        HashMap<Integer , TreeNode> nodes = new HashMap<>() ;
        TreeNode root = new TreeNode(0) ;
        nodes.put(0 , root ) ;
        for(int child = 1 ; child < n ; child++) {
            int p = parent[child] ;
            nodes.putIfAbsent(p , new TreeNode(p)) ;
            nodes.putIfAbsent(child , new TreeNode(child)) ;
            nodes.get(p).childs.add(nodes.get(child)) ;
            nodes.get(child).parent = nodes.get(p) ;
        }

        int[] recentNode = new int[26] ;
        Arrays.fill(recentNode , -1);
        int[] newParents = Arrays.copyOf(parent , n) ;
        dfs(root , recentNode , nodes , s.toCharArray() , newParents) ;


        TreeNode newRoot = new TreeNode(0) ;
        nodes = new HashMap<>() ;
        nodes.put(0 , newRoot ) ;
        for(int child = 1 ; child < n ; child++) {
            int p = newParents[child] ;
            nodes.putIfAbsent(p , new TreeNode(p)) ;
            nodes.putIfAbsent(child , new TreeNode(child)) ;
            nodes.get(p).childs.add(nodes.get(child)) ;
        }

        int[] ans = new int[n] ;

        countSubTree(ans , newRoot) ;

//        int[] possibleParents = new int[parent.length] ;
//        int n = parent.length ;
//
//        for(int i = 1 ; i < n ; i++) {
//            int curr = findParentWithSameChar(parent , parent[i] , s.toCharArray() , s.charAt(i)) ;
//            possibleParents[i] = curr != -1 ? curr : parent[i] ;
//        }
//
//        int[] ans = new int[n] ;
//        Arrays.fill(ans , 1 ) ;
//        for(int i = 1 ; i < n ; i++) {
//            addCountToAns(ans , possibleParents , possibleParents[i]);
//        }
        return ans ;
    }

    void dfs(TreeNode root , int[] recentNode , HashMap<Integer , TreeNode> nodes , char[] chars , int[] newParents)  {
        char desired = chars[root.val] ;
        if(recentNode[desired - 'a'] != -1) {
            TreeNode newParent = nodes.get(recentNode[desired - 'a']) ;
            newParents[root.val] = newParent.val ;
        }

        for(TreeNode child : root.childs) {
            int prev = recentNode[desired - 'a'] ;
            recentNode[desired - 'a'] = root.val ;
            dfs(child , recentNode , nodes , chars, newParents) ;
            recentNode[desired - 'a' ] = prev ;
        }
    }

    int countSubTree( int[] ans , TreeNode root) {
        int curr = 1 ;
        for(TreeNode child : root.childs) {
            curr += countSubTree(ans , child) ;
        }
        ans[root.val] = curr ;
        return ans[root.val] ;
    }

    int findParentWithSameChar(int[] parent , int x , char[] chars , char desired) {
        if(x == 0 && chars[x] != desired ) return  -1 ;
        if(x == 0) return 0 ;
        if(chars[x] == desired) return x ;
        return findParentWithSameChar(parent , parent[x] , chars , desired) ;
    }

    void addCountToAns(int[] ans , int[] parent , int x) {
        ans[x] += 1 ;
        if(x == 0) return ;
        addCountToAns(ans , parent , parent[x]);
    }
}
