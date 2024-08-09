package org.raddan.newspaper.dto;

import lombok.Data;
import org.raddan.newspaper.annotation.NotEmpty;

/**
 * @author Alexander Dudkin
 */
@Data
public class CategoryDto {

    @NotEmpty
    private String name;

}
