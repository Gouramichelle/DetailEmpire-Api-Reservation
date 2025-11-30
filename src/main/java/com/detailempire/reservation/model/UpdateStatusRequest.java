package com.detailempire.reservation.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStatusRequest {

    @NotNull
    private ReservationStatus status;
}