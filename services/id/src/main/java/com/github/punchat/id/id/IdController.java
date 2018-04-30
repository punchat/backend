package com.github.punchat.id.id;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@RestController
public class IdController {
    private final IdService idService;

    public IdController(IdService idService) {
        this.idService = idService;
    }

    @GetMapping("/id")
    @PreAuthorize("hasTrustedScope()")
    public long next() {
        log.info("next");
        return idService.next();
    }
}
