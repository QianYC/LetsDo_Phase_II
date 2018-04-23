package YingYingMonster.LetsDo_Phase_II.model;

import java.io.Serializable;

public class WorkerRequirement implements Serializable {
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
