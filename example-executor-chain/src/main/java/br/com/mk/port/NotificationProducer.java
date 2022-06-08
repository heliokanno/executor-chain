package br.com.mk.port;

import br.com.mk.domain.NotificationPayload;

public interface NotificationProducer {
    void publish(NotificationPayload payload);
}
