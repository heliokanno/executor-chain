package br.com.mk.notification;

import br.com.mk.domain.Notification;
import br.com.mk.executor.ExecutorChain;
import br.com.mk.notification.chain.CheckToggleNotificationEnable;
import br.com.mk.notification.chain.ErrorHandler;
import br.com.mk.notification.chain.LoadNotification;
import br.com.mk.notification.chain.PublishNotification;
import br.com.mk.notification.chain.SaveNotification;
import br.com.mk.notification.chain.ValidateNotification;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class SendNotificationImpl implements SendNotification {

    private final LoadNotification loadNotification;

    private final ValidateNotification validateNotification;

    private final ErrorHandler errorHandler;

    private final CheckToggleNotificationEnable checkToggleNotificationEnable;

    private final PublishNotification publishNotification;

    private final SaveNotification saveNotification;

    public void process(Notification notification) {
        var operation = buildExecutorChain();
        operation.execute(notification);
    }

    private ExecutorChain<Notification> buildExecutorChain() {
        var beforeAll = ExecutorChain.<Notification>builder()
                .chain(loadNotification)
                .chain(validateNotification)
                .build();

        return ExecutorChain.<Notification>builder()
                .errorHandler(errorHandler::execute)
                .beforeAll(beforeAll::execute)
                .chain(checkToggleNotificationEnable)
                .chain(publishNotification)
                .afterAll(saveNotification::execute)
                .build();
    }

}
