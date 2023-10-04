package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

// Solution to the problem: Дана разреженная матрицы (CCS).
//Переставить столбцы в матрице по возрастанию сумм элементов в этих столбцах.

public class SparseMatrix {
    private ArrayList<Integer> values; // Array of values
    private ArrayList<Integer> rowIndices; //Array of line numbers
    private ArrayList<Integer> colIndices; // Array of column numbers
    private int numRows;
    private int numCols;

    public SparseMatrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        values = new ArrayList<>();
        rowIndices = new ArrayList<>();
        colIndices = new ArrayList<>();
    }

    // add value into matrix
    public void addValue(int row, int col, int value) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            throw new IllegalArgumentException("Invalid row or column index");
        }

        if (value != 0) {
            rowIndices.add(row);
            colIndices.add(col);
            values.add(value);
        }
    }


    public void sortedMatrixByColumnSum() {
        int[] columnSums = new int[numCols];

        // calculating the sum of elements in each column of matrix
        for (int i = 0; i < values.size(); i++) {
            int col = colIndices.get(i);
            int value = values.get(i);
            columnSums[col] += value;
        }

        // Creating an array of pairs (column index, sum of elements)
        Integer[] columnIndices = new Integer[numCols];
        for (int i = 0; i < numCols; i++) {
            columnIndices[i] = i;
        }

        // Sorting the array of pairs by column sums in ascending order
        Arrays.sort(columnIndices, Comparator.comparingInt(a -> columnSums[a]));

        int[][] rearrangedMatrix = new int[numRows][numCols];

        // We fill in a new matrix taking into account the permutation of columns
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int col = columnIndices[j];
                for (int k = 0; k < values.size(); k++) {
                    if (rowIndices.get(k) == i && colIndices.get(k) == col) {
                        rearrangedMatrix[i][j] = values.get(k);
                    }
                }
                System.out.print(rearrangedMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }




    public void printMatrix() {
        int currentIndex = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {

                if (currentIndex < values.size() && // Checking for non-zero elements
                        rowIndices.get(currentIndex) == i && // Checking for the current element belongs to the row
                        colIndices.get(currentIndex) == j) { // Checking for the current element belongs to the current column
                    System.out.print(values.get(currentIndex) + " ");
                    currentIndex++;
                } else {
                    System.out.print("0 ");
                }
            }


            System.out.println();
        }
    }
}
