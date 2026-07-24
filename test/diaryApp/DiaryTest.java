package diaryApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryTest {

    private Diary diary;

    @BeforeEach
    public void setUp() {
        diary = new Diary("alice", "secret123");
    }

    @Test
    public void newDiary_StartsLockedTest() {
        assertTrue(diary.isLocked());
    }

    @Test
    public void unlockDiary_CorrectPasswordUnlocksItTest() {
        diary.unlockDiary("secret123");
        assertFalse(diary.isLocked());
    }

    @Test
    public void unlockDiary_WrongPasswordStaysLockedTest() {
        diary.unlockDiary("wrongPassword");
        assertTrue(diary.isLocked());
    }

    @Test
    public void lockDiary_LocksUnlockedDiaryTest() {
        diary.unlockDiary("secret123");
        diary.lockDiary();
        assertTrue(diary.isLocked());
    }

    @Test
    public void createEntry_FailsWhenDiaryIsLockedTest() {
        assertThrows(IllegalStateException.class,
                () -> diary.createEntry("Title", "Body"));
    }

    @Test
    public void createEntry_AddsEntryWhenUnlockedTest() {
        diary.unlockDiary("secret123");
        diary.createEntry("My Day", "It was a good day.");
        assertEquals(1, diary.getEntries().size());
    }

    @Test
    public void findEntryById_ReturnsCorrectEntryTest() {
        diary.unlockDiary("secret123");
        diary.createEntry("My Day", "It was a good day.");
        Entry found = diary.findEntryById(1);
        assertNotNull(found);
        assertEquals("My Day", found.getTitle());
    }

    @Test
    public void findEntryById_ReturnsNullWhenNotFoundTest() {
        diary.unlockDiary("secret123");
        assertNull(diary.findEntryById(99));
    }

    @Test
    public void updateEntry_ChangesTitleAndBodyTest() {
        diary.unlockDiary("secret123");
        diary.createEntry("Old Title", "Old Body");
        diary.updateEntry(1, "New Title", "New Body");

        Entry updated = diary.findEntryById(1);
        assertEquals("New Title", updated.getTitle());
        assertEquals("New Body", updated.getBody());
    }

    @Test
    public void deleteEntry_RemovesEntryTest() {
        diary.unlockDiary("secret123");
        diary.createEntry("Title", "Body");
        diary.deleteEntry(1);
        assertNull(diary.findEntryById(1));
    }

    @Test
    public void deleteEntry_FailsWhenDiaryIsLockedTest() {
        assertThrows(IllegalStateException.class,
                () -> diary.deleteEntry(1));
    }

    @Test
    public void allTestCases_RunsFullDiaryWorkflowTest() {
        assertTrue(diary.isLocked());

        diary.unlockDiary("secret123");
        assertFalse(diary.isLocked());

        diary.createEntry("Day 1", "First entry");
        assertEquals(1, diary.getEntries().size());

        diary.updateEntry(1, "Day 1 (edited)", "First entry edited");
        assertEquals("Day 1 (edited)", diary.findEntryById(1).getTitle());

        diary.deleteEntry(1);
        assertNull(diary.findEntryById(1));

        diary.lockDiary();
        assertTrue(diary.isLocked());
    }
}
