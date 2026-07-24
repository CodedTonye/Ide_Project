package mapCollection;

public class Map {
    private String[] keys = new String[10];
    private String[] values = new String[10];
    private int count = 0;

    public Map() {
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void put(String key, String value) {
        for(int index = 0; index < count; ++index) {
            if (keys[index].equals(key)) {
                values[index] = value;
                return;
            }
        }

        keys[count] = key;
        values[count] = value;
        ++count;
    }

    public void remove(String key) {
        for(int index = 0; index < count; ++index) {
            if (keys[index].equals(key)) {
                for(int element = index; element < count - 1; ++element) {
                    keys[element] = keys[element + 1];
                    values[element] = values[element + 1];
                }

                --count;
                return;
            }
        }

    }

    public boolean containsKey(String key) {
        for(int index = 0; index < count; ++index) {
            if (keys[index].equals(key)) {
                return true;
            }
        }

        return false;
    }

    public String get(String key) {
        for(int index = 0; index < count; ++index) {
            if (keys[index].equals(key)) {
                return values[index];
            }
        }

        return null;
    }

    public int size() {
        return count;
    }
}
