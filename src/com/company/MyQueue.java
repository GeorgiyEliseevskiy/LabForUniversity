package com.company;


import java.util.Optional;

public class MyQueue<T> {

    private Node<T> head;

    private class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            if (data == null) {
                throw new IllegalArgumentException("Значение не может быть null");
            }
            this.data = data;
            this.next = null;
        }
    }

    //Returns true if this list contains no elements.
    public boolean isEmpty() {
        if(head == null) {
            return true;
        } else {
            return false;
        }
    }

    public void push(T data) { // add new element
        Node<T> newNode = new Node<>(data);
        Node<T> currentNode = head;

        if(head == null) { // Checking the empties of the queue
            head = newNode;
        } else {
            while(currentNode.next != null) { // Looking for the end of the queue and add new element
                currentNode = currentNode.next;
            } currentNode.next = newNode;
        }
    }

    public T peek() { // return head
        if(head != null) {
            return head.data;
        }
        else {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        }
    }

    public T pop() { // return head and delete
        if(head != null) {
            var result = head.data;
            head = head.next;
            return result;
        } else {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        }
    }

    public void printQueue() {
        Node<T> currentNode = head;

        if (head != null) {
            while (currentNode != null) {
                System.out.println(currentNode.data);
                currentNode = currentNode.next;
            }
        }
    }
}

