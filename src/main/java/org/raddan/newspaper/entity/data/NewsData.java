package org.raddan.newspaper.entity.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class NewsData {
    private String title;
    private String content;
    private String summary;
    private String category;
    private List<String> tags;
    private List<String> imageURL;
    private Map<String, Object> metadata;
}
