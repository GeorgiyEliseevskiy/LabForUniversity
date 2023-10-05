package com.company;

import java.nio.charset.StandardCharsets;

public class MyHashTable<K, V> {

    private static class Entry<K, V> { // Bucket Implementation
        K key;
        V value;
        boolean deleted;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            deleted = false;
        }
    }

    //  default table capacity
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    // If the table is filled by more than 70, then it expands
    private static final double DEFAULT_LOAD_FACTOR = 0.7;
    // arr buckets
    private Entry<K, V>[] table;
    private int size;

    public MyHashTable() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public MyHashTable(int initialCapacity) {
        table = new Entry[initialCapacity];
        size = 0;
    }

    // Implementation merge hash code
    // 1- transformation to byte[]
    // 2- The current hash value is shifted 8 bits to the left
    // The keyBytes[i] byte is "truncated" to the lower 8 bits
    // to get a value in the range from 0 to 255 (0xFF in binary form)
    // The results of the shift and the "trimmed" byte are combined
    private int myHashCode(K key) {
        byte[] keyBytes = key.toString().getBytes(StandardCharsets.UTF_8);
        int hash = 0;
        for (int i = 0; i < keyBytes.length; i++) {
            hash = (hash << 8) | (keyBytes[i] & 0xFF);
        }
        return hash;
    }

    // Entering new data into the table
    //
    public void put(K key, V value) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        // Checking the capacity of the table
        if (size >= table.length * DEFAULT_LOAD_FACTOR) {
            resize();
        }

        int hash = myHashCode(key);
        // The initial index is calculated
        int index = hash % table.length;
        int originalIndex = index;
        int i = 1;

        //Trying to find an empty bucket
        while (table[index] != null && !table[index].deleted) {

            // Open addressing
            index = (originalIndex + i * i) % table.length;
            i++;
        }
        // Put
        table[index] = new Entry<>(key, value);
        size++;
    }

    // Entering new data into the table
    // WARNING, this method can return null
    public V get(K key) {
        int hash = myHashCode(key);
        int index = hash % table.length;
        int originalIndex = index;
        int i = 1;

        while (table[index] != null) {
            if (!table[index].deleted && table[index].key.equals(key)) {
                return table[index].value;
            }
            // Open addressing
            index = (originalIndex + i * i) % table.length;
            i++;
        }
        // If the key is not contained in the table, return NULL
        return null;
    }

    // Delete element from table. Change flag deleted to true
    public void remove(K key) {
        int hash = myHashCode(key);
        int index = hash % table.length;
        int originalIndex = index;
        int i = 1;

        while (table[index] != null) {
            if (!table[index].deleted && table[index].key.equals(key)) {
                table[index].deleted = true;
                size--;
                return;
            }
            // Open addressing
            index = (originalIndex + i * i) % table.length;
            i++;
        }
    }

    // Changing size hashTable
    private void resize() {

        Entry<K, V>[] newTable = new Entry[table.length * 2];

        // Checking every element and calculating a new hash code and put into new table
        for (Entry<K, V> entry : table) {
            if (entry != null && !entry.deleted) {
                int hash = myHashCode(entry.key);
                int index = hash % newTable.length;
                int originalIndex = index;
                int i = 1;

                while (newTable[index] != null) {
                    // Open addressing
                    index = (originalIndex + i * i) % newTable.length;
                    i++;
                }

                newTable[index] = entry;
            }
        }
        table = newTable;
    }

    // Print hashTable
    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> entry = table[i];
            if (entry != null && !entry.deleted) {
                System.out.println("Index " + i + ": Key = " + entry.key + ", Value = " + entry.value);
            }
        }
    }
}



