package com.company.model;


public class MyQueue<T> {

    private Node<T> head;
    private static class Node<E> { // static because must not have access to external fields
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

    //Returns true if this queue contains no elements.
    public boolean isEmpty() {
        if(head == null) {
            return true;
        } else {
            return false;
        }
    }

    // add new element
    public void push(T data) {
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
    // return head
    public T peek() {
        if(head != null) {
            return head.data;
        }
        else {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        }
    }
    // return head and delete
    public T pop() {
        if(head != null) {
            var result = head.data;
            head = head.next;
            return result;
        } else {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        }
    }

    @Deprecated
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

