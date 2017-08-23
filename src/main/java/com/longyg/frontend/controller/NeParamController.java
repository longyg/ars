package com.longyg.frontend.controller;

import com.longyg.backend.ars.tpl.Variable;
import com.longyg.backend.ars.tpl.VariablesRepository;
import com.longyg.frontend.model.ne.NeReleaseRepository;
import com.longyg.frontend.model.ne.NeRelease;
import com.longyg.frontend.model.ne.NeType;
import com.longyg.frontend.model.ne.NeTypeRepository;
import com.longyg.frontend.model.param.NeParam;
import com.longyg.frontend.model.param.NeParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.logging.Logger;

@Controller
public class NeParamController {
    private static final Logger LOG = Logger.getLogger(NeParamController.class.getName());
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
        String neTypeId = request.getParameter("neTypeId");
        List<NeRelease> neReleaseList = findNeReleaseByNeTypeId(neTypeId);

        String neRelId = request.getParameter("neRelId");
        List<NeParam> neParamList = findNeParamListByNeRelId(neRelId);

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
    public ModelAndView add(@RequestParam String neTypeId, @RequestParam String neRelId) {
        NeRelease neRelease = null;
        if (null != neRelId && !"".equals(neRelId)) {
            Optional<NeRelease> neRelOpt = neReleaseRepository.findById(neRelId);
            if (neRelOpt.isPresent()) {
                neRelease = neRelOpt.get();
            }
        }
        List<Variable> paramList = VariablesRepository.getVariables();

        Map<String, Object> params = new HashMap<>();
        params.put("neTypeId", neTypeId);
        params.put("neRelId", neRelId);
        params.put("neRelease", neRelease);
        params.put("paramList", paramList);

        return new ModelAndView("param/add", params);
    }

    @RequestMapping(value = "/param/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request) {
        String neTypeId = request.getParameter("neTypeId");
        String neRelId = request.getParameter("neRelId");
        String neType = request.getParameter("neType");
        String neVersion = request.getParameter("neVersion");

        List<NeParam> neParamList = neParamRepository.findByNeRelease(neType, neVersion);
        int v = neParamList.size() + 1;

        NeParam neParam = new NeParam();
        neParam.setNeType(neType);
        LOG.info("NE Type: " + neType);
        neParam.setNeVersion(neVersion);
        LOG.info("NE version:" + neVersion);
        neParam.setV(v);

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

        return "redirect:/param?neTypeId=" + neTypeId + "&neRelId=" + neRelId;
    }

    @RequestMapping("/param/view")
    public ModelAndView view(@RequestParam String id) {
        Optional<NeParam> neParam = neParamRepository.findById(id);
        Map<String, Object> params = new HashMap<>();
        params.put("neParam", neParam.get());
        return new ModelAndView("param/view", params);
    }

    private List<NeParam> findNeParamListByNeRelId(String neRelId) {
        List<NeParam> neParamList = new ArrayList<>();
        if (null != neRelId && !"".equals(neRelId)) {
            Optional<NeRelease> neRelOpt = neReleaseRepository.findById(neRelId);
            if (neRelOpt.isPresent()) {
                NeRelease neRelease = neRelOpt.get();
                neParamList = neParamRepository.findByNeRelease(neRelease.getNeType(), neRelease.getNeVersion());
            }
        }
        return neParamList;
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
