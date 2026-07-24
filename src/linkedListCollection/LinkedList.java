package linkedListCollection;

public class LinkedList {
    private Node head;
    private int size;

    public LinkedList() {
    }

    public void add(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node current;
            for(current = head; current.next != null; current = current.next) {
            }

            current.next = newNode;
        }

        ++size;
    }

    public int get(int index) {
        validateIndex(index);
        Node current = head;

        for(int count = 0; count < index; ++count) {
            current = current.next;
        }

        return current.value;
    }

    public void remove(int value) {
        if (isEmpty()) {
            throw new IllegalArgumentException("Value not found");
        } else if (head.value == value) {
            head = head.next;
            --size;
        } else {
            for(Node current = head; current.next != null; current = current.next) {
                if (current.next.value == value) {
                    current.next = current.next.next;
                    --size;
                    return;
                }
            }

            throw new IllegalArgumentException("Value not found");
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private static class Node {
        private final int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
