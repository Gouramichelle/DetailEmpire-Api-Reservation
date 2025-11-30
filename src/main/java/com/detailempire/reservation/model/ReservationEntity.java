package com.detailempire.reservation.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;          // id

    @Column(nullable = false)
    private Long userId;      // id_user (del token)

    @Column(nullable = false)
    private Long vehicleId;   // vehicle (id del vehículo)

    @Column(nullable = false)
    private Long serviceId;   // service (id del servicio)

    @Column(nullable = false)
    private LocalDateTime date;  // date (fecha y hora de la reserva)

    private String notes;     // notes

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    // Guardamos la lista de fotos como un único string (URLs separadas por ";")
    @Lob
    private String photos;}    // photos