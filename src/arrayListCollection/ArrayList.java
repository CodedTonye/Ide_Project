package arrayListCollection;

public class ArrayList {
    private int[] elements;
    private int count;

    public ArrayList(int size) {
        elements = new int[size];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void add(int element) {
        if (count == elements.length) {
            expandArrayWhenFull();
        }

        elements[count++] = element;
    }

    public void expandArrayWhenFull() {
        int[] newArray = new int[count * 2];

        for(int index = 0; index < elements.length; ++index) {
            newArray[index] = elements[index];
        }

        elements = newArray;
    }

    public void remove(int number) {
        elements[count--] = number;
    }

    public int length() {
        return count;
    }

    public int returnElementInSpecifiedIndex(int index) {
        return elements[index];
    }

    public int get(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Out of bound");
        } else {
            return elements[index];
        }
    }

    public int[] toArray() {
        return elements;
    }

    public int size() {
        return count;
    }

    public boolean contains(int element) {
        return true;
    }
}
