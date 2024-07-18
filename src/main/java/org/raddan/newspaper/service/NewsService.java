package org.raddan.newspaper.service;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.repository.NewsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;


}
