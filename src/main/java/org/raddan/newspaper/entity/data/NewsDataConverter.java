package org.raddan.newspaper.entity.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class NewsDataConverter implements AttributeConverter<NewsData, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(NewsData newsData) {
        try {
            return objectMapper.writeValueAsString(newsData);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting NewsData to JSON", e);
        }
    }

    @Override
    public NewsData convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, NewsData.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting JSON to NewsData", e);
        }
    }
}
