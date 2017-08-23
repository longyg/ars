package com.longyg.frontend.controller;

import com.longyg.frontend.model.ars.us.UsBuilder;
import com.longyg.frontend.model.ars.us.UsRepository;
import com.longyg.frontend.model.ars.us.UserStorySpec;
import com.longyg.frontend.model.ne.NeRelease;
import com.longyg.frontend.model.param.NeParam;
import com.longyg.frontend.model.param.NeParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ArsController {
    @Autowired
    private NeParamRepository neParamRepository;

    @Autowired
    private UsBuilder usBuilder;

    @Autowired
    private UsRepository usRepository;

    @RequestMapping("/ars")
    public ModelAndView ars(@ModelAttribute NeRelease ne) {
        UserStorySpec usSpec = usRepository.findByNe(ne);

        Map<String, Object> params = new HashMap<>();
        params.put("ne", ne);
        params.put("usSpec", usSpec);

        return new ModelAndView("ars/view", params);
    }

    @RequestMapping("/ars/create")
    public String createUs(@ModelAttribute NeRelease ne) {
//        NeParam neParam = neParamRepository.findByNe(ne);
//        UserStorySpec usSpec = usBuilder.create(neParam);
//        usRepository.save(usSpec);
        return "redirect:/ars?neType=" + ne.getNeType() + "&neVersion=" + ne.getNeVersion();
    }
}
