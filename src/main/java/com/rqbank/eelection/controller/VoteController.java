package com.rqbank.eelection.controller;

import com.rqbank.eelection.config.msgloader.Messages;
import com.rqbank.eelection.domain.Candidate;
import com.rqbank.eelection.exception.BadVoteException;
import com.rqbank.eelection.model.LangPair;
import com.rqbank.eelection.service.CandidateService;
import com.rqbank.eelection.service.ElectionService;
import com.rqbank.eelection.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class VoteController {
    @Autowired
    VoteService voteService;

    @Autowired
    ElectionService electionService;

    @Autowired
    CandidateService candidateService;

    @Autowired
    LangPair langPair;

    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public String loadPage(Model model, @RequestParam("election") String electionId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if(voteService.hadVoted(username,electionId))
            model.addAttribute("message", Messages.getMessage("YouHadVoted","fa"));
        if(!voteService.hadRight(username,electionId))
            model.addAttribute("message", Messages.getMessage("YouDonthavePermission","fa"));

        List<Candidate> candidates = candidateService.findCandidateByElection(electionId);
        model.addAttribute("candidates", candidates);
        model.addAttribute("electionId", electionId);
        model.addAttribute("maxSelection", candidates.get(0).getElection().getMaxSelection());
        model.addAttribute("messages", Messages.getInst());
        model.addAttribute("lang", langPair.name);
        model.addAttribute("langDir", langPair.value);

        return "vote";
    }

    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/voteSubmit", method = RequestMethod.GET)
    public String vote(@RequestParam String votes, @RequestParam String electionId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if(voteService.hadVoted(username,electionId)||!voteService.hadRight(username,electionId)||(votes.split("-").length-1)>=electionService.findById(electionId).getMaxSelection())
        {
//            return "redirect:vote?election="+electionId;
            throw new BadVoteException();
        }
        voteService.vote(username,electionId,votes.split("-"));
        return "welcome";
    }
}
