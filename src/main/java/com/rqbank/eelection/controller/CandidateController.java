package com.rqbank.eelection.controller;

import com.rqbank.eelection.config.msgloader.Messages;
import com.rqbank.eelection.domain.Candidate;
import com.rqbank.eelection.domain.Election;
import com.rqbank.eelection.model.CandidateDTO;
import com.rqbank.eelection.model.ElectionDTO;
import com.rqbank.eelection.service.CandidateService;
import jdk.internal.util.xml.impl.Pair;
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
    Pair langPair;

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/candidate", method = RequestMethod.GET)
    public String loadPage(Model model, @RequestParam("election") String electionId) {

        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setElection(Integer.parseInt(electionId));
        model.addAttribute("candidateDTO", candidateDTO);
        model.addAttribute("candidates", candidateService.findCandidateByElection(electionId));
        model.addAttribute("messages", Messages.getInst());
        model.addAttribute("lang", langPair.name);
        model.addAttribute("langDir", langPair.value);
        return "candidate";
    }
    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/candidate", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("candidateDTO") CandidateDTO candidateDTO) {
        candidateService.save(candidateDTO);
        return "redirect:candidate?election="+candidateDTO.getElection();
    }

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
}
