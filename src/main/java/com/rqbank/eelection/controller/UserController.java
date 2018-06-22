package com.rqbank.eelection.controller;

import com.rqbank.eelection.config.msgloader.Messages;
import com.rqbank.eelection.domain.Candidate;
import com.rqbank.eelection.domain.User;
import com.rqbank.eelection.model.CandidateDTO;
import com.rqbank.eelection.service.CandidateService;
import com.rqbank.eelection.service.UserService;
import jdk.internal.util.xml.impl.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    Pair langPair;

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String loadPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("messages", Messages.getInst());
        model.addAttribute("lang", langPair.name);
        model.addAttribute("langDir", langPair.value);
        return "user";
    }
    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:user";
    }

    @RequestMapping(value = "/userRemove", method = RequestMethod.GET)
    public @ResponseBody String remove(@RequestParam("removeId") String id) {
        userService.remove(id);
        return "user";
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/userEdit", method = RequestMethod.GET)
    public @ResponseBody
    User edit(@RequestParam("editId") String id) {
        return userService.findById(id);
    }
}
