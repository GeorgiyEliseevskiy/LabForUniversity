package com.company;

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

        while (flagWork) {
            System.out.println("Hello. Choice necessary decision \n" +
                    "1- First Laboratory work");
            answerForPanel = inputIsCorrect();
                switch (answerForPanel) {
                    case '1':
                        System.out.println("Please, choosing an input method \n 1- keyboard input \n 2- file input");
                        answerForPanel = inputIsCorrect();
                        if(answerForPanel == '1') {
                            System.out.println("Please, input the text: ");
                            String text = scanner.nextLine();
                            Solution.solutionForFirstLab(text);
                        }
                        else if(answerForPanel == '2') {

                            Path path = Paths.get("C:\\Users\\Valcorian\\Desktop\\FileForLab1.txt");
                            List<String> lines = new ArrayList<>();
                            try {
                                lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String text = lines.stream().collect(Collectors.joining());
                            Solution.solutionForFirstLab(text);
                        }
                        flagWork = false;
                        break;
                }
        }
    }

    public static char inputIsCorrect() {
        String answerForPanel = scanner.nextLine();
        if(Character.isDigit(answerForPanel.charAt(0))) {
            return answerForPanel.charAt(0);
        }
        else {
            while (!Character.isDigit(answerForPanel.charAt(0))) {
                System.out.println("Please, answer the digit");
                answerForPanel = scanner.nextLine();
            }
            return answerForPanel.charAt(0);
        }
    }
}
