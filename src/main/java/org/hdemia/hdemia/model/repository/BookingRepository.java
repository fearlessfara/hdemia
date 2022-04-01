package org.hdemia.hdemia.model.repository;

import org.hdemia.hdemia.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
