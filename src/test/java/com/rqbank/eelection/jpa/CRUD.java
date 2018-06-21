package com.rqbank.eelection.jpa;

import com.rqbank.eelection.domain.*;
import com.rqbank.eelection.repository.*;
import com.rqbank.eelection.service.CategoryService;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration
public class CRUD extends AbstractJUnit4SpringContextTests {
    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ElectionRepository electionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserElectionRepository userElectionRepository;
    @Autowired
    VoteRepository voteRepository;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void before() {
        System.out.println("******************** " + testName.getMethodName() + " *****************");
    }


    @Test
    public void createCandidate() {
        Candidate candidate = new Candidate();
        candidate.setFirstName("Behnam");
        candidate.setLastName("Safari");
        candidateRepository.saveAndFlush(candidate);
        assert (candidate.getId() != 0);
        System.out.println(candidate);
    }

    @Test
    public void createCategory() {
        Category category = new Category();
        category.setName("TestCategory");
        categoryRepository.saveAndFlush(category);
        assert (category.getId() != 0);
        System.out.println(category);
    }

    @Test
    public void createElection() {
        Election election = new Election();
        electionRepository.saveAndFlush(election);
        assert (election.getId() != 0);
        System.out.println(election);

    }

    @Test
    public void createUser() {
        User user = new User();
        userRepository.saveAndFlush(user);
        assert (user.getId() != 0);
        System.out.println(user);

    }

    @Test
    public void createUserElection() {
        UserElection userElection = new UserElection();
        userElectionRepository.saveAndFlush(userElection);
        assert (userElection.getId() != 0);
        System.out.println(userElection);
    }

    @Test
    public void createVote() {
        Vote vote = new Vote();
        voteRepository.save(vote);
        assert (vote.getId() != 0);
        System.out.println(vote);
    }


    @Test
    public void findCandidate() {
        Optional<Candidate> candidate = candidateRepository.findById(1);
        System.out.println("\n\n\n\n" + candidate);
    }

    @Test
    public void findCategory() {
        Optional<Category> category= categoryRepository.findById(2);
        System.out.println("\n\n\n\n" + category);
    }

    @Test
    public void findElection() {
        Optional<Election> election= electionRepository.findById(2);
        System.out.println("\n\n\n\n" + election);
    }

    @Test
    public void findUser() {
        User user = userRepository.findByUsername("user");

        System.out.println("\n\n\n\n" + user);
    }

    @Test
    public void findUserElection() {
        Optional<UserElection> userElection = userElectionRepository.findById(2);
        System.out.println("\n\n\n\n" + userElection);
    }

    @Test
    public void findVote() {
        Optional<Vote> vote = voteRepository.findById(2);
        System.out.println("\n\n\n\n" + vote);
    }

    @Test
    public void findUserElectionVote() {

        System.out.println("\n\n\n\n" + voteRepository.findVotes("user",69));
    }



    @Test
    public void enableAdmin() {
        User admin = userRepository.findByUsername("admin");
        admin.setIsActive("true");
        userRepository.saveAndFlush(admin);

    }
}
