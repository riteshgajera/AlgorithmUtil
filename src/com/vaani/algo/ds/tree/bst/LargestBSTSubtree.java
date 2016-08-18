package com.vaani.algo.ds.tree.bst;

import com.vaani.algo.ds.core.TreeNode;

/**
 * Given a binary tree,
 * find the largest subtree which is a Binary Search Tree (BST),
 * where largest means subtree with largest number of nodes in it.
 */
public class LargestBSTSubtree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode node5 = new TreeNode(5);
        TreeNode node15 = new TreeNode(15);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node15.right = node7;
        TreeNode node3 = new TreeNode(3);
        TreeNode node12 = new TreeNode(12);
        TreeNode node11 = new TreeNode(11);
        TreeNode node13 = new TreeNode(13);
        TreeNode node14 = new TreeNode(14);

        root.insertForBst(node5);
        root.insertForBst(node6);
        root.insertForBst(node15);
        root.insertForBst(node3);
        root.insertForBst(node12);
        root.insertForBst(node11);
        root.insertForBst(node13);
        root.insertForBst(node14);

        int result = largestBSTSubtree(root);
        System.out.println("largest bst (may not include all children): " + result);

        LargestBST largeBST = largestBSTSubtree1(root);
        if (largeBST != null)
            System.out.println(largeBST.node.val + " : size " + largeBST.maxNode);

        LargestBST largeBST2 = largestBSTSubtree2(root);
        if (largeBST2 != null)
            System.out.println(largeBST2.node.val + " : size " + largeBST2.maxNode);
    }

    // Given a binary tree, find the largest Binary Search Tree (BST),
    // where largest means BST with largest number of nodes in it.
    // The largest BST may or may not include all of its descendants.
    public static int largestBSTSubtree(TreeNode node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;

        int leftNode = largestBSTSubtree(node.left);
        int rightNode = largestBSTSubtree(node.right);

        if (node.left != null && node.right != null) {
            if ((node.left.val < node.val) && (node.right.val > node.val)) {
                return leftNode + rightNode + 1;
            } else if (node.left.val < node.val) {
                return leftNode + 1;
            } else if (node.right.val > node.val) {
                return rightNode + 1;
            } else {
                return Math.max(rightNode, leftNode);
            }
        } else if (node.left != null) {
            if (node.left.val < node.val)
                return leftNode + 1;
            else
                return leftNode;
        } else {// if (node.right != null){
            if (node.val < node.right.val)
                return rightNode + 1;
            else
                return rightNode;
        }
    }

    // Given a binary tree, find the largest Binary Search Tree (BST),
    // where largest means BST with largest number of nodes in it.
    // The largest BST may or may not include all of its descendants.
    public static LargestBST largestBSTSubtree1(TreeNode node) {
        if (node == null)
            return null;
        if (node.left == null && node.right == null) {
            return new LargestBST(node, node.size(), node.val, node.val);
        }

        LargestBST leftNode = largestBSTSubtree1(node.left);
        LargestBST rightNode = largestBSTSubtree1(node.right);

        if (leftNode != null && rightNode != null) {
            if ((node.val > leftNode.max && node.left == leftNode.node)
                    && (node.val < rightNode.min && node.right == rightNode.node)) {

                LargestBST bst = new LargestBST(node,
                        leftNode.maxNode + rightNode.maxNode + 1,
                        leftNode.min,
                        rightNode.max);

                return bst;
            } else if (node.val > leftNode.max && node.left == leftNode.node) {
                return new LargestBST(node, leftNode.maxNode + 1, leftNode.min, node.val);

            } else if (node.val < rightNode.min && node.right == rightNode.node) {
                return new LargestBST(node, rightNode.maxNode + 1, node.val, rightNode.max);
            } else {
                return (leftNode.maxNode > rightNode.maxNode) ? leftNode : rightNode;
            }
        } else if (leftNode != null) {
            if (node.val > leftNode.max && node.left == leftNode.node) {
                return new LargestBST(node, leftNode.maxNode + 1, leftNode.min, node.val);
            } else {
                return leftNode;
            }

        } else if (rightNode != null) {
            if (node.val < rightNode.min && node.right == rightNode.node) {
                return new LargestBST(node, rightNode.maxNode + 1, node.val, rightNode.max);
            } else {
                return rightNode;
            }
        }

        return null;
    }

    // Given a binary tree, find the largest Binary Search Tree (BST),
    // where largest means BST with largest number of nodes in it.
    // The largest BST must include all of its descendants.
    public static LargestBST largestBSTSubtree2(TreeNode node) {
        if (node == null)
            return null;
        if (node.left == null && node.right == null) {
            return new LargestBST(node, node.size(), node.val, node.val);
        }

        LargestBST leftNode = largestBSTSubtree2(node.left);
        LargestBST rightNode = largestBSTSubtree2(node.right);

        if (leftNode != null && rightNode != null) {
            if ((node.val > leftNode.max && node.left == leftNode.node)
                    && (node.val < rightNode.min && node.right == rightNode.node)) {

                LargestBST bst = new LargestBST(node,
                        leftNode.maxNode + rightNode.maxNode + 1,
                        leftNode.min,
                        rightNode.max);

                return bst;
            } else {
                return (leftNode.maxNode > rightNode.maxNode) ? leftNode : rightNode;
            }
        } else if (leftNode != null) {
            if (node.val > leftNode.max && node.left == leftNode.node) {
                return new LargestBST(node, leftNode.maxNode + 1, leftNode.min, node.val);
            } else {
                return leftNode;
            }

        } else if (rightNode != null) {
            if (node.val < rightNode.min && node.right == rightNode.node) {
                return new LargestBST(node, rightNode.maxNode + 1, node.val, rightNode.max);
            } else {
                return rightNode;
            }
        }
        return null;
    }

    public static class LargestBST {
        public TreeNode node;
        public int maxNode;
        public int min;
        public int max;

        public LargestBST(TreeNode n, int number, int min_value, int max_value) {
            node = n;
            max = max_value;
            min = min_value;
            maxNode = number;
        }
    } // end of class LargestBST


}
