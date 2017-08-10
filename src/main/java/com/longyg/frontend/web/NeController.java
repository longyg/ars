package com.longyg.frontend.web;

import com.longyg.frontend.model.ne.NERepository;
import com.longyg.frontend.model.ne.NetworkElement;
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

    @Autowired
    private NERepository neRepository;

    @RequestMapping("/ne")
    public ModelAndView list() {
        List<NetworkElement> neList = neRepository.findAll();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("neList", neList);
        return new ModelAndView("ne", params);
    }

    @RequestMapping(value = "/ne/add", method = RequestMethod.POST)
    public String addNe(@ModelAttribute("ne") NetworkElement ne) {
        neRepository.save(ne);
        return "redirect:/ne";
    }
}
