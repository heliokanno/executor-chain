package br.com.mk.notification.chain;

import br.com.mk.domain.Notification;
import br.com.mk.domain.NotificationPayload;
import br.com.mk.executor.Executor;
import br.com.mk.port.NotificationProducer;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class PublishNotification implements Executor<Notification> {

    private final NotificationProducer notificationProducer;

    @Override
    public Notification execute(Notification input) {
        if (Notification.State.SUCCESS != input.getState()) {
            notificationProducer.publish(NotificationPayload.builder()
                    .title(input.getTitle())
                    .content(input.getContent())
                    .build());
            input.setState(Notification.State.SUCCESS);
        }
        return input;
    }
}