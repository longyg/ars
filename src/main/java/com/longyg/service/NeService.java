package com.longyg.service;

import com.longyg.model.NetworkElement;

import java.util.List;

public interface NeService {
    public List<NetworkElement> getNeList();

    public boolean addNe(NetworkElement ne);

    public boolean updateNe(NetworkElement ne);

    public boolean deleteNe(NetworkElement ne);
}
