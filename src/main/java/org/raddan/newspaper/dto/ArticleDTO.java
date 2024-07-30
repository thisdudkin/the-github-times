package org.raddan.newspaper.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
@Data
public class ArticleDTO {
    private String title;
    private String summary;
    private String content;
    private String picture;
    private List<Long> categoryIds;
    private List<Long> tagIds;
}
