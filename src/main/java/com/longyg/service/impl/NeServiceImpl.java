package com.longyg.service.impl;

import com.longyg.model.NetworkElement;
import com.longyg.service.NeService;

import java.util.ArrayList;
import java.util.List;

public class NeServiceImpl implements NeService {
    @Override
    public List<NetworkElement> getNeList() {
        List<NetworkElement> neList = new ArrayList<NetworkElement>();
        neList.add(new NetworkElement("CSCF", "18.0C", "bare metal"));
        neList.add(new NetworkElement("CSCF", "18.0VI", "virtualization"));
        neList.add(new NetworkElement("Nokia HSS VNF", "18.0VNF", "Nokia HSS VNF"));
        return neList;
    }

    @Override
    public boolean addNe(NetworkElement ne) {
        return false;
    }

    @Override
    public boolean updateNe(NetworkElement ne) {
        return false;
    }

    @Override
    public boolean deleteNe(NetworkElement ne) {
        return false;
    }
}
