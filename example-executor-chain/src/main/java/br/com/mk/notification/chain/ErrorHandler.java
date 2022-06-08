package br.com.mk.notification.chain;

import br.com.mk.domain.Notification;
import br.com.mk.executor.Executor;

public class ErrorHandler implements Executor<Notification> {

    private static final int MAX_ATTEMPTS = 3;

    @Override
    public Notification execute(Notification input) {
        if (MAX_ATTEMPTS >= input.getAttempts()) {
            input.setState(Notification.State.FAILURE);
        }
        input.setAttempts(input.getAttempts() + 1);
        return input;
    }

}