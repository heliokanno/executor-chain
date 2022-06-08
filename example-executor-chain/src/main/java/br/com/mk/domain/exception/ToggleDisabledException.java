package br.com.mk.domain.exception;

import java.text.MessageFormat;

public class ToggleDisabledException extends RuntimeException {

    public ToggleDisabledException(final String toggle) {
        super(MessageFormat.format("The {0} toggle is currently off", toggle));
    }

}
