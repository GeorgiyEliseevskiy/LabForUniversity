package com.company;

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
}

