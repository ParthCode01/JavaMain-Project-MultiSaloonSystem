package com.parth.saloonmanagement.service;


import com.parth.saloonmanagement.dto.BookingRequest;
import com.parth.saloonmanagement.dto.BookingResponse;
import com.parth.saloonmanagement.entity.*;
import com.parth.saloonmanagement.exception.BookingConflictException;
import com.parth.saloonmanagement.exception.ResourceNotFoundException;
import com.parth.saloonmanagement.repository.BookingRepository;
import com.parth.saloonmanagement.repository.StylistRepository;
import com.parth.saloonmanagement.repository.TreatmentRepository;
import com.parth.saloonmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TreatmentRepository treatmentRepository;
    private final StylistRepository stylistRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, TreatmentRepository treatmentRepository, StylistRepository stylistRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.treatmentRepository = treatmentRepository;
        this.stylistRepository = stylistRepository;
    }

    public BookingResponse createBooking(BookingRequest bookingRequest){

        User user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Treatment treatment = treatmentRepository.findById(bookingRequest.getTreatmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found"));
        Stylist stylist = stylistRepository.findById(bookingRequest.getStylistId())
                .orElseThrow(() -> new ResourceNotFoundException("Stylist not found"));


        LocalDateTime startTime = bookingRequest.getStartTime();
        LocalDateTime endTime = startTime.plusMinutes(treatment.getDurationMinutes());

        List<Booking> conflits = bookingRepository
                .findByStylistAndStartTimeLessThanAndEndTimeGreaterThan(stylist , endTime , startTime );


        if(!conflits.isEmpty()){
            throw new BookingConflictException("Stylist is already booked");
        }

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setStylist(stylist);
        booking.setTreatment(treatment);
        booking.setTenant(stylist.getTenant());
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setStatus(BookingStatus.PENDING);

        Booking savedBooking = bookingRepository.save(booking);

        return BookingResponse.builder()
                .id(savedBooking.getId())
                .status(savedBooking.getStatus())
                .startTime(savedBooking.getStartTime())
                .endTime(savedBooking.getEndTime())
                .treatmentName(treatment.getName())
                .stylistName(stylist.getName())
                .build();

    }



}
