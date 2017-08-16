package com.longyg.frontend.controller;

import com.longyg.backend.adaptation.topology.PmbObject;
import com.longyg.frontend.model.iface.InterfaceObject;
import com.longyg.frontend.model.iface.InterfaceRepository;
import com.longyg.frontend.model.object.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ConfigController {
    @Autowired
    private InterfaceRepository interfaceRepository;

    @Autowired
    private ObjectRepository objectRepository;

    @RequestMapping("/interface")
    public ModelAndView listInterface() {
        List<InterfaceObject> interfaceList = interfaceRepository.findAll();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("interfaceList", interfaceList);
        return new ModelAndView("config/interface", params);
    }

    @RequestMapping(value = "/interface/add", method = RequestMethod.POST)
    public String addInterface(@ModelAttribute InterfaceObject io) {
        interfaceRepository.save(io);
        return "redirect:/interface";
    }

    @RequestMapping("/interface/delete")
    public String deleteInterface(@ModelAttribute InterfaceObject io) {
        List<InterfaceObject> interfaceList = interfaceRepository.findAll();
        InterfaceObject intfo = null;
        for (InterfaceObject ifo : interfaceList) {
            if (ifo.getName().equals(io.getName())) {
                intfo = ifo;
            }
        }
        if (null != intfo) {
            interfaceRepository.delete(intfo);
        }
        return "redirect:/interface";
    }

    @RequestMapping("/object")
    public ModelAndView listObject() {
        List<PmbObject> objectList = objectRepository.findAll();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("objectList", objectList);
        return new ModelAndView("config/object", params);
    }
}
