package com.longyg.frontend.controller;

import com.longyg.backend.adaptation.main.ResourceRepository;
import com.longyg.backend.ars.generator.ArsGenerator;
import com.longyg.frontend.model.ars.*;
import com.longyg.frontend.model.ars.us.UsRepository;
import com.longyg.frontend.model.ars.us.UserStorySpec;
import com.longyg.frontend.model.config.Adaptation;
import com.longyg.frontend.model.config.AdaptationResource;
import com.longyg.frontend.model.config.AdaptationResourceRepository;
import com.longyg.frontend.model.ne.NeRelease;
import com.longyg.frontend.model.ne.NeReleaseRepository;
import com.longyg.frontend.model.ne.NeType;
import com.longyg.frontend.model.ne.NeTypeRepository;
import com.longyg.frontend.model.response.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.util.*;
import java.util.logging.Logger;

@Controller
public class ArsController {
    private static final Logger LOG = Logger.getLogger(ArsController.class.getName());

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

    @Autowired
    private AdaptationResourceRepository resourceRepository;

    @Autowired
    private ArsGenerator arsGenerator;

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
    public String createUs(@RequestParam String neTypeId, @RequestParam String neRelId) {
        ArsConfig arsConfig = findArsConfig(neRelId);
        if (null != arsConfig)
        {
            arsGenerator.generateAndSave(arsConfig);
        }
        else
        {
            LOG.severe("ARS config is null, can't generate ARS!");
        }

        return "redirect:/ars?neTypeId=" + neTypeId;
    }

    @RequestMapping("/ars/addConfig")
    public ModelAndView addConfig(@RequestParam String neTypeId, @RequestParam String neRelId) {
        Map<String, Object> params = new HashMap<>();
        params.put("neTypeId", neTypeId);
        params.put("neRelId", neRelId);

        NeType neType = null;
        Optional<NeType> neTypeOpt = neTypeRepository.findById(neTypeId);
        if (neTypeOpt.isPresent()) {
            neType = neTypeOpt.get();
            params.put("neType", neType);
        }

        NeRelease neRelease = null;
        Optional<NeRelease> neRelOpt = neReleaseRepository.findById(neRelId);
        if (neRelOpt.isPresent()) {
            neRelease = neRelOpt.get();
            params.put("neRelease", neRelease);
        } else {
            LOG.severe("Can't find NE release with id: " + neRelId);
        }

        List<AdaptationResource> supportedResources = new ArrayList<>();
        if (null != neRelease) {
            ArsConfig arsConfig = arsConfigRepository.findByNeTypeAndRelease(neRelease.getNeType(), neRelease.getNeVersion());
            if (null != arsConfig) {
                for (String srcId : arsConfig.getResources()) {
                    Optional<AdaptationResource> opt = resourceRepository.findById(srcId);
                    if (opt.isPresent()) {
                        supportedResources.add(opt.get());
                    }
                }
            }
        }
        params.put("supportedResources", supportedResources);

        return new ModelAndView("ars/addConfig", params);
    }

    @RequestMapping(value = "/getAdapVersions", method = RequestMethod.POST)
    @ResponseBody
    public List<String> getAdaptationReleases(@RequestParam String adaptationId) {
        List<String> releases = new ArrayList<>();
        List<AdaptationResource> resources = resourceRepository.findAll();

        for (AdaptationResource src : resources) {
            if (src.getAdaptation().getId().equals(adaptationId)) {
                releases.add(src.getAdaptation().getRelease());
            }
        }

        return releases;
    }

    @RequestMapping(value = "/ars/addResource", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse addArsResource(@RequestParam String neTypeId, @RequestParam String neRelId,
                                       @RequestParam String adaptationId, @RequestParam String adaptationRelease) {
        LOG.info("neTypeId: " + neTypeId);
        LOG.info("neRelId: " + neRelId);
        LOG.info("adaptationId: " + adaptationId);
        LOG.info("adaptationRelease: " + adaptationRelease);
        NeRelease neRelease = null;
        Optional<NeRelease> neRelOpt = neReleaseRepository.findById(neRelId);
        if (neRelOpt.isPresent()) {
            neRelease = neRelOpt.get();
        } else {
            LOG.severe("Can't find NE release with id: " + neRelId);
        }

        ArsConfig arsConfig = null;
        if (null != neRelease) {
            arsConfig = arsConfigRepository.findByNeTypeAndRelease(neRelease.getNeType(), neRelease.getNeVersion());
        }

        if (arsConfig == null) {
            arsConfig = new ArsConfig();
            if (neRelease != null) {
                arsConfig.setNeType(neRelease.getNeType());
                arsConfig.setNeVersion(neRelease.getNeVersion());
            }
        }

        AdaptationResource addResource = null;
        List<AdaptationResource> resources = resourceRepository.findAll();
        for (AdaptationResource src : resources) {
            if (src.getAdaptation().getId().equals(adaptationId) && src.getAdaptation().getRelease().equals(adaptationRelease)) {
                boolean success = arsConfig.addResource(src.getId());
                if (success) {
                    addResource = src;
                }
                break;
            }
        }
        arsConfigRepository.save(arsConfig);

        AjaxResponse response = new AjaxResponse();
        response.setStatus("ok");
        response.setData(addResource.getAdaptation());
        return response;
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

    private ArsConfig findArsConfig(String neRelId) {
        if (null != neRelId && !"".equals(neRelId)) {
            Optional<NeRelease> neRelOpt = neReleaseRepository.findById(neRelId);
            if (neRelOpt.isPresent()) {
                NeRelease neRelease = neRelOpt.get();
                return arsConfigRepository.findByNeTypeAndRelease(neRelease.getNeType(), neRelease.getNeVersion());
            }
        }
        return null;
    }


}
