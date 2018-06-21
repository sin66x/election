package com.rqbank.eelection.controller;

import com.rqbank.eelection.domain.Category;
import com.rqbank.eelection.domain.Election;
import com.rqbank.eelection.model.CategoryDTO;
import com.rqbank.eelection.model.ElectionDTO;
import com.rqbank.eelection.service.CategoryService;
import com.rqbank.eelection.service.ElectionService;
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

    @RequestMapping(value = "/election", method = RequestMethod.GET)
    public String loadPage(Model model) {
        model.addAttribute("electionDTO", new ElectionDTO());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("elections", electionService.getAllActives());
        return "election";
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/election", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("electionDTO") ElectionDTO electionDTO) {
        electionService.save(electionDTO);
        return "redirect:election";
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