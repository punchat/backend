package com.github.punchat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NewAccessCodeRequest {
    private String email;
}
