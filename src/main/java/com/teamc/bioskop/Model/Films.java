package com.teamc.bioskop.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teamc.bioskop.DTO.FilmsResponseDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "films")
public class Films {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmId;
    private String name;
    private Integer isPlaying;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

        public FilmsResponseDTO convertToResponse() {
        return FilmsResponseDTO.builder().code(this.filmId)
                .title(this.name)
                .status(this.isPlaying)
                .build();
    }

    @Override
    public String toString() {
        return "Films{" +
                "filmId=" + filmId +
                ", name='" + name + '\'' +
                ", isPlaying=" + isPlaying +
                '}';
    }
}