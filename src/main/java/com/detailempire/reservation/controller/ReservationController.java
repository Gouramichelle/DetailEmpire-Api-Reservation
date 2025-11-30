package com.detailempire.reservation.controller;



import com.detailempire.reservation.model.ReservationRequest;
import com.detailempire.reservation.model.ReservationResponse;
import com.detailempire.reservation.model.UpdateStatusRequest;
import com.detailempire.reservation.security.UserPrincipal;
import com.detailempire.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@CrossOrigin
public class ReservationController {

    private final ReservationService reservationService;

    // Crear reserva
    @PostMapping
    public ReservationResponse create(
            @AuthenticationPrincipal UserPrincipal user,
            @Valid @RequestBody ReservationRequest request
    ) {
        return reservationService.create(user.getUserId(), request);
    }

    // Ver mis reservas
    @GetMapping("/my")
    public List<ReservationResponse> myReservations(
            @AuthenticationPrincipal UserPrincipal user
    ) {
        return reservationService.getMyReservations(user.getUserId());
    }

    // Eliminar una reserva mía
    @DeleteMapping("/{id}")
    public void delete(
            @AuthenticationPrincipal UserPrincipal user,
            @PathVariable Long id
    ) {
        reservationService.deleteMyReservation(user.getUserId(), id);
    }
    @PatchMapping("/admin/{id}/status")
    public ReservationResponse updateStatus(
            @PathVariable Long id,
            @RequestBody @Valid UpdateStatusRequest request
    ) {
        return reservationService.updateStatus(id, request.getStatus());
    }
    // Ver TODAS las reservas (solo ADMIN)
    @GetMapping("/admin/all")
    public List<ReservationResponse> getAllReservations() {
        return reservationService.getAllReservations();
    }

}
