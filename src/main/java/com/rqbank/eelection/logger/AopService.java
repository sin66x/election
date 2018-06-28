package com.rqbank.eelection.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AbstractConfiguration;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AopService {

    @Before("execution(* com.rqbank.eelection..service..*(..)) ")
    public void logService(JoinPoint joinPoint){
        final Logger logger = LogManager.getLogger("ServiceLogger");

        String args = argsToString(joinPoint.getArgs());
        String username;
        if (SecurityContextHolder.getContext().getAuthentication()==null)
            username = "System...";
        else
            username = SecurityContextHolder.getContext().getAuthentication().getName();

        String callingMethod = joinPoint.getSignature().toString()+"::("+args+")"+" by ::"+username;
        logger.info(callingMethod);
    }

    @Before("execution(* com.rqbank.eelection..controller..*(..)) ")
    public void logController(JoinPoint joinPoint){
        final Logger logger = LogManager.getLogger("ControllerLogger");
        String args = argsToString(joinPoint.getArgs());
        String username;
        if (SecurityContextHolder.getContext().getAuthentication()==null)
            username = "System...";
        else
            username = SecurityContextHolder.getContext().getAuthentication().getName();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String callingMethod = joinPoint.getSignature().toString()+"::("+args+")"+" by ::"+username+"::IP="+request.getRemoteAddr();
        logger.info(callingMethod);
    }

    private String argsToString (Object []arguments){
        String args = "";
        for (Object arg: arguments){
            if (arg!=null)
                args += arg.toString()+",";
            else
                args += "NULL,";
        }
        if (!"".equals(args))
            args = args.substring(0,args.length()-1);
        return args;
    }
}