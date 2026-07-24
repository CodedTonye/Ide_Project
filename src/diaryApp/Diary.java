package diaryApp;

import java.util.ArrayList;
import java.util.List;

public class Diary {

    private String username;
    private String password;
    private boolean isLocked;
    private List<Entry> entries;

    // Simple incrementing id generator for new entries
    private int nextEntryId = 1;

    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
        this.isLocked = true; // diaries start locked
        this.entries = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void unlockDiary(String password) {
        if (checkPassword(password)) {
            this.isLocked = false;
        }
    }

    public void lockDiary() {
        this.isLocked = true;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void createEntry(String title, String body) {
        requireUnlocked();
        Entry entry = new Entry(nextEntryId, title, body);
        nextEntryId++;
        entries.add(entry);
    }

    public void deleteEntry(int id) {
        requireUnlocked();
        Entry entry = findEntryById(id);
        if (entry != null) {
            entries.remove(entry);
        }
    }

    public Entry findEntryById(int id) {
        for (Entry entry : entries) {
            if (entry.getId() == id) {
                return entry;
            }
        }
        return null;
    }

    public void updateEntry(int id, String title, String body) {
        requireUnlocked();
        Entry entry = findEntryById(id);
        if (entry != null) {
            entry.setTitle(title);
            entry.setBody(body);
        }
    }

    public List<Entry> getEntries() {
        return entries;
    }

    private void requireUnlocked() {
        if (isLocked) {
            throw new IllegalStateException("Diary is locked. Unlock it first.");
        }
    }
}
