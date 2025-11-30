package com.detailempire.reservation.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ReservationResponse {

    private Long id;
    private Long userId;
    private Long vehicleId;
    private Long serviceId;
    private LocalDateTime date;
    private String notes;
    private ReservationStatus status;
    private List<String> photos;
}
