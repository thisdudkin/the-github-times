package org.raddan.newspaper.entity.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "News creation response")
public class NewsCreationResponse {
    private String newsId;
    private String title;
    private String summary;
    private String content;

}
