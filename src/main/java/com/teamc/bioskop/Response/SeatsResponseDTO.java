package com.teamc.bioskop.Response;


import com.teamc.bioskop.Model.StatusSeat;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatsResponseDTO {
    private Long code;
    private StatusSeat status;
    private String studio;
    private Long seat;

    public String toString() {
        return "SeatsResponseDTO{" +
                "code=" + code +
                ", status=" + status +
                ", studio=" + studio +
                ", seat=" + seat +
                '}';
    }
}
