package com.github.punchat.messaging.domain.member;

import com.github.punchat.dto.messaging.member.MemberDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MemberController {
    private final MemberFacadeService service;
    private final MemberMapper mapper;

    @ApiOperation(value = "update member info")
    @PutMapping("/members/{id}")
    public MemberDto update(@PathVariable("id") Long id) {
        throw new UnsupportedOperationException();
    }

    @ApiOperation(value = "exclude user from channel")
    @DeleteMapping("/members/{id}")
    public void exclude(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
