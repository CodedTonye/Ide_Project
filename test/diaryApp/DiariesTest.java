package diaryApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiariesTest {

    private Diaries diaries;

    @BeforeEach
    public void setUp() {
        diaries = new Diaries();
    }

    @Test
    public void newDiaries_IsEmptyTest() {
        assertEquals(0, diaries.size());
    }

    @Test
    public void addDiary_CreatesNewDiaryTest() {
        diaries.add("bob", "password1");
        assertEquals(1, diaries.size());
    }

    @Test
    public void addDiary_DuplicateUsernameThrowsTest() {
        diaries.add("bob", "password1");
        assertThrows(IllegalArgumentException.class,
                () -> diaries.add("bob", "differentPassword"));
    }

    @Test
    public void findByUsername_ReturnsCorrectDiaryTest() {
        diaries.add("bob", "password1");
        Diary found = diaries.findByUsername("bob");
        assertNotNull(found);
        assertEquals("bob", found.getUsername());
    }

    @Test
    public void findByUsername_ReturnsNullWhenNotFoundTest() {
        assertNull(diaries.findByUsername("ghost"));
    }

    @Test
    public void deleteDiary_RemovesDiaryWithCorrectPasswordTest() {
        diaries.add("bob", "password1");
        diaries.delete("bob", "password1");
        assertNull(diaries.findByUsername("bob"));
    }

    @Test
    public void deleteDiary_DoesNothingWithWrongPasswordTest() {
        diaries.add("bob", "password1");
        diaries.delete("bob", "wrongPassword");
        assertNotNull(diaries.findByUsername("bob"));
    }

    @Test
    public void allTestCases_RunsFullDiariesWorkflowTest() {
        assertEquals(0, diaries.size());

        diaries.add("alice", "secret123");
        assertEquals(1, diaries.size());

        Diary alice = diaries.findByUsername("alice");
        assertNotNull(alice);

        diaries.delete("alice", "wrongPassword");
        assertEquals(1, diaries.size());

        diaries.delete("alice", "secret123");
        assertEquals(0, diaries.size());
        assertNull(diaries.findByUsername("alice"));
    }
}
