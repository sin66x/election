package com.rqbank.eelection.controller;

import com.rqbank.eelection.config.msgloader.Messages;
import com.rqbank.eelection.model.CategoryDTO;
import com.rqbank.eelection.service.UserService;
import jdk.internal.util.xml.impl.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class WelcomeController {
    @Autowired
    UserService userService;

    @Autowired
    Pair langPair;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("elections", userService.getActiveElections(username));
        model.addAttribute("messages", Messages.getInst());
        model.addAttribute("lang", langPair.name);
        model.addAttribute("langDir", langPair.value);
        return "welcome";
    }


}
