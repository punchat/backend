package com.github.punchat.am.domain.access;

import org.springframework.web.bind.annotation.*;

public class AccessCodeController {
    private final AccessCodeService service;

    public AccessCodeController(AccessCodeService service) {
        this.service = service;
    }

    @PostMapping("/invites/{email}/code")
    public boolean checkAccessCode(@PathVariable String email,
                                   @RequestBody String code) {
        return service.checkAccessCode(email, code);
    }
}
