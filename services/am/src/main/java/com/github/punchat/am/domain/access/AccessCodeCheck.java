package com.github.punchat.am.domain.access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  AccessCodeCheck {
    private int refreshTimeInMinutes;
    private long accessTimeInMinutes;
}
