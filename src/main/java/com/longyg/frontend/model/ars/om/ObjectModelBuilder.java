package com.longyg.frontend.model.ars.om;

import com.longyg.frontend.model.ne.NetworkElement;
import org.springframework.stereotype.Component;

@Component
public class ObjectModelBuilder {
    private NetworkElement ne;

    public void create(NetworkElement ne) {
        this.ne = ne;


    }
}
