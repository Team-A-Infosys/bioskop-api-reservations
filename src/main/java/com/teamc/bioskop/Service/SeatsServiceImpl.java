package com.teamc.bioskop.Service;

import com.teamc.bioskop.Exception.ResourceNotFoundException;
import com.teamc.bioskop.Model.Seats;
import com.teamc.bioskop.Model.StatusSeats;
import com.teamc.bioskop.Repository.SeatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SeatsServiceImpl implements SeatsService {

    private final SeatsRepository seatRepository;


    @Override
    public List<Seats> findAllseats() {

        List<Seats> optionalSeats = seatRepository.findAll();
        if (optionalSeats.isEmpty()) {
            throw new ResourceNotFoundException("table seats have not value");
        }
        return seatRepository.findAll();
    }

    @Override
    public Optional<Seats> findbyid(Long id) {

        Optional<Seats> optionalSeats = seatRepository.findById(id);
        if (optionalSeats == null) {
            throw new ResourceNotFoundException(" Seats not Exist with id :" + id);
        }
        return seatRepository.findById(id);
    }

    @Override
    public Seats createseat(Seats seat) {
        return seatRepository.save(seat);
    }

    @Override
    public Seats updateseat(Seats seat, Long seatId) {
        Optional<Seats> optionalSeats = seatRepository.findById(seatId);
        if (optionalSeats == null) {
            throw new ResourceNotFoundException("Films not exist with id" + seatId);
        }
        return seatRepository.save(seat);
    }

    @Override
    public void deleteseat(Long seatId) {
        Optional<Seats> optionalSeats = seatRepository.findById(seatId);
        if (optionalSeats == null) {
            throw new ResourceNotFoundException("Seats not exist with id :" + seatId);
        }
        Seats seats = seatRepository.getReferenceById(seatId);
        this.seatRepository.delete(seats);

    }


    public Seats getReferenceById(Long id) {
        return this.seatRepository.getReferenceById(id);
    }

    @Override
    public List<Seats> getSeatAvailable(StatusSeats isAvailable) {

        List<Seats> optionalSeats = seatRepository.getSeatAvailable(isAvailable);
        if (optionalSeats == null) {
            throw new ResourceNotFoundException("Seats not exist with id : " + isAvailable);
        }
        return this.seatRepository.getSeatAvailable(isAvailable);
    }

    @Override
    public List<Seats> getSeatsByStudioName(String studioName){
        List<Seats> optionStudio = seatRepository.findByStudioName(studioName);

        return optionStudio;
    }

    @Override
    public Page<Seats> findPaginated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);

        return this.seatRepository.findAll(pageable);
    }

    @Override
    public Page<Seats> findPaginatedByStatus(StatusSeats isAvailable, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.seatRepository.findSeatsByIsAvailable(isAvailable, pageable);
    }
}