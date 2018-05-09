package com.github.punchat.am.domain.invite.workspace.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonSetter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessCodeValidation {

    private String email;

    private String code;

    @JsonIgnore
    public String getCode() {
        return code;
    }
    @JsonSetter
    public void setCode(String code) {
        this.code = code;
    }

    private AccessCodeValidationResult accessCodeValidationResult;

    @JsonGetter
    public AccessCodeValidationResult getAccessCodeValidationResult() {
        return accessCodeValidationResult;
    }

    @JsonIgnore
    public void setAccessCodeValidationResult(AccessCodeValidationResult accessCodeValidationResult) {
        this.accessCodeValidationResult = accessCodeValidationResult;
    }
}
