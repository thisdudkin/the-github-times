package org.raddan.newspaper.config;

import org.raddan.newspaper.config.fetcher.Fetcher;
import org.raddan.newspaper.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * @author Alexander Dudkin
 */
@Service
public class DataFetcherDeterminer {

    @Autowired
    private Map<String, Fetcher> fetcherMap;

    @SuppressWarnings("unchecked")
    public Set<Article> fetch(String param) {
        Fetcher fetcher = fetcherMap.get(param);
        if (fetcher == null)
            throw new UnsupportedOperationException("Not supported yet.");
        return (Set<Article>) fetcher.fetch(param);
    }
}
