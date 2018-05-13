package com.github.punchat.messaging.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Invitation {
    @NotNull
    private Long userId;
    private String role;
}