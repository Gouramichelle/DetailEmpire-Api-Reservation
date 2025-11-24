package com.detailempire.reservation.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationRequest {

    @NotNull
    private Long vehicleId;

    @NotNull
    private Long serviceId;

    @NotNull
    @Future
    private LocalDateTime date; // formato ISO: "2025-12-01T10:00:00"

    private String notes;

    // Lista de fotos (por ejemplo URLs). En la BD las guardamos como String.
    private List<String> photos;
}
