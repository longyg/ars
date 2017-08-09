package com.longyg.web;

import com.longyg.model.NetworkElement;
import com.longyg.service.NeService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NeController {

    @Resource
    private NeService neService;

    @RequestMapping("/ne")
    public String list(Model model) {
        List<NetworkElement> neList = neService.getNeList();
        model.addAttribute("neList", neList);
        return "ne";
    }
}
