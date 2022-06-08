package br.com.mk.notification.chain;

import br.com.mk.domain.Notification;
import br.com.mk.executor.Executor;
import br.com.mk.executor.exception.ExecutorFlowBreakerException;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.Objects;

@Named
@RequiredArgsConstructor
public class ValidateNotification implements Executor<Notification> {

    @Override
    public Notification execute(Notification input) {
        if (Objects.isNull(input.getTitle()) || Objects.isNull(input.getContent())) {
            input.setState(Notification.State.FAILURE);
            throw new ExecutorFlowBreakerException("Invalid fields");
        }
        return input;
    }

}
