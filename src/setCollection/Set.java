package setCollection;

public class Set {
    private String[] elements;
    private int count;

    public Set(int size) {
        elements = new String[size];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean add(String element) {
        if (isDuplicate()) {
            throw new IllegalArgumentException("Duplicate element");
        } else {
            elements[count++] = element;
            return true;
        }
    }

    public boolean isDuplicate() {
        return false;
    }

    public void remove(String element) {
        elements[count--] = element;
    }
}
