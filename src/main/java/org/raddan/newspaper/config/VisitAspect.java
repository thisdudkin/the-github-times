package org.raddan.newspaper.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.raddan.newspaper.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alexander Dudkin
 */
@Aspect
@Component
public class VisitAspect {

    private static final Logger log = LoggerFactory.getLogger(VisitAspect.class);

    @Autowired
    private ArticleService articleService;

    @Pointcut("execution(* org.raddan.newspaper.controller.ArticleController.get(..)) && args(id)")
    public void getArticle(Long id) {}

    @Before("getArticle(id)")
    public void incrementVisitCount(JoinPoint joinPoint, Long id) {
        log.info("Aspect triggered for method: {} with arguments: {}",
                joinPoint.getSignature()
                , joinPoint.getArgs());

        articleService.incrementVisitCount(id);
    }
}
