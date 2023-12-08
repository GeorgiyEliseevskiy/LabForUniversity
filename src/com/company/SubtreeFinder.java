package com.company;
import java.util.*;

class  TreeNode {
    int val;
    List<TreeNode> children;

    TreeNode(int val) {
        this.val = val;
        children = new ArrayList<>();
    }
}

// 14 Дано N-дерево. Найти все поддеревья, структура которых совпадает с заданной.
public class SubtreeFinder {

    //Метод проверяет, совпадают ли два дерева s и t в структуре и значениях их узлов.
    // Если структура и значения узлов совпадают, метод возвращает true, в противном случае - false.
    public static boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        if (s.children.size() != t.children.size()) {
            return false;
        }
        for (int i = 0; i < s.children.size(); i++) {
            if (!isSameTree(s.children.get(i), t.children.get(i))) {
                return false;
            }
        }
        return true;
    }

    // метод находит все поддеревья в дереве root, которые имеют структуру, совпадающую с деревом target.
    // Результат представлен в виде списка узлов TreeNode, представляющих найденные поддеревья.
    public static List<TreeNode> findSubtrees(TreeNode root, TreeNode target) {
        List<TreeNode> result = new ArrayList<>();
        findSubtreesRecursive(root, target, result);
        return result;
    }

    // Рекурсивный метод, используемый методом findSubtrees. Он проверяет, совпадает ли структура поддерева,
    // начиная с узла node, с заданным поддеревом target.
    // Если совпадение найдено, узел node добавляется в список результатов result.
    private static void findSubtreesRecursive(TreeNode node, TreeNode target, List<TreeNode> result) {
        if (node == null) {
            return;
        }

        if (isSameTree(node, target)) {
            result.add(node);
        }

        for (TreeNode child : node.children) {
            findSubtreesRecursive(child, target, result);
        }
    }

    //запрашивает корневое значение и количество детей для корневого узла,
    // а затем запрашивает значения для каждого дочернего узла и строит дерево,
    // используя объекты TreeNode. Возвращает корневой узел построенного дерев
    public static TreeNode buildTree() {
        System.out.print("Введите значение корневого узла: ");
        int rootValue = Main.inputIntWithValidation();
        TreeNode root = new TreeNode(rootValue);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            System.out.print("Введите количество детей для узла " + currentNode.val + ": ");
            int numChildren = Main.inputIntWithValidation();

            for (int i = 0; i < numChildren; i++) {
                System.out.print("Введите значение для дочернего узла " + (i + 1) + " узла " + currentNode.val + ": ");
                int childValue = Main.inputIntWithValidation();;
                TreeNode childNode = new TreeNode(childValue);
                currentNode.children.add(childNode);
                queue.add(childNode);
            }
        }

        return root;
    }

    public static void printTree(TreeNode node,  int depth) {
        if (node == null) {
            return;
        }
        // Print current node value with indentation based on the depth
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println(node.val);

        // Recursively print children with increased depth
        for (TreeNode child : node.children) {
            printTree(child, depth + 1);
        }
    }
}

