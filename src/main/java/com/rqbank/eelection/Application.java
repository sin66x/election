package com.rqbank.eelection;

import com.rqbank.eelection.domain.User;
import com.rqbank.eelection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;

/**
 * @auther Behnam Safari
 * date 6/13/18.
 * description
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer{

    @Autowired
    UserService userService;

    @PostConstruct
    public void makeAdminAcoount(){
        User admin = userService.findByUsername("admin");
        if (admin==null){
            userService.createDefaultAdmin();
        }
        else if (!"true".equals(admin.getIsActive())){
            userService.enableUser(admin);
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(Application.class);
    }
    public static void main(String[]args){
        SpringApplication.run(Application.class,args);
    }
}
