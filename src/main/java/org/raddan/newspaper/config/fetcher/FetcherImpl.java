package org.raddan.newspaper.config.fetcher;

import org.raddan.newspaper.entity.Article;
import org.raddan.newspaper.exception.custom.ArticleNotFoundException;
import org.raddan.newspaper.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Alexander Dudkin
 */
@Component("visitCount")
public class FetcherImpl implements Fetcher {

    @Autowired
    private ArticleRepository repository;

    @Override
    public Set<Article> fetch(String param) {
        return repository.findAllOrderedByVisitCount()
                .orElseThrow(() -> new ArticleNotFoundException("Articles not found"));
    }
}
