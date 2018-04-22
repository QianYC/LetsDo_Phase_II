package YingYingMonster.LetsDo_Phase_II.model;

import java.io.Serializable;

public class TagRequirement implements Serializable {
    private MarkMode markMode;
    /*markMode是tags的时候，requirement为tag列表，tag之间以逗号隔开，其他模式都为具体要求*/
    private String requirement;
    private int gradesLimit;

    public TagRequirement(MarkMode markMode, String requirement, int gradesLimit) {
        this.markMode = markMode;
        this.requirement = requirement;
        this.gradesLimit = gradesLimit;
    }

    public MarkMode getMarkMode() {
        return markMode;
    }

    public void setMarkMode(MarkMode markMode) {
        this.markMode = markMode;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public int getGradesLimit() {
        return gradesLimit;
    }

    public void setGradesLimit(int gradesLimit) {
        this.gradesLimit = gradesLimit;
    }
}
