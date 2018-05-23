package com.github.punchat.dto.messaging.member

import com.github.punchat.dto.messaging.role.RoleDto
import groovy.transform.ToString

@ToString
class MemberDto {
    Long id
    String username
    RoleDto role
}
