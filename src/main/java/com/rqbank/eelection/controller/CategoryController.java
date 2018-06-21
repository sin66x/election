package com.rqbank.eelection.controller;

import com.rqbank.eelection.domain.Category;
import com.rqbank.eelection.model.CategoryDTO;
import com.rqbank.eelection.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String loadPage(Model model) {
        model.addAttribute("categoryDTO", new CategoryDTO());
        model.addAttribute("parents", categoryService.getAll());
        return "category";
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("categoryDTO") CategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
        return "redirect:category";
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/categoryEdit", method = RequestMethod.GET)
    public @ResponseBody
    CategoryDTO edit(@RequestParam("editId") String id) {
        Category editingCat = categoryService.findById(id);
        return new CategoryDTO(editingCat);
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/categoryRemove", method = RequestMethod.GET)
    public @ResponseBody String remove(@RequestParam("removeId") String id) {
        categoryService.remove(id);
        return "category";
    }


}
