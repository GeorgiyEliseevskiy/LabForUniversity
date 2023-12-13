package com.company;

import com.company.model.SparseMatrix;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.company.Solution.eighthLab;
import static com.company.SubtreeFinder.*;


public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean flagWork = true;
        char answerForPanel;
        while (flagWork) {
            System.out.println("Hello. Choice necessary decision \n" +
                    "1- First Laboratory work" +
                    "\n2- Second Laboratory work" +
                    "\n3- Third Laboratory work" +
                    "\n4- Fourth Laboratory work" +
                    "\n5- Fifth Laboratory work" +
                    "\n6- Sixth Laboratory work");
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
                    for (int i = 0; i < countElementToDelete; i++) {
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

                case '4':
                    TreeNode root = buildTree();
                    System.out.println("Введите структуру поддерева для поиска:");
                    TreeNode target = buildTree();

                    List<TreeNode> matchingSubtrees = findSubtrees(root, target);
                    System.out.println("Совпадающие поддеревья:");
                    for (TreeNode subtree : matchingSubtrees) {
                        printTree(subtree, 0);
                        System.out.println();
                    }
                    flagWork = false;
                    break;

                case '5':
                    System.out.println("Input ");
                    MyHashTable<String, Integer> myHashTable = new MyHashTable<>();
                    System.out.print("Enter n elements into hashTable: ");
                    int n = inputIntWithValidation();
                    String phoneNumber;
                    int value;

                    for (int i = 0; i < n; i++) {
                        System.out.println("Enter the phone number: ");
                        phoneNumber = inputCorrectPhoneNumber();
                        System.out.println("Enter the value: ");
                        value = inputIsCorrect();
                        myHashTable.put(phoneNumber, value);
                    }
                    System.out.println("Ввидте ");
                    phoneNumber = inputCorrectPhoneNumber();
                    myHashTable.remove(phoneNumber);
                    myHashTable.printTable();

                    flagWork = false;
                    break;
                case '6':
                    System.out.println("Input size arr: ");
                    int size = inputIntWithValidation();
                    int[] arr = new int[size];

                    for (int i = 0; i < size; i++) {
                        System.out.print("Element " + (i + 1) + ": ");
                        arr[i] = scanner.nextInt();
                    }
                    Solution.quickSort(arr, 0, arr.length - 1);
                    Arrays.stream(arr).forEach(System.out::println);
                    flagWork = false;
                    break;
                case '7':
                    eighthLab();
                    flagWork = false;
                    break;
                default:
                    System.out.println("Incorrect number laboratory");
                    flagWork = false;
                    break;
            }
        }
    }

    // Entering and verifying the correct value
    public static int inputIntWithValidation() {
        int value = 0;
        boolean inputValid = false;

        while (!inputValid) {
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                inputValid = true;
            } else {
                System.out.println("Please, input the digit:");
                scanner.nextLine();
            }
        }

        return value;
    }

    // Entering and verifying the correct value
    // if digit: return value | else infinite while until the correct value is entered
    public static char inputIsCorrect() {
        String answerForPanel = scanner.nextLine();
        try {
            if (Character.isDigit(answerForPanel.charAt(0))) {
                return answerForPanel.charAt(0);
            } else {
                while (!Character.isDigit(answerForPanel.charAt(0))) {
                    System.out.println("Please, input the digit");
                    answerForPanel = scanner.nextLine();
                }
                return answerForPanel.charAt(0);
            }
        } catch (StringIndexOutOfBoundsException c) {
            System.out.println("bad data");
        }
        return '9';
    }




    // Entering and verifying the correct phone number with regex
    public static String inputCorrectPhoneNumber() {
        String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        String phoneNumber;

        do {
            System.out.println("Enter the phone number(10 digits):");
            phoneNumber = scanner.nextLine();
        }
        while (!pattern.matcher(phoneNumber).matches());

        return phoneNumber;
    }

}
