package com.github.punchat.dto.messaging.member;

import lombok.Data;

import java.util.List;

@Data
public class AddNewMembersDto {
    private List<AddNewMemberDto> members;
}
