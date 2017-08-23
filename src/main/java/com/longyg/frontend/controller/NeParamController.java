package com.longyg.frontend.controller;

import com.longyg.backend.ars.tpl.Variable;
import com.longyg.backend.ars.tpl.VariablesRepository;
import com.longyg.frontend.model.ne.NeReleaseRepository;
import com.longyg.frontend.model.ne.NeRelease;
import com.longyg.frontend.model.ne.NeType;
import com.longyg.frontend.model.ne.NeTypeRepository;
import com.longyg.frontend.model.param.NeParam;
import com.longyg.frontend.model.param.NeParamRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class NeParamController {
    private static final Logger LOG = Logger.getLogger(NeParamController.class);
    @Autowired
    private NeReleaseRepository neRepository;

    @Autowired
    private NeParamRepository neParamRepository;

    @Autowired
    private NeReleaseRepository neReleaseRepository;

    @Autowired
    private NeTypeRepository neTypeRepository;

    @RequestMapping("/param")
    public ModelAndView list(HttpServletRequest request) {
        List<NeRelease> neReleaseList = new ArrayList<>();
        List<NeParam> neParamList = new ArrayList<>();

        String neTypeId = request.getParameter("neTypeId");
        if (null != neTypeId && !"".equals(neTypeId)) {
            Optional<NeType> neTypeOpt = neTypeRepository.findById(neTypeId);
            if (neTypeOpt.isPresent()) {
                NeType neType = neTypeOpt.get();
                neReleaseList = neReleaseRepository.findByNeType(neType.getName());
            }
        }

        String neRelId = request.getParameter("neRelId");
        if (null != neRelId && !"".equals(neRelId)) {
            Optional<NeRelease> neRelOpt = neReleaseRepository.findById(neRelId);
            if (neRelOpt.isPresent()) {
                NeRelease neRelease = neRelOpt.get();
                neParamList = neParamRepository.findByNeRelease(neRelease.getNeType(), neRelease.getNeVersion());
            }
        }
        List<NeType> allNeTypeList = neTypeRepository.findAll();

        Map<String, Object> params = new HashMap<>();
        params.put("neTypeId", neTypeId);
        params.put("neRelId", neRelId);
        params.put("neParamList", neParamList);
        params.put("neReleaseList", neReleaseList);
        params.put("allNeTypeList", allNeTypeList);
        return new ModelAndView("param/list", params);
    }

    @RequestMapping(value = "/param/add", method = RequestMethod.GET)
    public ModelAndView add(@RequestParam String neRelId) {
        NeRelease neRelease = null;
        if (null != neRelId && !"".equals(neRelId)) {
            Optional<NeRelease> neRelOpt = neReleaseRepository.findById(neRelId);
            if (neRelOpt.isPresent()) {
                neRelease = neRelOpt.get();
            }
        }
        List<Variable> paramList = VariablesRepository.getVariables();

        Map<String, Object> params = new HashMap<>();
        params.put("neRelease", neRelease);
        params.put("paramList", paramList);

        return new ModelAndView("param/add", params);
    }

    @RequestMapping(value = "/param/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request) {
        LOG.info("Entering save() method...");
        String neType = request.getParameter("neType");
        String neVersion = request.getParameter("neVersion");
        NeParam neParam = new NeParam();
        neParam.setNeType(neType);
        neParam.setNeVersion(neVersion);

        List<Variable> variables = new ArrayList<>();
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = (String) parameterNames.nextElement();

            String paramValue = request.getParameter(paramName);

            Variable variable = new Variable(paramName, paramValue);
            variables.add(variable);
        }
        neParam.setVariables(variables);

        neParamRepository.save(neParam);

        return "redirect:/param?neType=" + neType + "&neVersion=" + neVersion;
    }
}
