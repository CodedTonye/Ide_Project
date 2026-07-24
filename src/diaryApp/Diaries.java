package diaryApp;

import java.util.ArrayList;
import java.util.List;

public class Diaries {

    private List<Diary> diaries;

    public Diaries() {
        this.diaries = new ArrayList<>();
    }

    public void add(String username, String password) {
        if (findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }
        diaries.add(new Diary(username, password));
    }

    public Diary findByUsername(String username) {
        for (Diary diary : diaries) {
            if (diary.getUsername().equals(username)) {
                return diary;
            }
        }
        return null;
    }

    public void delete(String username, String password) {
        Diary diary = findByUsername(username);
        if (diary != null && diary.checkPassword(password)) {
            diaries.remove(diary);
        }
    }

    public List<Diary> getDiaries() {
        return diaries;
    }

    public int size() {
        return diaries.size();
    }
}
