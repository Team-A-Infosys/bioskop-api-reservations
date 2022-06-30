package com.teamc.bioskop.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "seats")
public class Seats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @Column(name = "seat_number")
    private long seatNumber;

    @Column(name = "studio_name")
    private String studioName;

    @Column(name = "is_available")
    private int isAvailable;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    public Seats(long seatId, long seatNumber, String studioName, int isAvailable){
        super();
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.studioName = studioName;
        this.isAvailable = isAvailable;
    }

    public Long getSeatId() {

        return seatId;
    }

    public void setSeatId(Long seatId) {

        this.seatId = seatId;
    }

    public Long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Long seatNumber) {

        this.seatNumber = seatNumber;
    }

    public String getStudioName() {

        return studioName;
    }

    public void setStudioName(String studioName) {

        this.studioName = studioName;
    }

    public int getIsAvailable() {

        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {

        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Seats{" +
                "seatId=" + seatId +
                ", seatNumber=" + seatNumber +
                ", studioName='" + studioName + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}