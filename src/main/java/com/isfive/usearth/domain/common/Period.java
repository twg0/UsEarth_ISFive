package com.isfive.usearth.domain.common;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;
@Embeddable
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime dueDate;

}
