package dev.earlspilner.articles.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

/**
 * @author Alexander Dudkin
 */
public record TagDto(
        @NotNull
        @JsonProperty(access = READ_ONLY)
        Integer id,
        @NotNull
        String name,
        @NotNull
        @JsonProperty(access = READ_ONLY)
        String createdUtc,
        @JsonProperty(access = READ_ONLY)
        String updatedUtc
) { }
