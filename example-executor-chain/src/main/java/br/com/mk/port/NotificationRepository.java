package br.com.mk.port;

import br.com.mk.domain.Notification;

import java.util.UUID;

public interface NotificationRepository {
    void save(Notification notification);

    Notification findById(UUID notificationId);
}
