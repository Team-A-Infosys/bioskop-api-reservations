package com.teamc.bioskop.Repository;

import com.teamc.bioskop.Model.Seats;
import com.teamc.bioskop.Model.StatusSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRepository extends JpaRepository<Seats, Long> {
    @Query(value = "select * from seats s where is_available =?1", nativeQuery = true)
    List<Seats> getSeatAvailable(StatusSeats isAvailable);

}