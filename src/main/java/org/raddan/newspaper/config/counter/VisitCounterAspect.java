package org.raddan.newspaper.config.counter;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.raddan.newspaper.auth.service.UserService;
import org.raddan.newspaper.config.validator.ArticleValidator;
import org.raddan.newspaper.config.validator.ProfileValidator;
import org.raddan.newspaper.exception.custom.ArticleNotFoundException;
import org.raddan.newspaper.model.User;
import org.springframework.stereotype.Component;

/**
 * @author Alexander Dudkin
 */
@Aspect
@Component
@RequiredArgsConstructor
public class VisitCounterAspect {

    private final Counter counter;
    private final UserService userService;
    private final ArticleValidator articleValidator;
    private final ProfileValidator profileValidator;

    @Pointcut("execution(* org.raddan.newspaper.controller.ProfileController.getProfile(..))")
    public void profileVisit() {
    }

    @Pointcut("execution(* org.raddan.newspaper.controller.ArticleController.get(..))")
    public void articleVisit() {
    }

    @After("profileVisit()")
    public void incrementProfileCounter() {
        User currentUser = userService.getCurrentUser();
        profileValidator.validate(currentUser);
        String key = "profile:" + currentUser.getProfile().getId();
        counter.increment(key);
    }

    @After("articleVisit()")
    public void incrementArticleCounter(JoinPoint joinPoint) {
        Long articleId = extractArticleId(joinPoint.getArgs());
        articleValidator.validate(articleId);
        String key = "article:" + articleId;
        counter.increment(key);
    }

    private Long extractArticleId(Object[] args) {
        if (args.length == 0) {
            throw new ArticleNotFoundException("No article ID provided");
        }

        try {
            return Long.parseLong(args[0].toString());
        } catch (NumberFormatException e) {
            throw new ArticleNotFoundException("Invalid article ID provided: " + args[0]);
        }
    }
}
