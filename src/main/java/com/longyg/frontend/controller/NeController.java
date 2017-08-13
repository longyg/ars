package com.longyg.frontend.controller;

import com.longyg.frontend.model.ars.us.UsRepository;
import com.longyg.frontend.model.ne.NERepository;
import com.longyg.frontend.model.ne.NetworkElement;
import com.longyg.frontend.model.param.NeParamRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NeController {
    private static final Logger LOG = Logger.getLogger(NeController.class);

    @Autowired
    private NERepository neRepository;

    @Autowired
    private NeParamRepository neParamRepository;

    @Autowired
    private UsRepository usRepository;

    @RequestMapping("/ne")
    public ModelAndView list() {
        List<NetworkElement> neList = neRepository.findAll();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("neList", neList);
        return new ModelAndView("ne", params);
    }

    @RequestMapping(value = "/ne/add", method = RequestMethod.POST)
    public String addNe(@ModelAttribute NetworkElement ne) {
        neRepository.save(ne);
        return "redirect:/ne";
    }

    @RequestMapping("/ne/delete")
    public String deleteNe(@ModelAttribute NetworkElement ne) {
        usRepository.deleteByNe(ne);
        neParamRepository.deleteByNe(ne);
        List<NetworkElement> neList = neRepository.findAll();

        String id = null;
        for (NetworkElement n : neList) {
            if (n.getNeType().equals(ne.getNeType()) && n.getNeVersion().equals(ne.getNeVersion())) {
                id = n.getId();
            }
        }
        if (id != null) {
            neRepository.deleteById(id);
        }
        return "redirect:/ne";
    }
}
