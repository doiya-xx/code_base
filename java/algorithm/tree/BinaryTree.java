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
     * 二叉树的广度优先遍历
     *
     * @param root 二叉树的根节点
     * @return {@code List<List<Integer>>}
     */
    public static List<List<Integer>> getBreadthFirstTraversalByLevel(TreeNode root) {
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
     * 二叉树的深度优先遍历
     */
    public static void printDepthFirstTraversal(TreeNode root) {
        System.out.println(getDepthFirstTraversal(root));
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
     *
     */
}
