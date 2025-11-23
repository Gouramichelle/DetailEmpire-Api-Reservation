package com.detailempire.reservation.repository;

import com.detailempire.reservation.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findByUserId(Long userId);
}
