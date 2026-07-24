package problemSolving;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProblemTrackerTest {
    Problem debt;
    Problem exam;
    Person ade;

    public ProblemTrackerTest() {
    }

    @BeforeEach
    public void setUp() {
        debt = new Problem("Debt", ProblemType.FINANCIAL);
        exam = new Problem("Exam", ProblemType.EDUCATION);
        ade = new Person("Ade");
    }

    @Test
    public void problemHasNameAndTypeTest() {
        assertEquals("Debt", debt.getName());
        assertEquals(ProblemType.FINANCIAL, debt.getType());
    }

    @Test
    public void newProblemIsNotSolvedTest() {
        assertFalse(debt.isSolved());
    }

    @Test
    public void problemCanBeSolvedTest() {
        debt.solve();
        assertTrue(debt.isSolved());
    }

    @Test
    public void personHasNameTest() {
        assertEquals("Ade", ade.getName());
    }

    @Test
    public void personStartsWithNoProblemsTest() {
        assertTrue(ade.getUnsolvedProblems().isEmpty());
    }

    @Test
    void personCanAddProblemTest() {
        ade.addProblem(debt);
        assertEquals(1, ade.getUnsolvedProblems().size());
    }

    @Test
    public void personCanSolveProblemTest() {
        ade.addProblem(debt);
        ade.addProblem(exam);
        ade.solveProblem("Debt");
        List<Problem> unsolved = ade.getUnsolvedProblems();
        assertEquals(1, unsolved.size());
        assertEquals("Exam", ((Problem)unsolved.get(0)).getName());
    }

    @Test
    public void personCanRecountUnsolvedProblemsTest() {
        ade.addProblem(debt);
        ade.addProblem(exam);
        ade.addProblem(new Problem("Career", ProblemType.BUSINESS));
        ade.solveProblem("Exam");
        assertEquals(2, ade.getUnsolvedProblems().size());
    }
}
