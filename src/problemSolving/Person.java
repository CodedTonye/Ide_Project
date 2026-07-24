package problemSolving;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private List<Problem> problems = new ArrayList();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addProblem(Problem problem) {
        problems.add(problem);
    }

    public void solveProblem(String name) {
        for(Problem problem : problems) {
            if (problem.getName().equals(name)) {
                problem.solve();
            }
        }

    }

    public List<Problem> getUnsolvedProblems() {
        List<Problem> unsolved = new ArrayList();

        for(Problem problem : this.problems) {
            if (!problem.isSolved()) {
                unsolved.add(problem);
            }
        }

        return unsolved;
    }
}
