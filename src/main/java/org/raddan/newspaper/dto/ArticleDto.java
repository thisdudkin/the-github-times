package org.raddan.newspaper.dto;

import lombok.Data;
import org.raddan.newspaper.annotation.NotEmpty;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
@Data
public class ArticleDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String summary;

    @NotEmpty
    private String content;

    @NotEmpty
    private String picture;

    @NotEmpty
    private List<String> categoryNames;

    @NotEmpty
    private List<String> tagNames;

}
