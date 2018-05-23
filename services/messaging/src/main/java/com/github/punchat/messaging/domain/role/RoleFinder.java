package com.github.punchat.messaging.domain.role;

public interface RoleFinder {
    Role byId(Long id);

    Role byName(String name);

    Role owner();
}
