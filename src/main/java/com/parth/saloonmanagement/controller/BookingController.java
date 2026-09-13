package com.parth.saloonmanagement.controller;

import com.parth.saloonmanagement.dto.BookingRequest;
import com.parth.saloonmanagement.dto.BookingResponse;
import com.parth.saloonmanagement.dto.BookingStatusRequest;
import com.parth.saloonmanagement.entity.Booking;
import com.parth.saloonmanagement.entity.BookingStatus;
import com.parth.saloonmanagement.service.BookingService;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        List<BookingResponse> responses = bookingService.getAllBookings();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id) {
        BookingResponse response = bookingService.getBookingById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BookingResponse>updateBookingStatus(
            @PathVariable Long id ,
            @RequestBody BookingStatusRequest request
            ) throws AccessDeniedException {

        BookingResponse response = bookingService.updateBookingStatus(id , request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookingResponse> deleteBooking(@PathVariable Long id) {
        BookingResponse response = bookingService.deleteBooking(id);
        return ResponseEntity.ok(response);
    }
}