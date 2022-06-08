package br.com.mk.notification;

import br.com.mk.domain.Notification;

public interface SendNotification {
    void process(Notification notification);
}
