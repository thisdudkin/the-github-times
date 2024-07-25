package org.raddan.newspaper.entity.response.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.raddan.newspaper.entity.data.NewsData;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsInfoResponse {
    private String ID;
    private String author;
    private String createdAt;
    private String updatedAt;
    private NewsData data;
}
