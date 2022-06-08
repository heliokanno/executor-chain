package br.com.mk.notification.chain;

import br.com.mk.domain.Notification;
import br.com.mk.executor.Executor;
import br.com.mk.port.NotificationRepository;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.Objects;

@Named
@RequiredArgsConstructor
public class LoadNotification implements Executor<Notification> {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification execute(Notification input) {
        if (Objects.nonNull(input.getId())) {
            var notification = notificationRepository.findById(input.getId());
            input.setTitle(notification.getTitle());
            input.setContent(notification.getContent());
            input.setState(notification.getState());
            input.setAttempts(notification.getAttempts());
        }
        return input;
    }
}