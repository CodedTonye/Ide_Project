package problemSolving;

public class Problem {
    private String name;
    private ProblemType type;
    private boolean solved = false;

    public Problem(String name, ProblemType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ProblemType getType() {
        return type;
    }

    public boolean isSolved() {
        return solved;
    }

    public void solve() {
        solved = true;
    }
}
