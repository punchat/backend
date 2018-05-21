package com.github.punchat.dto.messaging.member;

import lombok.Data;

@Data
public class AddNewMemberDto {
    private Long userId;
    private Long roleId;
}
