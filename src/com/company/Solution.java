package com.company;

import com.company.model.BinaryTree;
import com.company.model.MyQueue;

public class Solution {

    // Solution to the problem:
    // "Используя очередь, отредактировать текст, оставляя один пробел в каждой серии пробелов"
    public static void solutionForFirstLab(String text) throws IllegalArgumentException, ArrayIndexOutOfBoundsException{

        MyQueue<Character> queue = new MyQueue<>();

        for(Character c : text.toCharArray()) {
            queue.push(c);
        }

        StringBuilder result = new StringBuilder();
        boolean inSpaceFlag = false;

        //If you find a space and the flag is true, delete all to the first character.
        // If the flag is false, add to the builder
        // Else add to the builder
        while (!queue.isEmpty()) {
            if(queue.peek() == ' ') {
                if(inSpaceFlag) {
                    while (queue.peek() == ' ') {
                        queue.pop();
                    }
                    inSpaceFlag = false;
                } else {
                    result.append(queue.pop());
                    inSpaceFlag = true;
                }
            } else {
                result.append(queue.pop());
                inSpaceFlag = false;
            }
        }
        System.out.println(result.toString());
    }

    // Solution to the problem:
    // Дано бинарное дерево. Найти поддерево не включающее ни одну из заданных вершин.
    public static void solutionForThirdLab(int[] valueBinaryTree, int[] excludedNodes) {
        BinaryTree binaryTree = new BinaryTree();

        for(int i : valueBinaryTree) {
            binaryTree.insert(i);
        }
        binaryTree.printTree();

        BinaryTree resultTree = binaryTree.findSubtreeExcludingNodes(excludedNodes);
        resultTree.printTree();
    }


    // Solution to the problem:
    // Задача 14. Реализовать «быструю» сортировку
    public static void quickSort(int[]arr, int left, int right) {

        if(arr.length==0 || left >= right) {
            return;
        }

        int pivot = arr[(left + right)/2];
        int leftMarker = left;

        int rightMarker = right;

        // Перекладываем элементы вправо или влево от опорного
        while (leftMarker <= rightMarker) {

            while (arr[leftMarker] < pivot) leftMarker++;
            while (arr[rightMarker] > pivot) rightMarker--;

            // Если слева и справа не соответствие и левый меньше правого,
            // то меняем элементы местами
            if(leftMarker<= rightMarker) {
                int temp = arr[leftMarker];
                arr[leftMarker] = arr[rightMarker];
                arr[rightMarker] = temp;

                leftMarker++;
                rightMarker--;
            }
        }

        //3 Рекурсия для сортировки левой и правой части
        if(left < rightMarker) {
            quickSort(arr, left, rightMarker);
        }
        if(right > leftMarker) {
            quickSort(arr, leftMarker, right);
        }

    }

}

