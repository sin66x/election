package com.rqbank.eelection.controller;

import com.rqbank.eelection.config.msgloader.Messages;
import com.rqbank.eelection.domain.Category;
import com.rqbank.eelection.domain.Election;
import com.rqbank.eelection.model.CategoryDTO;
import com.rqbank.eelection.model.ElectionDTO;
import com.rqbank.eelection.service.CategoryService;
import com.rqbank.eelection.service.ElectionService;
import jdk.internal.util.xml.impl.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ElectionController {

    @Autowired
    ElectionService electionService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    Pair langPair;

    @RequestMapping(value = "/election", method = RequestMethod.GET)
    public String loadPage(Model model,@RequestParam(value = "error",required = false) String errorMessage) {
        ElectionDTO electionDTO = new ElectionDTO();
        electionDTO.setMaxSelection(1);
        model.addAttribute("electionDTO", electionDTO);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("elections", electionService.getAllActives());
        model.addAttribute("messages", Messages.getInst());
        model.addAttribute("lang", langPair.name);
        model.addAttribute("langDir", langPair.value);
        if (!"".equals(errorMessage)&& errorMessage!=null)
            model.addAttribute("errorMessage", Messages.getMessage(errorMessage,langPair.name));
        else
            model.addAttribute("errorMessage", "");
        return "election";
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/election", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("electionDTO") ElectionDTO electionDTO) {
        String errorMessage = checkValidator(electionDTO);
        if ("".equals(errorMessage)) {
            electionService.save(electionDTO);
            return "redirect:election";
        }
        else {
            return "redirect:election?error="+errorMessage;
        }

    }

    private String checkValidator(ElectionDTO electionDTO) {
        if ("".equals(electionDTO.getName()))
            return "InsertName";
        if (electionDTO.getMaxSelection()<=0)
            return "AtLeastOneCandidate";
        return "";
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/electionEdit", method = RequestMethod.GET)
    public @ResponseBody
    ElectionDTO edit(@RequestParam("editId") String id) {
        Election editingElec = electionService.findById(id);
        return new ElectionDTO(editingElec);
    }

    @RequestMapping(value = "/electionRemove", method = RequestMethod.GET)
    public @ResponseBody String remove(@RequestParam("removeId") String id) {
        electionService.remove(id);
        return "election";
    }
}