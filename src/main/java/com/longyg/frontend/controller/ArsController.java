package com.longyg.frontend.controller;

import com.longyg.frontend.model.ars.*;
import com.longyg.frontend.model.ars.us.UsBuilder;
import com.longyg.frontend.model.ars.us.UsRepository;
import com.longyg.frontend.model.ars.us.UserStorySpec;
import com.longyg.frontend.model.ne.NeRelease;
import com.longyg.frontend.model.ne.NeReleaseRepository;
import com.longyg.frontend.model.ne.NeType;
import com.longyg.frontend.model.ne.NeTypeRepository;
import com.longyg.frontend.model.param.NeParam;
import com.longyg.frontend.model.param.NeParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class ArsController {
    @Autowired
    private UsRepository usRepository;

    @Autowired
    private NeTypeRepository neTypeRepository;

    @Autowired
    private NeReleaseRepository neReleaseRepository;

    @Autowired
    private ArsRepository arsRepository;

    @Autowired
    private ArsConfigRepository arsConfigRepository;

    @RequestMapping("/ars")
    public ModelAndView list(HttpServletRequest request) {
        String neTypeId = request.getParameter("neTypeId");

        List<NeReleaseArs> neArsList = new ArrayList<>();

        List<NeRelease> neReleaseList = findNeReleaseByNeTypeId(neTypeId);

        for (NeRelease neRelease : neReleaseList) {
            NeReleaseArs neArs = new NeReleaseArs();
            neArs.setNeRelease(neRelease);

            ArsConfig arsConfig = arsConfigRepository.findByNeTypeAndRelease(neRelease.getNeType(), neRelease.getNeVersion());
            ARS ars = arsRepository.findByNeTypeAndRelease(neRelease.getNeType(), neRelease.getNeVersion());

            neArs.setArs(ars);
            neArs.setArsConfig(arsConfig);
            neArsList.add(neArs);
        }

        List<NeType> allNeTypeList = neTypeRepository.findAll();

        Map<String, Object> params = new HashMap<>();
        params.put("neTypeId", neTypeId);
        params.put("allNeTypeList", allNeTypeList);
        params.put("neArsList", neArsList);

        return new ModelAndView("ars/list", params);
    }

    @RequestMapping("/ars/view")
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

    private List<NeRelease> findNeReleaseByNeTypeId(String neTypeId) {
        List<NeRelease> neReleaseList = new ArrayList<>();
        if (null != neTypeId && !"".equals(neTypeId)) {
            Optional<NeType> neTypeOpt = neTypeRepository.findById(neTypeId);
            if (neTypeOpt.isPresent()) {
                NeType neType = neTypeOpt.get();
                neReleaseList = neReleaseRepository.findByNeType(neType.getName());
            }
        }
        return neReleaseList;
    }
}
