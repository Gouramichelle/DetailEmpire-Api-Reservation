package com.detailempire.reservation.service;


import com.detailempire.reservation.model.ReservationRequest;
import com.detailempire.reservation.model.ReservationResponse;
import com.detailempire.reservation.model.ReservationEntity;
import com.detailempire.reservation.model.ReservationStatus;
import com.detailempire.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationResponse create(Long userId, ReservationRequest request) {

        String photosString = null;
        if (request.getPhotos() != null && !request.getPhotos().isEmpty()) {
            photosString = String.join(";", request.getPhotos());
        }

        ReservationEntity entity = ReservationEntity.builder()
                .userId(userId)
                .vehicleId(request.getVehicleId())
                .serviceId(request.getServiceId())
                .date(request.getDate())
                .notes(request.getNotes())
                .photos(photosString)
                .status(ReservationStatus.PENDING)
                .build();

        ReservationEntity saved = reservationRepository.save(entity);
        return toResponse(saved);
    }

    public List<ReservationResponse> getMyReservations(Long userId) {
        return reservationRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }
    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(this::toResponse)  // suponiendo que ya tienes este helper
                .collect(Collectors.toList());
    }

    public void deleteMyReservation(Long userId, Long reservationId) {
        ReservationEntity entity = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        if (!entity.getUserId().equals(userId)) {
            throw new RuntimeException("No puedes eliminar una reserva que no es tuya");
        }

        reservationRepository.delete(entity);
    }

    private ReservationResponse toResponse(ReservationEntity e) {
        List<String> photosList = Collections.emptyList();
        if (e.getPhotos() != null && !e.getPhotos().isBlank()) {
            photosList = Arrays.asList(e.getPhotos().split(";"));
        }

        return ReservationResponse.builder()
                .id(e.getId())
                .userId(e.getUserId())
                .vehicleId(e.getVehicleId())
                .serviceId(e.getServiceId())
                .date(e.getDate())
                .notes(e.getNotes())
                .photos(photosList)
                .status(e.getStatus())
                .build();
    }
    public ReservationResponse updateStatus(Long id, ReservationStatus newStatus) {
        ReservationEntity e = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        e.setStatus(newStatus);
        ReservationEntity saved = reservationRepository.save(e);
        return toResponse(saved);
    }
}
