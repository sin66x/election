package com.rqbank.eelection.service.impl;

import com.rqbank.eelection.domain.Candidate;
import com.rqbank.eelection.domain.Election;
import com.rqbank.eelection.model.CandidateDTO;
import com.rqbank.eelection.repository.CandidateRepository;
import com.rqbank.eelection.service.CandidateService;
import com.rqbank.eelection.service.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    CandidateRepository repository;

    @Autowired
    ElectionService electionService;

    @Override
    public int save(CandidateDTO candidateDTO) {
        Candidate candidate = new Candidate();

        candidate.setEmployDate(candidateDTO.getMiladiEmployDate());
        candidate.setBirthdate(candidateDTO.getMiladiBirthdate());

        candidate.setElection(electionService.getElectionById(candidateDTO.getElection()));
        candidate.setFirstName(candidateDTO.getFirstName());
        candidate.setLastName(candidateDTO.getLastName());
        candidate.setBirthCity(candidateDTO.getBirthCity());
        candidate.setProvinceCode(candidateDTO.getProvinceCode());
        candidate.setBranchCode(candidateDTO.getBranchCode());
        candidate.setCreatedDate(new Date());
        candidate.setEducationDegree(candidateDTO.getEducationDegree());
        candidate.setEducationField(candidateDTO.getEducationField());
        candidate.setEducationUni(candidateDTO.getEducationUni());

        candidate.setExternalHistory(candidateDTO.getExternalHistory());
        candidate.setInternalHistory(candidateDTO.getInternalHistory());
        candidate.setOfficialPosition(candidateDTO.getOfficialPosition());
        candidate.setFatherName(candidateDTO.getFatherName());
        candidate.setPersonalCode(candidateDTO.getPersonalCode());
        candidate.setIsActive(candidateDTO.getIsActive());

        if (candidateDTO.getId() != 0) {
            candidate.setId(candidateDTO.getId());
            candidate.setCandidateCode(candidateDTO.getElection()+""+candidateDTO.getId());
        }

        repository.save(candidate);
        if (candidateDTO.getId() == 0) {
            candidate.setCandidateCode(candidateDTO.getElection()+""+candidate.getId());
            repository.save(candidate);
        }
        repository.flush();
        return candidate.getId();

    }

    @Override
    public List<Candidate> findCandidateByElection(String electionId) {
        Election election = electionService.findById(electionId);

        if (election != null)
            return election.getCandidates();
        return null;
    }

    @Override
    public void remove(String id) {
        Optional<Candidate> optionalCandidate = repository.findById(Integer.parseInt(id));
        if (optionalCandidate.isPresent())
        {
            Candidate candidate = optionalCandidate.get();
            candidate.setIsActive("false");
            repository.saveAndFlush(candidate);
        }
    }

    @Override
    public Candidate findById(String id) {
        try {
            Optional<Candidate> optionalCandidate = repository.findById(Integer.parseInt(id));
        if (optionalCandidate.isPresent())
            return optionalCandidate.get();
        else
            return null;
        }catch (Exception e){
            return null;
        }
    }


}
