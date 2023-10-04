package com.company;

import com.company.model.SparseMatrix;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean flagWork = true;
        char answerForPanel;

       /* BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(2);
        binaryTree.insert(12);
        binaryTree.insert(1);
        binaryTree.insert(10);
        binaryTree.insert(9);

        binaryTree.printTree();
        int[] excludedNodses = {12};
        BinaryTree resultTree = binaryTree.findSubtreeExcludingNodes(excludedNodses);
        resultTree.printTree();*/

        while (flagWork) {
            System.out.println("Hello. Choice necessary decision \n" +
                    "1- First Laboratory work" +
                    "\n2- Second Laboratory work" +
                    "\n3- Third Laboratory work");
            answerForPanel = inputIsCorrect();

            switch (answerForPanel) {
                case '1':
                    System.out.println("Please, choosing an input method \n 1- keyboard input \n 2- file input");
                    answerForPanel = inputIsCorrect();
                    if (answerForPanel == '1') {
                        System.out.println("Please, input the text: ");
                        String text = scanner.nextLine();
                        Solution.solutionForFirstLab(text);
                    } else if (answerForPanel == '2') {

                        Path path = Paths.get("C:\\Users\\Valcorian\\Desktop\\FileForLab1.txt");
                        List<String> linesFromFile = new ArrayList<>();

                        try {
                            linesFromFile = Files.readAllLines(path, StandardCharsets.UTF_8);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String text = linesFromFile.stream().collect(Collectors.joining());
                        Solution.solutionForFirstLab(text);
                    }
                    flagWork = false; //TODO When adding new lab work, change
                    break;

                case '2':
                    int rows, row, columns, column;
                    System.out.println("Please, choosing an input method \n 1- keyboard input");
                    answerForPanel = inputIsCorrect();
                    if (answerForPanel == '1') {

                        System.out.println("Please, input the matrix size (rows, column)");
                        rows = inputIntWithValidation();
                        columns = inputIntWithValidation();
                        SparseMatrix sparseMatrix = new SparseMatrix(rows, columns);

                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < columns; j++) {
                                System.out.println("Введите значение для строки " + i + " и столбца " + j + ":");
                                int value = inputIntWithValidation();
                                sparseMatrix.addValue(i, j, value);
                            }
                        }

                        sparseMatrix.printMatrix();
                        System.out.println();
                        sparseMatrix.sortedMatrixByColumnSum();
                        flagWork = false;

                    }
                    break;

                case '3':
                    System.out.println("Input size binary tree: ");
                    int sizeBinaryTree = inputIntWithValidation();
                    int element = 0;

                    List<Integer> valueBinaryTree = new ArrayList<>(sizeBinaryTree);

                    // Enter value to arrBinaryTree
                    for (int i = 0; i < sizeBinaryTree; i++) {
                        System.out.println("Input value: ");
                        valueBinaryTree.add(inputIntWithValidation());
                    }

                    System.out.println("Input counts elementsToDelete: ");
                    int countElementToDelete = inputIntWithValidation();
                    int[] arrExcludeNodes = new int[countElementToDelete];

                    // input elements to delete and check validity(contains in valueBinaryTree)
                    for (int i = 0; i<countElementToDelete; i++) {
                        System.out.println("Input element which contains in tree: ");
                        element = inputIntWithValidation();

                        // Input while we'll'nt enter the correct value
                        while (!valueBinaryTree.contains(element)) {
                            System.out.println("Input element which contains in tree: ");
                            element = inputIntWithValidation();
                        }
                        arrExcludeNodes[i] = element;
                    }

                    Solution.solutionForThirdLab(valueBinaryTree.stream().mapToInt(i -> i).toArray(),
                                    arrExcludeNodes);

                    flagWork = false;
                    break;
            }
        }
    }

    // Entering and verifying the correct value
    private static int inputIntWithValidation() {
        int value = 0;
        boolean inputValid = false;

        while (!inputValid) {
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                inputValid = true;
            } else {
                System.out.println("Please, input the digit:");
                scanner.nextLine(); // Очистка буфера ввода
            }
        }

        return value;
    }

    // Entering and verifying the correct value
    // if digit: return value | else infinite while until the correct value is entered
    public static char inputIsCorrect() {
        String answerForPanel = scanner.nextLine();
        if(Character.isDigit(answerForPanel.charAt(0))) {
            return answerForPanel.charAt(0);
        }
        else {
            while (!Character.isDigit(answerForPanel.charAt(0))) {
                System.out.println("Please, input the digit");
                answerForPanel = scanner.nextLine();
            }
            return answerForPanel.charAt(0);
        }
    }

}
