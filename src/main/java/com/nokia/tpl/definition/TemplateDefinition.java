package com.nokia.tpl.definition;

import java.util.ArrayList;
import java.util.List;

public class TemplateDefinition {
    private int sheet;
    private String name;
    private Basic basic;
    private List<UserStory> usList = new ArrayList<UserStory>();

    public int getSheet() {
        return sheet;
    }

    public void setSheet(int sheet) {
        this.sheet = sheet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public List<UserStory> getUsList() {
        return usList;
    }

    public void setUsList(List<UserStory> usList) {
        this.usList = usList;
    }

    public void addUs(UserStory us) {
        usList.add(us);
    }
}

