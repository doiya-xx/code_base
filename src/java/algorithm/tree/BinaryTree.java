package algorithm.tree;

import utils.CollectionUtils;

import java.util.*;

public class BinaryTree {
    
    public static void main(String[] args) {
    
    }
    
    /**
     * 根据Integer[]数组创建二叉树
     *
     * @param array Integer[]数组
     * @return {@code TreeNode}
     */
    public static TreeNode createBinaryTree(Integer[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        List<TreeNode> nodeList = new ArrayList<>();
        for (Integer i : array) {
            if (i == null) {
                nodeList.add(null);
            } else {
                nodeList.add(new TreeNode(i));
            }
        }
        TreeNode root = nodeList.get(0);
        for (int i = 0; i < array.length / 2; i++) {
            TreeNode node = nodeList.get(i);
            if (node == null) {
                continue;
            }
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;
            if (leftIndex < nodeList.size()) {
                node.left = nodeList.get(leftIndex);
            }
            if (rightIndex < nodeList.size()) {
                node.right = nodeList.get(rightIndex);
            }
        }
        return root;
    }
    
    /**
     * 基于广度优先，根据Integer[]数组创建二叉树
     *
     * @param array Integer[]数组
     * @return {@code TreeNode}
     */
    public static TreeNode createBinaryTreeByBreadthFirst(Integer[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < array.length) {
            TreeNode node = queue.poll();
            if (array[i] != null) {
                node.left = new TreeNode(array[i]);
                queue.offer(node.left);
            }
            i++;
            if (i < array.length && array[i] != null) {
                node.right = new TreeNode(array[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }
    
    /**
     * 根据前序遍历List<Integer>和中序遍历List<Integer>创建二叉树
     *
     * @param preorder 前序遍历List<Integer>
     * @param inorder  中序遍历List<Integer>
     * @return {@code TreeNode}
     */
    public static TreeNode createBinaryTreeByPreIn(List<Integer> preorder, List<Integer> inorder) {
        if (CollectionUtils.isEmpty(preorder, inorder)) {
            return null;
        }
        int length = preorder.size();
        if (length == 1) {
            return new TreeNode(preorder.get(0));
        }
        TreeNode root = new TreeNode(preorder.get(0));
        int rootIndex = inorder.indexOf(preorder.get(0));
        List<Integer> leftPreorder = preorder.subList(1, rootIndex + 1);
        List<Integer> rightPreorder = preorder.subList(rootIndex + 1, length);
        List<Integer> leftInorder = inorder.subList(0, rootIndex);
        List<Integer> rightInorder = inorder.subList(rootIndex + 1, length);
        root.left = createBinaryTreeByPreIn(leftPreorder, leftInorder);
        root.right = createBinaryTreeByPreIn(rightPreorder, rightInorder);
        return root;
    }
    
    /**
     * 根据中序遍历List<Integer>和后序遍历List<Integer>创建二叉树
     *
     * @param inorder   中序遍历List<Integer>
     * @param postorder 后序遍历List<Integer>
     * @return {@code TreeNode}
     */
    public static TreeNode createBinaryTreeByInPost(List<Integer> inorder, List<Integer> postorder) {
        if (CollectionUtils.isEmpty(inorder, postorder)) {
            return null;
        }
        int length = postorder.size();
        if (length == 1) {
            return new TreeNode(postorder.get(0));
        }
        TreeNode root = new TreeNode(postorder.get(length - 1));
        int rootIndex = inorder.indexOf(postorder.get(length - 1));
        List<Integer> leftInorder = inorder.subList(0, rootIndex);
        List<Integer> rightInorder = inorder.subList(rootIndex + 1, length);
        List<Integer> leftPostorder = postorder.subList(0, rootIndex);
        List<Integer> rightPostorder = postorder.subList(rootIndex, length - 1);
        root.left = createBinaryTreeByInPost(leftInorder, leftPostorder);
        root.right = createBinaryTreeByInPost(rightInorder, rightPostorder);
        return root;
    }
    
    /**
     * 根据前序遍历List<Integer>和后序遍历List<Integer>创建二叉树
     *
     * @param preorder  前序遍历List<Integer>
     * @param postorder 后序遍历List<Integer>
     * @return {@code TreeNode}
     */
    public static TreeNode createBinaryTreeByPrePost(List<Integer> preorder, List<Integer> postorder) {
        if (CollectionUtils.isEmpty(preorder, postorder)) {
            return null;
        }
        int length = preorder.size();
        if (length == 1) {
            return new TreeNode(preorder.get(0));
        }
        TreeNode root = new TreeNode(preorder.get(0));
        int rootIndex = preorder.indexOf(postorder.get(length - 2));
        List<Integer> leftPreorder = preorder.subList(1, rootIndex);
        List<Integer> rightPreorder = preorder.subList(rootIndex, length);
        List<Integer> leftPostorder = postorder.subList(0, rootIndex - 1);
        List<Integer> rightPostorder = postorder.subList(rootIndex - 1, length - 1);
        root.left = createBinaryTreeByPrePost(leftPreorder, leftPostorder);
        root.right = createBinaryTreeByPrePost(rightPreorder, rightPostorder);
        return root;
    }
    
    
    /**
     * 二叉树的广度优先遍历 打印输出
     */
    public static void printBreadthFirstTraversal(TreeNode root) {
        System.out.println(getBreadthFirstTraversal(root));
    }
    
    /**
     * 二叉树的深度优先遍历
     */
    public static void printDepthFirstTraversal(TreeNode root) {
        System.out.println(getDepthFirstTraversal(root));
    }
    
    /**
     * 二叉树的前序遍历
     */
    public static void printPreorderTraversal(TreeNode root) {
        System.out.println(getPreorderTraversalByRecursion(root));
    }
    
    /**
     * 二叉树的中序遍历
     */
    public static void printInorderTraversal(TreeNode root) {
        System.out.println(getInorderTraversalByRecursion(root));
    }
    
    /**
     * 二叉树的后序遍历
     */
    public static void printPostorderTraversal(TreeNode root) {
        System.out.println(getPostorderTraversalByRecursion(root));
    }
    
    /**
     * 二叉树的广度优先遍历
     *
     * @param root 二叉树的根节点
     * @return {@code List<Integer>}
     */
    public static List<Integer> getBreadthFirstTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 创建一个队列
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点入队
        queue.offer(root);
        List<Integer> list = new ArrayList<>(); // 用于存放遍历结果
        while (!queue.isEmpty()) {
            // 出队
            TreeNode node = queue.poll();
            list.add(node.val);
            // 如果左子节点不为空，则入队
            if (node.left != null) {
                queue.offer(node.left);
            }
            // 如果右子节点不为空，则入队
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return list;
    }
    
    /**
     * 二叉树的层序遍历
     *
     * @param root 二叉树的根节点
     * @return {@code List<List<Integer>>}
     */
    public static List<List<Integer>> getLevelTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 创建一个队列
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点入队
        queue.offer(root);
        List<List<Integer>> list = new ArrayList<>(); // 用于存放遍历结果
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                // 出队
                TreeNode node = queue.poll();
                subList.add(node.val);
                // 如果左子节点不为空，则入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                // 如果右子节点不为空，则入队
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(subList);
        }
        return list;
    }
    
    /**
     * 二叉树的锯齿形层序遍历
     */
    public static List<List<Integer>> getZigzagLevelTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isOrderLeft = true;
        while (!queue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(node.val);
                } else {
                    levelList.offerFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(new ArrayList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return list;
    }
    
    /**
     * 二叉树的深度优先遍历
     *
     * @param root 二叉树的根节点
     * @return {@code List<Integer>}
     */
    public static List<Integer> getDepthFirstTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        if (root.left != null) {
            list.addAll(getDepthFirstTraversal(root.left));
        }
        if (root.right != null) {
            list.addAll(getDepthFirstTraversal(root.right));
        }
        return list;
    }
    
    /**
     * 二叉树的深度优先遍历
     *
     * @param root 二叉树的根节点
     * @return {@code List<List<Integer>>}
     */
    public static List<List<Integer>> getDepthFirstTraversalByLevel(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        subList.add(root.val);
        list.add(subList);
        if (root.left != null) {
            list.addAll(getDepthFirstTraversalByLevel(root.left));
        }
        if (root.right != null) {
            list.addAll(getDepthFirstTraversalByLevel(root.right));
        }
        return list;
    }
    
    /**
     * 基于递归，二叉树的前序遍历
     *
     * @param root 二叉树的根节点
     * @return {@code List<Integer>}
     */
    public static List<Integer> getPreorderTraversalByRecursion(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        list.addAll(getPreorderTraversalByRecursion(root.left));
        list.addAll(getPreorderTraversalByRecursion(root.right));
        return list;
    }
    
    /**
     * 基于栈的迭代，二叉树的前序遍历
     *
     * @param root 二叉树的根节点
     * @return {@code List<Integer>}
     */
    public static List<Integer> getPreorderTraversalByStack(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }
    
    /**
     * 基于Mirrors，二叉树的前序遍历
     *
     * @param root 二叉树的根节点
     * @return {@code List<Integer>}
     */
    public static List<Integer> getPreorderTraversalByMirrors(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return list;
    }
    
    /**
     * 基于递归，二叉树的中序遍历
     *
     * @param root 二叉树的根节点
     * @return {@code List<Integer>}
     */
    public static List<Integer> getInorderTraversalByRecursion(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        list.addAll(getInorderTraversalByRecursion(root.left));
        list.add(root.val);
        list.addAll(getInorderTraversalByRecursion(root.right));
        return list;
    }
    
    /**
     * 基于栈的迭代，二叉树的中序遍历
     *
     * @param root 二叉树的根节点
     * @return {@code List<Integer>}
     */
    public static List<Integer> getInorderTraversalByStack(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
        return list;
    }
    
    /**
     * 基于Mirrors，二叉树的中序遍历
     *
     * @param root 二叉树的根节点
     * @return {@code List<Integer>}
     */
    public static List<Integer> getInorderTraversalByMirrors(TreeNode root) {
        // 检查根节点是否为空，如果为空，则返回一个空的 ArrayList
        if (root == null) {
            return new ArrayList<>();
        }
        
        // 初始化一个空的 ArrayList 用于存储遍历的结果
        List<Integer> list = new ArrayList<>();
        
        // 初始化两个 TreeNode 变量，cur 和 pre。cur 初始设置为树的根节点
        TreeNode cur = root;
        TreeNode pre;
        
        // 进入一个 while 循环，只要 cur 不为空就继续
        while (cur != null) {
            // 检查 cur 的左子节点是否为空
            if (cur.left == null) {
                // 如果为空，将 cur 的值添加到 list 中，并将 cur 移动到其右子节点
                list.add(cur.val);
                cur = cur.right;
            } else {
                // 如果 cur 的左子节点不为空，将 pre 设置为 cur 的左子节点，并进入另一个 while 循环
                pre = cur.left;
                while (pre.right != null) {
                    // 只要 pre 的右子节点不为空，就将 pre 移动到其右子节点
                    pre = pre.right;
                }
                // 在内部 while 循环之后，将 pre 的右子节点设置为 cur，将 cur 移动到其左子节点，并将原始 cur 的左子节点设置为 null
                pre.right = cur;
                TreeNode temp = cur;
                cur = cur.left;
                temp.left = null;
            }
        }
        // 在外部 while 循环之后，返回 list，它现在包含了二叉树的中序遍历
        return list;
    }
    
    /**
     * 基于栈，二叉树的中序遍历
     *
     * @param root 二叉树的根节点
     * @return {@code List<TreeNode>}
     */
    public static List<TreeNode> getInorderTraversalByStack2(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                list.add(node);
                node = node.right;
            }
        }
        return list;
    }
    
    /**
     * 基于递归，二叉树的后序遍历
     *
     * @param root 二叉树的根节点
     * @return {@code List<Integer>}
     */
    public static List<Integer> getPostorderTraversalByRecursion(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        list.addAll(getPostorderTraversalByRecursion(root.left));
        list.addAll(getPostorderTraversalByRecursion(root.right));
        list.add(root.val);
        return list;
    }
    
    /**
     * 基于栈的迭代，二叉树的后序遍历
     *
     * @param root 二叉树的根节点
     * @return {@code List<Integer>}
     */
    public static List<Integer> getPostorderTraversalByStack(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(0, node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return list;
    }
    
    /**
     * 计算二叉树的深度
     */
    public static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
    
    /**
     * 计算二叉树的节点数
     */
    public static int getNodeCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getNodeCount(root.left) + getNodeCount(root.right) + 1;
    }
    
    /**
     * 计算二叉搜索树的种数
     */
    public static int numTrees(int n) {
        // 如果节点数量为0或1，那么只能构成一种BST，所以返回1
        if (n == 0 || n == 1) {
            return 1;
        }
        
        // 初始化动态规划数组，长度为节点数量n+1
        int[] dp = new int[n + 1];
        
        // 当节点数量为0时，只有一种BST
        dp[0] = 1;
        
        // 对于每一个数量i（从1到n）
        for (int i = 1; i <= n; i++) {
            // 对于每一个数量j（从0到i）
            for (int j = 0; j < i; j++) {
                // dp[i]等于dp[j]和dp[i-j-1]的乘积的和
                // dp[j]表示左子树的可能BST数量
                // dp[i-j-1]表示右子树的可能BST数量
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        
        // 返回节点数量为n时的可能BST数量
        return dp[n];
    }
    
    /**
     * 生成所有由n个节点组成且节点值从1到n互不相同的不同二叉搜索树
     */
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }
    
    private static List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftList = generateTrees(start, i - 1);
            List<TreeNode> rightList = generateTrees(i + 1, end);
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;
    }
    
    static long pre = Long.MIN_VALUE;
    /**
     * 判断二叉树是否为有效二叉搜索树
     */
    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }
    
    /**
     * 交换两个节点的值
     */
    public static void swap(TreeNode a, TreeNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
    
    /**
     * 基于Mirrors，将一颗普通二叉树恢复为二叉搜索树，且不改变原来的树结构（只有两个节点有问题）
     */
    public static void recoverBSTTree(TreeNode root) {
        // 获取中序遍历的结果
        List<TreeNode> inorder = getInorderTraversalByStack2(root);
        // 使用stream，获取List中需要交换的两个节点
        TreeNode x = null, y = null;
        for (int i = 0; i < inorder.size() - 1; i++) {
            if (inorder.get(i).val > inorder.get(i + 1).val) {
                y = inorder.get(i + 1);
                if (x == null) {
                    x = inorder.get(i);
                } else {
                    break;
                }
            }
        }
        // 交换两个节点的值
        swap(x, y);
    }
    
    /**
     * 判断两颗二叉树是否相同，即结构相同且节点值相同
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    /**
     * 判断二叉树是否对称
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }
    
    private static boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
    
}
