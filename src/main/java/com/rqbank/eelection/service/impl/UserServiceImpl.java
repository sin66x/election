package com.rqbank.eelection.service.impl;

import com.rqbank.eelection.domain.Election;
import com.rqbank.eelection.domain.User;
import com.rqbank.eelection.domain.UserElection;
import com.rqbank.eelection.repository.UserElectionRepository;
import com.rqbank.eelection.repository.UserRepository;
import com.rqbank.eelection.service.ElectionService;
import com.rqbank.eelection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    ElectionService electionService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserElectionRepository userElectionRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<Election> getActiveElections(String username) {
        return electionService.findActiveElections(username);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void createDefaultAdmin() {
        User admin = new User("admin",passwordEncoder.encode("p"),"ROLE_admin","true");
        userRepository.saveAndFlush(admin);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public int save(User user) {
        if (user.getId()==0) {
            user.setCreatedDate(new Date());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.saveAndFlush(user);
            return user.getId();
        }
        else{
            if (user.getPassword()!=null&&!"".equals(user.getPassword())){
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.saveAndFlush(user);
                return user.getId();
            }
            else {
                userRepository.updateWithoutPassword(user.getId(),user.getUsername(),user.getIsActive(),user.getRole());
                return user.getId();
            }
        }
    }

    @Override
    public void remove(String id) {
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(id));
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setIsActive("false");
            userRepository.saveAndFlush(user);
        }
    }

    @Override
    public void enableUser(User user) {
        user.setIsActive("true");
        userRepository.saveAndFlush(user);

    }

    @Override
    public User findById(String id) {
        Optional<User> userOptional = userRepository.findById(Integer.parseInt(id));
        if (userOptional.isPresent())
            return userOptional.get();
        return null;
    }

    @Override
    public void createUsersForElection(List<User> users, String electionId) {
        Election election = electionService.findById(electionId);
        userElectionRepository.deleteByElection(election);

        for (User user: users){
            User u = userRepository.findByUsername(user.getUsername());
            if (u==null){
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.saveAndFlush(user);
                u = user;
            }
            else{
                u.setPassword(passwordEncoder.encode(user.getPassword()));
                u.setIsActive(user.getIsActive());
                u.setRole(user.getRole());
                userRepository.saveAndFlush(u);
            }

            UserElection userElection = new UserElection();
            userElection.setCreatedDate(new Date());
            userElection.setElection(election);
            userElection.setUser(u);
            userElectionRepository.saveAndFlush(userElection);




        }
    }

    @Override
    public List<User> findUsersForElection(String electionId) {
        Election election = electionService.getElectionById(Integer.parseInt(electionId));
        List<UserElection> userElections =  userElectionRepository.findByElection(election);
        List<User> users = new ArrayList<User>();
        for(UserElection ue : userElections){
            users.add(ue.getUser());
        }
        return users;
    }
}

