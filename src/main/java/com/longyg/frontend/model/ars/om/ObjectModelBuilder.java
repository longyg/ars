package com.longyg.frontend.model.ars.om;

import com.longyg.frontend.model.ne.NeRelease;
import org.springframework.stereotype.Component;

@Component
public class ObjectModelBuilder {
    private NeRelease ne;

    public void create(NeRelease ne) {
        this.ne = ne;


    }
}
