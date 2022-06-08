package br.com.mk.notification.chain;

import br.com.mk.domain.Notification;
import br.com.mk.domain.exception.ToggleDisabledException;
import br.com.mk.executor.Executor;
import br.com.mk.port.ToggleClient;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class CheckToggleNotificationEnable implements Executor<Notification> {

    private static final String TOGGLE_NOTIFICATION_ENABLE = "NotificationEnable";

    private final ToggleClient toggleClient;

    @Override
    public Notification execute(Notification input) {
        var isEnabled = toggleClient.isEnabled(TOGGLE_NOTIFICATION_ENABLE);
        if (!isEnabled) {
            throw new ToggleDisabledException(TOGGLE_NOTIFICATION_ENABLE);
        }
        return input;
    }

}
