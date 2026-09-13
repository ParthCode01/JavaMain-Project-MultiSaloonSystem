package com.parth.saloonmanagement.repository;


import com.parth.saloonmanagement.entity.Booking;
import com.parth.saloonmanagement.entity.Stylist;
import com.parth.saloonmanagement.entity.Tenant;
import com.parth.saloonmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking>findByStylistAndStartTimeLessThanAndEndTimeGreaterThan(
      Stylist stylist, LocalDateTime endTime , LocalDateTime startTime
    );

    List<Booking> findByTenant(Tenant tenant);
    List<Booking> findByUser(User user);

    Optional<Booking> findByIdAndTenant(Long id, Tenant tenant);

    Optional<Booking> findByIdAndUser(Long id, User user);
}
