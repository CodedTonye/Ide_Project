package queueCollection;

import java.util.NoSuchElementException;

public class Queue {
    private String[] elements;
    private int count;
    private int index = 0;

    public Queue(int size) {
        elements = new String[size];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void shiftElementsToFront() {
        int newIndex = 0;

        for (int current = index; current < index + count; current++) {
            elements[newIndex++] = elements[current];
        }

        while (newIndex < elements.length) {
            elements[newIndex++] = null;
        }

        index = 0;
    }

    public boolean add(String element) {
        if (isFull()) {
            throw new IllegalArgumentException("Queue is currently full");
        }

        if (index + count == elements.length) {
            shiftElementsToFront();
        }

        elements[count++] = element;
        return true;

    }

    public boolean isFull() {
        return count == elements.length;
    }

    public boolean offer(String element) {
        if (isFull()) {
            return false;
        } else {
            elements[count++] = element;
            return true;
        }
    }

    public String remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is currently empty");
        } else {
            String removedString = elements[index];
            elements[index++] = null;
            --count;
            return removedString;
        }
    }

    public String poll() {
        if (isEmpty()) {
            return null;
        } else {
            String removedString = elements[index];
            elements[index++] = null;
            --count;
            return removedString;
        }
    }

    public String element() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is currently empty");
        } else {
            return elements[index];
        }
    }

    public String peek() {
        return isEmpty() ? null : elements[index];
    }
}
