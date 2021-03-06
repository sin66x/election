package com.rqbank.eelection.controller;

import com.rqbank.eelection.config.msgloader.Messages;
import com.rqbank.eelection.domain.User;
import com.rqbank.eelection.model.LangPair;
import com.rqbank.eelection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadVoterController {

    @Autowired
    UserService userService;

    @Autowired
    LangPair langPair;

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/uploadVoter", method = RequestMethod.GET)
    public String loadPage(Model model, @RequestParam("election") String electionId,@RequestParam(value = "error",required = false) String errorMessage) {
        model.addAttribute("file", new MyModelAttribute());
        model.addAttribute("electionId", electionId);
        model.addAttribute("users",userService.findUsersForElection(electionId));
        model.addAttribute("messages", Messages.getInst());
        model.addAttribute("lang", langPair.name);
        model.addAttribute("langDir", langPair.value);
        if (!"".equals(errorMessage)&& errorMessage!=null)
            model.addAttribute("errorMessage", Messages.getMessage(errorMessage,langPair.name));
        else
            model.addAttribute("errorMessage", "");
        return "upload-voters";
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/electionUpload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam("electionId") String electionId,
                                   RedirectAttributes redirectAttributes) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(),"UTF-8"));
            String line = reader.readLine();
            List<User> users = new ArrayList<User>();
            while (line != null) {
                String username = line.split(",")[0];
                String password = line.split(",")[1];
                users.add(new User(username,password,"ROLE_user","true"));
                line = reader.readLine();
            }
            reader.close();

            userService.createUsersForElection(users, electionId);
            return "redirect:/";
        }
        catch (Exception e){
            e.printStackTrace();
            return "redirect:uploadVoter?election="+electionId+"&error=BadFileFormat";
        }
    }


    class MyModelAttribute {
        private MultipartFile file;

        public MultipartFile getFile() {
            return file;
        }

        public void setFile(MultipartFile file) {
            this.file = file;
        }
    }
}
