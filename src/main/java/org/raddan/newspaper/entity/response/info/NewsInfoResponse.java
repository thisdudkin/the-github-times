package org.raddan.newspaper.entity.response.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsInfoResponse {
    private String UUID;
    private String title;
    private String summary;
    private String content;
    private List<String> tags;
    private List<String> imageURL;
    private String author;
    private String createdAt;
}
