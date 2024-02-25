package algorithm.tree;

public class TreeNode {
    
    int val;
    
    TreeNode left;
    
    TreeNode right;
    
    TreeNode(int x) {
        val = x;
    }
    
    TreeNode(int x, TreeNode left, TreeNode right) {
        val = x;
        this.left = left;
        this.right = right;
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        System.out.println("root = " + root);
    }
}
