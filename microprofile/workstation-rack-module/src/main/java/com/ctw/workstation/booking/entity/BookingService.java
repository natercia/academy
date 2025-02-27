package com.ctw.workstation.booking.entity;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.repositories.BookingRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.Collection;

@Singleton
public class BookingService {

    @Inject
    BookingRepository bookingRepo;

    public BookingReturnDTO[] getAllBookings(){
        Collection<Booking> bookings = bookingRepo.getAll();
        return bookings.stream().map(Booking::toDTO).toList().toArray(new BookingReturnDTO[bookings.size()]);
    }

    public BookingReturnDTO getBookingById(Long bookingId){
        return bookingRepo.getById(bookingId).toDTO();
    }

    @Transactional
    public BookingReturnDTO updateBooking(Long id, BookingInputDTO input){
        Booking booking = bookingRepo.getById(id);
        if (input.rackId() != null) {
            booking.setRackId(input.rackId());
        }
        if(input.requesterId()!=null) {
            booking.setRequesterId(input.requesterId());
        }
        if(input.bookFrom() != null){
            booking.setBookFrom(input.bookFrom());
        }
        if(input.bookTo() != null) {
            booking.setBookTo(input.bookTo());
        }
        bookingRepo.persistAndFlush(booking);
        return booking.toDTO();
    }

    @Transactional
    public void deleteBookingById(Long bookingId){
        bookingRepo.deleteById(bookingId);
    }

    @Transactional
    public BookingReturnDTO createBooking(BookingInputDTO input){
        Booking newBooking = new Booking();
        newBooking.setRackId(input.rackId());
        newBooking.setBookFrom(input.bookFrom());
        newBooking.setBookTo(input.bookTo());
        newBooking.setRequesterId(input.requesterId());
        bookingRepo.persistAndFlush(newBooking);
        return newBooking.toDTO();
    }
}
