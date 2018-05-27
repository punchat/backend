package com.github.punchat.messaging.domain.member;

import com.github.punchat.dto.messaging.member.MemberDto;
import com.github.punchat.dto.messaging.member.MemberRequest;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class MemberController {
    private final MemberFacadeService service;

    @ApiOperation(value = "update member info")
    @PutMapping("/members/{id}")
    public MemberDto update(@PathVariable("id") Long id, @RequestBody MemberRequest payload) {
        throw new UnsupportedOperationException();
    }

    @ApiOperation(value = "exclude user from channel")
    @DeleteMapping("/members/{id}")
    public void exclude(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
