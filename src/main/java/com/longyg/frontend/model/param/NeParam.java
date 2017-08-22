package com.longyg.frontend.model.param;

import com.longyg.backend.ars.tpl.Variable;
import com.longyg.frontend.model.ne.NeRelease;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document(collection = "params")
public class NeParam {
    @Id
    private String id;
    private int v;
    private NeRelease ne;
    private List<Variable> variables = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public NeRelease getNe() {
        return ne;
    }

    public void setNe(NeRelease ne) {
        this.ne = ne;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }
}
