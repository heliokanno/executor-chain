package br.com.mk.notification.chain;

import br.com.mk.domain.Notification;
import br.com.mk.executor.Executor;
import br.com.mk.port.NotificationRepository;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.Objects;
import java.util.UUID;

@Named
@RequiredArgsConstructor
public class SaveNotification implements Executor<Notification> {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification execute(Notification input) {
        if (Objects.nonNull(input.getId())) {
            input.setId(UUID.randomUUID());
        }
        notificationRepository.save(input);
        return input;
    }
}