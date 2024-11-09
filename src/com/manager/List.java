package com.manager;

import java.util.Iterator;

/**
 * Represents a list of elements of type E.
 * 
 * @param <E> the type of elements in the list
 * 
 * @author Surya Bhardwaj
 * @author Harsh Singh
 */
public class List<E> implements Iterable<E> {
    /** The array of elements in the list. */
    private E[] objects;
    /** The number of elements in the list. */
    private int size;
    /** The initial capacity of the list. */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Creates an empty list with an initial capacity of 10.
     */
    @SuppressWarnings("unchecked")
    public List() {
        objects = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Find the index of the element e in the list.
     * 
     * @param e the element to search for
     * @return the index of the element e in the list, or -1 if not found
     */
    private int find(E e) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Grows the list capacity when needed.
     */
    @SuppressWarnings("unchecked")
    private void grow() {
        E[] newObjects = (E[]) new Object[objects.length * 2];
        for (int i = 0; i < size; i++) {
            newObjects[i] = objects[i];
        }
        objects = newObjects;
    }

    /**
     * Returns true if the list contains the specified element.
     * 
     * @param e the element to check for
     * @return true if the list contains the specified element
     */
    public boolean contains(E e) {
        return find(e) != -1;
    }

    /**
     * Adds an element to the end of the list.
     * 
     * @param element the element to add
     */
    public void add(E element) {
        if (size == objects.length)
            grow();
        objects[size++] = element;
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     * 
     * @param e the element to remove
     */
    public void remove(E e) {
        int index = find(e);
        if (index != -1) {
            for (int i = index; i < size - 1; i++)
                objects[i] = objects[i + 1];

            objects[--size] = null;
        }
    }

    /**
     * Returns true if the list is empty.
     * 
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in the list.
     * 
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns the element at the specified index.
     * 
     * @param index the index of the element to return
     * @return the element at the specified index
     */
    public E get(int index) {
        if (index < 0 || index >= size)
            return null;

        return objects[index];
    }

    /**
     * Sets the element at the specified index to the specified element.
     * 
     * @param index the index of the element to set
     * @param e     the element to set
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            return;

        objects[index] = e;
    }

    /**
     * Returns the index of the element e, or -1 if not found.
     * 
     * @param e the element to search for
     * @return the index of the element e in the list, or -1 if not found
     */
    public int indexOf(E e) {
        return find(e);
    }

    /**
     * An iterator for all of the elements in the list.
     * 
     * @return an iterator over the elements in the list
     */
    @Override
    public java.util.Iterator<E> iterator() {
        return new java.util.Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                return get(currentIndex++);
            }
        };
    }

    /**
     * Sorts the list using the specified comparator.
     * 
     * @param comparator the comparator to use for sorting
     */
    public void sort(java.util.Comparator<? super E> comparator) {
        java.util.Arrays.sort((E[]) objects, 0, size, comparator);
    }

    /**
     * Inner class to provide the ListIterator.
     */
    @SuppressWarnings("unused")
    private class ListIterator implements Iterator<E> {
        /** The index of the current element in the list. */
        private int currentIndex = 0;

        /**
         * Returns true if there are more elements in the list.
         * 
         * @return true if there are more elements in the list
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Returns the next element in the list.
         * 
         * @return the next element in the list
         */
        @Override
        public E next() {
            if (!hasNext()) {
                return null;
            }
            E element = objects[currentIndex++];
            if (currentIndex >= size) {
                currentIndex = 0;
            }
            return element;
        }
    }
}
