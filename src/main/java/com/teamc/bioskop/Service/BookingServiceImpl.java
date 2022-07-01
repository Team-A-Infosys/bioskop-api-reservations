package com.teamc.bioskop.Service;

import com.teamc.bioskop.Exception.ResourceNotFoundException;
import com.teamc.bioskop.Model.Booking;
import com.teamc.bioskop.Model.Films;
import com.teamc.bioskop.Model.Schedule;
import com.teamc.bioskop.Model.Seats;
import com.teamc.bioskop.Repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService{

    private BookingRepository bookingRepository;

    @Override
    public List<Booking> getAll() {
        List<Booking> optionalBooking = bookingRepository.findAll();
        if(optionalBooking.isEmpty()){
            throw new ResourceNotFoundException("Data Booking not exist");
        }
        return this.bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getBookingById(Long Id) throws ResourceNotFoundException {
        Optional<Booking> optionalBooking = bookingRepository.findById(Id);
        if(optionalBooking == null){
            throw new ResourceNotFoundException("Booking not exist with id " + Id);
        }
        return this.bookingRepository.findById(Id);
    }

    @Override
    public Booking updateBooking(Booking booking) throws ResourceNotFoundException {
        Optional<Booking> optionalBooking = bookingRepository.findById(booking.getBookingId());
        if(optionalBooking == null){
            throw new ResourceNotFoundException("Booking not exist with id " + booking.getBookingId());
        }
        return this.bookingRepository.save(booking);
    }

    @Override
    public Booking getReferenceById(Long Id) {
        return this.bookingRepository.getReferenceById(Id);
    }

    @Override
    public Booking createBooking(Booking booking) {
          return this.bookingRepository.save(booking);
    }

    @Override
    public void deleteSBookingById(Long Id) throws ResourceNotFoundException{
        Optional<Booking> optionalBooking = bookingRepository.findById(Id);
        if(optionalBooking == null){
            throw new ResourceNotFoundException("Booking not exist with id " + Id);
        }
        Booking booking = bookingRepository.getReferenceById(Id);
        this.bookingRepository.delete(booking);
    }

    //custom select
    @Override
    public List<Booking> getBookingByFilmName(String name) {
        List<Booking> optionalBooking = bookingRepository.getBookingByFilmName(name);
        if(optionalBooking.isEmpty()){
            throw new ResourceNotFoundException("Booking not exist with Filmname " +name);
        }
        return this.bookingRepository.getBookingByFilmName(name);
    }

}