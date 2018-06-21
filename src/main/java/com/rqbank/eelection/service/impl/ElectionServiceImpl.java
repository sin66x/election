package com.rqbank.eelection.service.impl;

import com.rqbank.eelection.domain.Election;
import com.rqbank.eelection.model.ElectionDTO;
import com.rqbank.eelection.repository.ElectionRepository;
import com.rqbank.eelection.service.CategoryService;
import com.rqbank.eelection.service.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ElectionServiceImpl implements ElectionService {
    @Autowired
    ElectionRepository repository;

    @Autowired
    CategoryService categoryService;

    @Override
    public void removeCategory(Election election) {
        election.setCategory(null);
        repository.saveAndFlush(election);
    }

    @Override
    public int save(ElectionDTO electionDTO) {
        Election election = new Election();
        if (electionDTO.getCategory()!=0)
            election.setCategory(categoryService.findById(electionDTO.getCategory()+""));

        if (electionDTO.getId()!=0)
            election.setId(electionDTO.getId());

        if (electionDTO.getIsActive()==null || "".equals(electionDTO.getIsActive()))
            election.setIsActive("true");
        else
            election.setIsActive(electionDTO.getIsActive());
        election.setCreatedDate(new Date());
        election.setEndDate(electionDTO.getMiladiEndDate());
        election.setMaxSelection(electionDTO.getMaxSelection());
        election.setName(electionDTO.getName());
        election.setStartDate(electionDTO.getMiladiStartDate());

        repository.saveAndFlush(election);

        return election.getId();
    }

    @Override
    public Election findById(String id) {
        Optional<Election> election = repository.findById(Integer.parseInt(id));
        if (election.isPresent())
            return election.get();
        return null;
    }

    @Override
    public void remove(String id) {
        Optional<Election> election = repository.findById(Integer.parseInt(id));
        if (election.isPresent()) {
            election.get().setIsActive("false");
            repository.saveAndFlush(election.get());
        }
    }

    @Override
    public List<Election> getAllActives() {
        return repository.findByIsActive("true");
    }

    @Override
    public Election getElectionById(int election) {
        Optional<Election> electionOptional = repository.findById(election);
        if (electionOptional.isPresent())
            return electionOptional.get();
        return null;
    }

    @Override
    public List<Election> findActiveElections(String username) {
        return repository.findActiveElections(username);
    }
}