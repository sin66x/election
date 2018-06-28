package com.rqbank.eelection.controller;

import com.rqbank.eelection.config.msgloader.Message;
import com.rqbank.eelection.config.msgloader.Messages;
import com.rqbank.eelection.domain.Category;
import com.rqbank.eelection.model.CategoryDTO;
import com.rqbank.eelection.model.LangPair;
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

    @Autowired
    LangPair langPair;

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String loadPage(Model model,@RequestParam(value = "error",required = false) String errorMessage) {
        model.addAttribute("categoryDTO", new CategoryDTO());
        model.addAttribute("parents", categoryService.getAll());
        model.addAttribute("messages", Messages.getInst());
        model.addAttribute("lang", langPair.name);
        model.addAttribute("langDir", langPair.value);
        if (!"".equals(errorMessage)&& errorMessage!=null)
            model.addAttribute("errorMessage", Messages.getMessage(errorMessage,langPair.name));
        else
            model.addAttribute("errorMessage", "");
        return "category";
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("categoryDTO") CategoryDTO categoryDTO) {
        String errorMessage = checkValidator(categoryDTO);
        if ("".equals(errorMessage)) {
            categoryService.save(categoryDTO);
            return "redirect:category";
        }
        else {
            return "redirect:category?error="+errorMessage;
        }
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

    private String checkValidator(CategoryDTO categoryDTO){
        if ("".equals(categoryDTO.getName()))
            return "InsertCatName";
        return "";
    }
}
