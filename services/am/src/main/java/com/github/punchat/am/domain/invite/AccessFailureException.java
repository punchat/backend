package com.github.punchat.am.domain.invite;

public class AccessFailureException extends BadRequestException{
        private final static String MESSAGE = "You haven't access. Please contact the administrator.";

        public AccessFailureException() {
            super(MESSAGE);
        }
}
