package com.nokia.tpl;

public class SubTaskTemplate {
    private boolean selected;
    private String name;
    private VariableTemplate description;
    private VariableTemplate rationale;
    private VariableTemplate issue;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VariableTemplate getDescription() {
        return description;
    }

    public void setDescription(VariableTemplate description) {
        this.description = description;
    }

    public VariableTemplate getRationale() {
        return rationale;
    }

    public void setRationale(VariableTemplate rationale) {
        this.rationale = rationale;
    }

    public VariableTemplate getIssue() {
        return issue;
    }

    public void setIssue(VariableTemplate issue) {
        this.issue = issue;
    }
}
