package com.rqbank.eelection.controller;

import com.rqbank.eelection.config.msgloader.Messages;
import com.rqbank.eelection.config.provinceloader.Provinces;
import com.rqbank.eelection.domain.Candidate;
import com.rqbank.eelection.domain.User;
import com.rqbank.eelection.service.CandidateService;
import com.rqbank.eelection.service.UserService;
import com.rqbank.eelection.util.date.DateConverter;
import com.rqbank.eelection.util.date.mDate;
import jdk.internal.util.xml.impl.Pair;
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
import java.util.Date;
import java.util.List;

@Controller
public class UploadCandidateController {

    @Autowired
    CandidateService candidateService;

    @Autowired
    Pair langPair;

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/candidateUpload", method = RequestMethod.GET)
    public String loadPage(Model model, @RequestParam("election") String electionId,@RequestParam(value = "error",required = false) String errorMessage) {
        model.addAttribute("file", new MyModelAttribute());
        model.addAttribute("electionId", electionId);
        model.addAttribute("candidates",candidateService.findCandidateByElection(electionId));
        model.addAttribute("messages", Messages.getInst());
        model.addAttribute("lang", langPair.name);
        model.addAttribute("langDir", langPair.value);
        if (!"".equals(errorMessage)&& errorMessage!=null)
            model.addAttribute("errorMessage", Messages.getMessage(errorMessage,langPair.name));
        else
            model.addAttribute("errorMessage", "");
        return "upload-candidate";
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/candidateUpload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam("electionId") String electionId,
                                   RedirectAttributes redirectAttributes) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(),"UTF-8"));
            String line = reader.readLine();
            List<Candidate> candidates= new ArrayList<Candidate>();
            while (line != null) {
                if (line.split(",").length==15) {
                    candidates.add(parsLine(line));
                }
                line = reader.readLine();
            }
            reader.close();

            candidateService.saveCandidateForElection(candidates, electionId);
            return "redirect:candidateUpload?election="+electionId;
        }
        catch (Exception e){
            e.printStackTrace();
            return "redirect:candidateUpload?election="+electionId+"&error=BadFileFormat";
        }
    }

    private Candidate parsLine(String line) {
        Candidate candidate = new Candidate();
        candidate.setFirstName(line.split(",")[0]);
        candidate.setLastName(line.split(",")[1]);
        candidate.setFatherName(line.split(",")[2]);
        Date birthdate = null;
        try {
            birthdate = DateConverter.shamsiToMiladi(mDate.parse("yyyy/mm/dd", line.split(",")[3]));
        }catch (Exception e){
            e.printStackTrace();
        }
        candidate.setBirthdate(birthdate);

        candidate.setPersonalCode(line.split(",")[4]);
        candidate.setBirthCity(line.split(",")[5]);

        Date employdate = null;
        try {
            employdate = DateConverter.shamsiToMiladi(mDate.parse("yyyy/mm/dd", line.split(",")[6]));
        }catch (Exception e){
            e.printStackTrace();
        }
        candidate.setEmployDate(employdate);
        candidate.setProvinceName(line.split(",")[7]);
        candidate.setProvinceCode(Provinces.findProvinceCode(line.split(",")[7]));

        candidate.setBranchCode(line.split(",")[8]);
        candidate.setBranchName(Provinces.findBranchName(line.split(",")[8]));

        candidate.setOfficialPosition(line.split(",")[9]);
        candidate.setEducationDegree(line.split(",")[10]);
        candidate.setEducationField(line.split(",")[11]);
        candidate.setEducationUni(line.split(",")[12]);
        candidate.setInternalHistory(line.split(",")[13]);
        candidate.setExternalHistory(line.split(",")[14]);

        return candidate;
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
