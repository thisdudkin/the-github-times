package org.raddan.newspaper.entity.response.deletion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Deletion response for the entity")
public class DeletionResponse {
    private String id;
    private String type;
    private String deletedAt;
    private String username;
}
