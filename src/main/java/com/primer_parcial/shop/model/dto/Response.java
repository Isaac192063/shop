package com.primer_parcial.shop.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class Response <T> {
    private LocalDateTime date;
    private int statusCode;
    private T message;
}

