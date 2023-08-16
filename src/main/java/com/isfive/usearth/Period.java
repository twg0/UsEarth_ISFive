package com.isfive.usearth;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;
@Embeddable
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime dueDate;

}
