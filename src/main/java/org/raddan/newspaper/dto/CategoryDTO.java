package org.raddan.newspaper.dto;

import lombok.Data;
import org.raddan.newspaper.annotation.NotEmpty;

/**
 * @author Alexander Dudkin
 */
@Data
public class CategoryDTO {

    @NotEmpty
    private String name;

}
