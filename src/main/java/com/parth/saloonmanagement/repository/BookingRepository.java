package com.parth.saloonmanagement.repository;


import com.parth.saloonmanagement.entity.Booking;
import com.parth.saloonmanagement.entity.Stylist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking>findByStylistAndStartTimeLessThanAndEndTimeGreaterThan(
      Stylist stylist, LocalDateTime endTime , LocalDateTime startTime
    );

}
