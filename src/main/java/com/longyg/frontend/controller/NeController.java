package com.longyg.frontend.controller;

import com.longyg.frontend.model.ars.us.UsRepository;
import com.longyg.frontend.model.ne.NeReleaseRepository;
import com.longyg.frontend.model.ne.NeType;
import com.longyg.frontend.model.ne.NeTypeRepository;
import com.longyg.frontend.model.ne.NeRelease;
import com.longyg.frontend.model.param.NeParamRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NeController {
    private static final Logger LOG = Logger.getLogger(NeController.class);

    @Autowired
    private NeReleaseRepository neReleaseRepository;

    @Autowired
    private NeParamRepository neParamRepository;

    @Autowired
    private UsRepository usRepository;

    @Autowired
    private NeTypeRepository neTypeRepository;

    //////////////////////////////////////////////////////////////
    // NE Type
    @RequestMapping("/netype")
    public ModelAndView listNeType() {
        List<NeType> neTypeList = neTypeRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("neTypeList", neTypeList);
        return new ModelAndView("ne/netype", params);
    }

    @RequestMapping(value = "/netype/add", method = RequestMethod.POST)
    public String addNeType(@RequestParam("adaptations") String[] adaptations, @ModelAttribute NeType neType) {
        neType.setAdaptList(Arrays.asList(adaptations));
        neTypeRepository.save(neType);
        return "redirect:/netype";
    }

    @RequestMapping(value = "/netype/delete")
    public String deleteNeType(@RequestParam String id) {
        neTypeRepository.deleteById(id);
        return "redirect:/netype";
    }

    //////////////////////////////////////////////////////////////
    // NE Release
    @RequestMapping("/nerelease")
    public ModelAndView list() {
        List<NeRelease> neReleaseList = neReleaseRepository.findAll();
        List<NeType> neTypeList = neTypeRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("neReleaseList", neReleaseList);
        params.put("neTypeList", neTypeList);
        return new ModelAndView("ne/nerelease", params);
    }

    @RequestMapping(value = "/nerelease/add", method = RequestMethod.POST)
    public String addNe(@ModelAttribute NeRelease ne) {
        neReleaseRepository.save(ne);
        return "redirect:/nerelease";
    }

    @RequestMapping("/nerelease/delete")
    public String deleteNe(@RequestParam String id) {
        neReleaseRepository.deleteById(id);
        return "redirect:/nerelease";
    }
}
