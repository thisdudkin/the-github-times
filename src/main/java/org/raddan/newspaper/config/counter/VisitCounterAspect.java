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
import org.raddan.newspaper.exception.custom.ProfileNotFoundException;
import org.raddan.newspaper.model.Profile;
import org.raddan.newspaper.model.User;
import org.raddan.newspaper.repository.ProfileRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Alexander Dudkin
 */
@Aspect
@Component
@RequiredArgsConstructor
public class VisitCounterAspect {

    static final String MY_PROFILE_COUNTER = "MY_PROFILE_COUNTER:";
    static final String PROFILE_COUNTER = "PROFILE_COUNTER:";
    static final String ARTICLE_COUNTER = "ARTICLE_COUNTER:";

    private final Counter counter;
    private final UserService userService;
    private final ArticleValidator articleValidator;
    private final ProfileValidator profileValidator;
    private final ProfileRepository profileRepository;

    @Pointcut("execution(* org.raddan.newspaper.controller.ProfileController.getProfile(..))")
    public void myProfileVisit() {
    }

    @Pointcut("execution(* org.raddan.newspaper.controller.ProfileController.getProfileById(..))")
    public void profileByIdVisit() {
    }

    @Pointcut("execution(* org.raddan.newspaper.controller.ArticleController.get(..))")
    public void articleVisit() {
    }

    @After("myProfileVisit()")
    public void incrementMyProfileCounter() {
        User currentUser = userService.getCurrentUser();
        profileValidator.validate(currentUser);
        String key = MY_PROFILE_COUNTER + currentUser.getProfile().getId();
        counter.increment(key);
    }

    @After("profileByIdVisit()")
    public void incrementProfileByIdCounter(JoinPoint joinPoint) {
        Long profileId = extractProfileId(joinPoint.getArgs());
        if (!profileRepository.existsById(profileId)) {
            throw new ProfileNotFoundException("Profile not found for ID: " + profileId);
        }
        String key = PROFILE_COUNTER + profileId;
        counter.increment(key);
    }

    @After("articleVisit()")
    public void incrementArticleCounter(JoinPoint joinPoint) {
        Long articleId = extractArticleId(joinPoint.getArgs());
        articleValidator.validate(articleId);
        String key = ARTICLE_COUNTER + articleId;
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

    private Long extractProfileId(Object[] args) {
        if (args.length == 0) {
            throw new ProfileNotFoundException("No profile ID provided");
        }

        return Long.parseLong(args[0].toString());
    }
}
