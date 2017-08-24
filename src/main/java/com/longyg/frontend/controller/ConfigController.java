package com.longyg.frontend.controller;

import com.longyg.frontend.model.config.AdaptationResource;
import com.longyg.frontend.model.config.AdaptationResourceRepository;
import com.longyg.frontend.model.iface.InterfaceObject;
import com.longyg.frontend.model.iface.InterfaceRepository;
import com.longyg.frontend.model.object.GlobalObject;
import com.longyg.frontend.model.object.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private AdaptationResourceRepository adaptationResourceRepository;

    @RequestMapping("/interface")
    public ModelAndView listInterface() {
        List<InterfaceObject> interfaceList = interfaceRepository.findAll();
        Map<String, Object> params = new HashMap<>();
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
        List<GlobalObject> objectList = objectRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("objectList", objectList);
        return new ModelAndView("config/object", params);
    }

    @RequestMapping(value = "/object/add", method = RequestMethod.POST)
    public String addObject(@ModelAttribute GlobalObject ao) {
        objectRepository.save(ao);
        return "redirect:/object";
    }

    @RequestMapping("/object/delete")
    public String deleteObject(@ModelAttribute GlobalObject ao) {
        List<GlobalObject> objectList = objectRepository.findAll();
        GlobalObject addo = null;
        for (GlobalObject ado : objectList) {
            if (ado.getName().equals(ao.getName())) {
                addo = ado;
            }
        }
        if (null != addo) {
            objectRepository.delete(addo);
        }
        return "redirect:/object";
    }

    @RequestMapping("/resource")
    public ModelAndView listResource() {
        List<AdaptationResource> adaptationResources = adaptationResourceRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("adaptationResources", adaptationResources);
        return new ModelAndView("config/resource", params);
    }

    @RequestMapping(value = "/resource/add", method = RequestMethod.POST)
    public String addResource(@ModelAttribute AdaptationResource resource) {
        adaptationResourceRepository.save(resource);
        return "redirect:/resource";
    }

    @RequestMapping(value = "/resource/delete")
    public String deleteResource(@RequestParam String id) {
        adaptationResourceRepository.deleteById(id);
        return "redirect:/resource";
    }
}