package com.github.punchat.dto.ts.sergey;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicClassifierResponse {
    @JsonProperty("Topic")
    private List<String> Topic;
}
