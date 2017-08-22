package com.longyg.frontend.controller;

import com.longyg.backend.ars.tpl.Variable;
import com.longyg.backend.ars.tpl.VariablesRepository;
import com.longyg.frontend.model.ne.NeReleaseRepository;
import com.longyg.frontend.model.ne.NeRelease;
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


    @RequestMapping("/param")
    public ModelAndView list(@ModelAttribute NeRelease ne) {
        LOG.info("Entering list() method...");
        NeParam neParam = neParamRepository.findByNe(ne);
        Map<String, Object> params = new HashMap<>();
        params.put("ne", ne);
        params.put("neParam", neParam);
        return new ModelAndView("param/list", params);
    }

    @RequestMapping(value = "/param/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute NeRelease ne) {
        List<NeRelease> neList = neRepository.findAll();
        List<Variable> paramList = VariablesRepository.getVariables();
        Map<String, Object> params = new HashMap<>();
        params.put("currentNe", ne);
        params.put("allNes", neList);
        params.put("paramList", paramList);

        return new ModelAndView("param/add", params);
    }

    @RequestMapping(value = "/param/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request) {
        LOG.info("Entering save() method...");
        String neType = request.getParameter("neType");
        String neVersion = request.getParameter("neVersion");
        NeRelease ne = new NeRelease(neType, neVersion);
        NeParam neParam = new NeParam();
        neParam.setNe(ne);

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
