package com.tsystems.logistics.logistics_vp.exceptions;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorRepresentation {
    private String error;
    private String message;
    private java.time.LocalDateTime dateAndTime;
}
