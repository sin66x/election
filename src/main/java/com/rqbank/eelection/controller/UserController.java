package com.rqbank.eelection.controller;

import com.rqbank.eelection.config.msgloader.Messages;
import com.rqbank.eelection.domain.User;
import com.rqbank.eelection.model.LangPair;
import com.rqbank.eelection.service.UserService;
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
    LangPair langPair;

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String loadPage(Model model,@RequestParam(value = "error",required = false) String errorMessage) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("messages", Messages.getInst());
        model.addAttribute("lang", langPair.name);
        model.addAttribute("langDir", langPair.value);
        if (!"".equals(errorMessage)&& errorMessage!=null)
            model.addAttribute("errorMessage", Messages.getMessage(errorMessage,langPair.name));
        else
            model.addAttribute("errorMessage", "");
        return "user";
    }
    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("user") User user) {
        String errorMessage = checkValidator(user);
        if ("".equals(errorMessage)) {
            userService.save(user);
            return "redirect:user";
        }
        return "redirect:user?error="+errorMessage;
    }

    @PreAuthorize("hasRole('admin')")
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

    private String checkValidator(User user) {
        if ("".equals(user.getUsername()))
            return "InsertUsername";
        if ("".equals(user.getPassword()))
            return "InsertPassword";
        return "";

    }
}
