package org.raddan.newspaper.entity.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "News creation response")
public class NewsCreationResponse {
    private UUID newsId;
    private String title;
    private String summary;
    private String content;

}
