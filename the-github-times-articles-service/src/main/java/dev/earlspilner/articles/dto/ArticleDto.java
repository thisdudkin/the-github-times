package dev.earlspilner.articles.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

/**
 * @author Alexander Dudkin
 */
@JsonInclude(NON_NULL)
public record ArticleDto(
        @JsonProperty(access = READ_ONLY)
        Integer id,
        @JsonProperty(access = READ_ONLY)
        Integer authorId,
        @NotNull
        String title,
        @NotNull
        String summary,
        @NotNull
        String content,
        @NotNull
        Set<CategoryDto> categories,
        @NotNull
        Set<TagDto> tags,
        @JsonProperty(access = READ_ONLY)
        String createdUtc,
        @JsonProperty(access = READ_ONLY)
        String updatedUtc
) { }
