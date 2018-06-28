package com.rqbank.eelection.controller;

import com.rqbank.eelection.config.msgloader.Messages;
import com.rqbank.eelection.domain.Candidate;
import com.rqbank.eelection.model.CandidateDTO;
import com.rqbank.eelection.model.LangPair;
import com.rqbank.eelection.service.CandidateService;
import com.rqbank.eelection.util.date.mDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CandidateController {
    @Autowired
    CandidateService candidateService;

    @Autowired
    LangPair langPair;

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/candidate", method = RequestMethod.GET)
    public String loadPage(Model model, @RequestParam("election") String electionId,@RequestParam(value = "error",required = false) String errorMessage) {
        CandidateDTO candidateDTO = new CandidateDTO();

        candidateDTO.setElection(Integer.parseInt(electionId));
        model.addAttribute("candidateDTO", candidateDTO);
        model.addAttribute("candidates", candidateService.findCandidateByElection(electionId));
        model.addAttribute("messages", Messages.getInst());
        model.addAttribute("lang", langPair.name);
        model.addAttribute("langDir", langPair.value);
        if (!"".equals(errorMessage)&& errorMessage!=null)
            model.addAttribute("errorMessage", Messages.getMessage(errorMessage,langPair.name));
        else
            model.addAttribute("errorMessage", "");

        return "candidate";
    }
    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/candidate", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("candidateDTO") CandidateDTO candidateDTO) {
        String errorMessage = checkValidator(candidateDTO);
        if ("".equals(errorMessage)) {
            candidateService.save(candidateDTO);
            return "redirect:candidate?election="+candidateDTO.getElection();
        }
        return "redirect:candidate?election="+candidateDTO.getElection()+"&error="+errorMessage;
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/candidateRemove", method = RequestMethod.GET)
    public @ResponseBody
    String remove(@RequestParam("removeId") String id) {
        candidateService.remove(id);
        return "candidate";
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/candidateEdit", method = RequestMethod.GET)
    public @ResponseBody
    CandidateDTO edit(@RequestParam("editId") String id) {
        Candidate editingCand = candidateService.findById(id);
        return new CandidateDTO(editingCand);
    }

    private String checkValidator(CandidateDTO candidateDTO) {
        if(
                "".equals(candidateDTO.getFirstName()) ||
                "".equals(candidateDTO.getLastName()) ||
                "".equals(candidateDTO.getFatherName()) ||
                "".equals(candidateDTO.getBirthCity()) ||
                "".equals(candidateDTO.getEducationDegree()) ||
                "".equals(candidateDTO.getEducationField()) ||
                "".equals(candidateDTO.getEducationUni()) ||
                "".equals(candidateDTO.getExternalHistory()) ||
                "".equals(candidateDTO.getInternalHistory()) ||
                "".equals(candidateDTO.getOfficialPosition()) ||
                "".equals(candidateDTO.getPersonalCode()) ||
                "".equals(candidateDTO.getEmployDate()) ||
                "".equals(candidateDTO.getBranchCode()) ||
                "".equals(candidateDTO.getProvinceCode())
                )
            return "FillAllFields";
        try{
            mDate.parse("yyyy/mm/dd",candidateDTO.getBirthdate());
        }
        catch (Exception e){
            candidateDTO.setBirthdate("");
            return "BirthDateInFormat";
        }
        try{
            mDate.parse("yyyy/mm/dd",candidateDTO.getEmployDate());
        }
        catch (Exception e){
            candidateDTO.setEmployDate("");
            return "EmployDateInFormat";
        }
        return "";
    }
}
