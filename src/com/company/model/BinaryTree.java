package com.company.model;

public class BinaryTree {

    private static class TreeNode { // static because must not have access to external fields
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private TreeNode root;

    public BinaryTree() {
        root = null;
    }

    // Recursive method to add a new element
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // root - current element
    // if root = null than end to binary tree
    // Recursively decide which subtree to insert
    // return root
    private TreeNode insertRec(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    // Arguments: int[] excludedNodes - elements under the tree to be deleted
    public BinaryTree findSubtreeExcludingNodes(int[] excludedNodes) {
        BinaryTree resultTree = new BinaryTree();
        resultTree.root = cloneSubtreeExcludingNodes(root, excludedNodes);
        return resultTree;
    }

    private TreeNode cloneSubtreeExcludingNodes(TreeNode currentNode, int[] excludedNodes) {
        if (currentNode == null) {
            return null;
        }

        // Checking the current element is included in the exceptions
        boolean isExcluded = false;
        for (int excludedNode : excludedNodes) {
            if (currentNode.data == excludedNode) {
                isExcluded = true;
                break;
            }
        }

        // If the element is not included in the exceptions,
        // we recursively copy it under the tree
        if (!isExcluded) {
            TreeNode clonedNode = new TreeNode(currentNode.data);
            clonedNode.left = cloneSubtreeExcludingNodes(currentNode.left, excludedNodes);
            clonedNode.right = cloneSubtreeExcludingNodes(currentNode.right, excludedNodes);
            return clonedNode;
        } else {
            // If element is included in the exceptions,
            // we return null to exclude it from the result
            return null;
        }
    }


    public void printTree() {
        System.out.println("Древовидное представление дерева:");
        printTreeRec(root, 0);
    }

    // Recursive print
    private void printTreeRec(TreeNode currentNode, int level) {
        if (currentNode != null) {
            // Печать уровней
            for (int i = 0; i < level; i++) {
                System.out.print("  ");
            }
            System.out.println(currentNode.data);
            // Recursive call for left and right subtrees
            printTreeRec(currentNode.left, level + 1);
            printTreeRec(currentNode.right, level + 1);
        }
    }


}


