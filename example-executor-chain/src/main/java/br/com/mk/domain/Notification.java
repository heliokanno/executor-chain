package br.com.mk.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    private UUID id;

    private State state;

    private String title;

    private String content;

    private int attempts;

    private LocalDateTime creationDate;

    public enum State {
        FAILURE, SUCCESS
    }

}
