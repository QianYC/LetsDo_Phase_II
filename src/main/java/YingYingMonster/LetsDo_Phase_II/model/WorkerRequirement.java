package YingYingMonster.LetsDo_Phase_II.model;

public class WorkerRequirement {
    private int LevelLimit;

    public WorkerRequirement(int levelLimit) {
        LevelLimit = levelLimit;
    }

    public int getLevelLimit() {
        return LevelLimit;
    }

    public void setLevelLimit(int levelLimit) {
        LevelLimit = levelLimit;
    }
}
