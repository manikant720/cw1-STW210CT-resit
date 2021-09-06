package week3;

import java.util.LinkedList;

class BinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode root;

    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
 
        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        LinkedList<Integer> counts = new LinkedList<Integer>();
 
        nodes.add(root);
        counts.add(1);
 
        while(!nodes.isEmpty()){
            TreeNode curr = nodes.remove();
            int count = counts.remove();
 
            if(curr.left == null && curr.right == null){
                return count;
            }
 
            if(curr.left != null){
                nodes.add(curr.left);
                counts.add(count+1);
            }
 
            if(curr.right != null){
                nodes.add(curr.right);
                counts.add(count+1);
            }
        }
        return 0;
    }

    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;
     
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
     
        int bigger = Math.max(leftDepth, rightDepth);
     
        return bigger+1;
    }


    public static void main(String[] args) {
        // Creating the object of BinaryTree class
        BinaryTree bt = new BinaryTree();
        bt.root= new TreeNode(10);
        // bt.root.left= new TreeNode(7);
        bt.root.right= new TreeNode(9);
        bt.root.right.right= new TreeNode(8);
        bt.root.right.left= new TreeNode(6);
        bt.root.right.right.right= new TreeNode(8);
        bt.root.right.right.left= new TreeNode(6);
        System.out.println(bt.maxDepth(bt.root) - 1);
        System.out.println(bt.minDepth(bt.root) - 1 );

    }
}