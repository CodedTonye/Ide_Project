package stackCollection;

public class Stack {
    private String[] elements = new String[5];
    private int count = 0;

    public Stack() {
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int count() {
        return count;
    }

    public void push(String item) {
        elements[count++] = item;
    }

    public String pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot pop from empty stack");
        } else {
            String item = elements[--count];
            elements[count] = null;
            return item;
        }
    }
}
