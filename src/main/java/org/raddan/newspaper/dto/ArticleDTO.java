package org.raddan.newspaper.dto;

import lombok.Data;
import org.raddan.newspaper.annotation.NotEmpty;

import java.util.List;
import java.util.Set;

/**
 * @author Alexander Dudkin
 */
@Data
public class ArticleDTO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String summary;

    @NotEmpty
    private String content;

    @NotEmpty
    private String picture;

    @NotEmpty
    private Set<String> categoryNames;

    @NotEmpty
    private Set<String> tagNames;

}
