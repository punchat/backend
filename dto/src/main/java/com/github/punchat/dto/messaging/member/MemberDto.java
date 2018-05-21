package com.github.punchat.dto.messaging.member;

import com.github.punchat.dto.messaging.role.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto {
    private Long id;
    private String username;
    private RoleDto role;
}
