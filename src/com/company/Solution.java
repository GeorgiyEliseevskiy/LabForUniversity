package com.company;

import com.company.model.BinaryTree;
import com.company.model.MyQueue;

import java.util.Scanner;

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

    public static void eighthLab() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter H: ");
        int H = scanner.nextInt();

        System.out.print("Enter W: ");
        int W = scanner.nextInt();

        System.out.print("Enter trees quantity: ");
        int tree = scanner.nextInt();

        System.out.println("\n\n");

        char[][] M = new char[H][W];

        int count = H * W;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                M[i][j] = '.';
            }
        }

        while (count >= 0) {
            int i_index = (int) (Math.random() * H);
            int j_index = (int) (Math.random() * W);

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (i == i_index && j == j_index) {
                        if (M[i][j] != 'O') {
                            M[i][j] = 'O';
                            tree--;
                            break;
                        } else {
                            count++;
                            break;
                        }
                    }
                }
            }
            if (tree == 0)
                break;
            count--;
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (M[i][j] == 'O') {
                    count = 0;
                    if (i - 1 >= 0) {
                        if (i - 2 >= 0) {
                            if (M[i - 2][j] == '#')
                                count++;
                            if (j - 1 >= 0) {
                                if (M[i - 2][j - 1] == '#')
                                    count++;
                            }
                            if (j + 1 < W) {
                                if (M[i - 2][j + 1] == '#')
                                    count++;
                            }
                        }
                        if (j + 1 < W) {
                            if (M[i - 1][j + 1] == '#' || M[i][j + 1] == '#')
                                count++;
                        }
                        if (j - 1 >= 0) {
                            if (M[i - 1][j - 1] == '#' || M[i][j - 1] == '#')
                                count++;
                        }
                        if (count == 0) {
                            M[i - 1][j] = '#';
                            j++;
                            if (j == W)
                                break;
                        }
                    }
                }
                if (M[i][j] == 'O') {
                    count = 0;
                    if (j + 1 < W) {
                        if (j + 2 < W) {
                            if (M[i][j + 2] == '#')
                                count++;
                            if (i - 1 >= 0) {
                                if (M[i - 1][j + 2] == '#')
                                    count++;
                            }
                            if (i + 1 < H) {
                                if (M[i + 1][j + 2] == '#')
                                    count++;
                            }
                        }
                        if (i - 1 >= 0) {
                            if (M[i - 1][j + 1] == '#' || M[i - 1][j] == '#')
                                count++;
                        }
                        if (i + 1 < H) {
                            if (M[i + 1][j + 1] == '#' || M[i + 1][j] == '#')
                                count++;
                        }
                        if (count == 0) {
                            M[i][j + 1] = '#';
                            j++;
                            if (j == W)
                                break;
                        }
                    }
                }
                if (M[i][j] == 'O') {
                    count = 0;
                    if (i + 1 < H) {
                        if (i + 2 < H) {
                            if (M[i + 2][j] == '#')
                                count++;
                            if (j - 1 >= 0) {
                                if (M[i + 2][j - 1] == '#')
                                    count++;
                            }
                            if (j + 1 < W) {
                                if (M[i + 2][j + 1] == '#')
                                    count++;
                            }
                        }
                        if (j - 1 >= 0) {
                            if (M[i + 1][j - 1] == '#' || M[i][j - 1] == '#')
                                count++;
                        }
                        if (j + 1 < W) {
                            if (M[i + 1][j + 1] == '#' || M[i][j + 1] == '#')
                                count++;
                        }
                        if (count == 0) {
                            M[i + 1][j] = '#';
                            j++;
                            if (j == W)
                                break;
                        }
                    }
                }
                if (M[i][j] == 'O') {
                    count = 0;
                    if (j - 1 >= 0) {
                        if (j - 2 >= 0) {
                            if (M[i][j - 2] == '#')
                                count++;
                            if (i - 1 >= 0) {
                                if (M[i - 1][j - 2] == '#')
                                    count++;
                            }
                            if (i + 1 < H) {
                                if (M[i + 1][j - 2] == '#')
                                    count++;
                            }
                        }
                        if (i - 1 >= 0) {
                            if (M[i - 1][j - 1] == '#' || M[i - 1][j] == '#')
                                count++;
                        }
                        if (i + 1 < H) {
                            if (M[i + 1][j - 1] == '#' || M[i + 1][j] == '#')
                                count++;
                        }
                        if (count == 0) {
                            M[i][j - 1] = '#';
                            j++;
                            if (j == W)
                                break;
                        }
                    }


                }
            }
        }

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                if (M[j][i] == '#')
                    count++;
            }
            System.out.printf("%4d", count);
            count = 0;
        }
        System.out.println("\n");

        for (int i = 0; i < W; i++)
            System.out.print("====");

        System.out.println("\n");

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.printf("%4s", M[i][j]);
            }
            for (int j = 0; j < W; j++) {
                if (M[i][j] == '#')
                    count++;
            }
            System.out.printf("  -- %d", count);
            count = 0;
            System.out.println("\n\n");
        }

        scanner.close();
    }

}

