package org.raddan.newspaper.utils;

import org.raddan.newspaper.entity.News;
import org.raddan.newspaper.entity.data.NewsData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author Alexander Dudkin
 */
@Component
public class ArticleFieldUpdater implements FieldUpdater<News> {

    private static final Logger logger = LoggerFactory.getLogger(ArticleFieldUpdater.class);

    @Override
    public void update(News entity, Map<String, Object> requestInfo) {
        requestInfo.forEach((fieldName, newValue) -> {
            try {
                Field field = null;
                boolean isFieldInNewsData = false;

                try {
                    field = entity.getClass().getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    field = entity.getData().getClass().getDeclaredField(fieldName);
                    isFieldInNewsData = true;
                }

                field.setAccessible(true);

                if (isFieldInNewsData) {
                    NewsData newsData = entity.getData();
                    field.set(newsData, newValue);
                    entity.setData(newsData);
                } else {
                    field.set(entity, newValue);
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                logger.error("Error updating field: {}", fieldName, e);
            }
        });
    }
}
