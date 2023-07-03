package dev.umc.whereseat.domain.seat;


import dev.umc.whereseat.domain.stadium.entity.Stadium;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    private String name;

    @Builder(access = AccessLevel.PRIVATE)
    private Seat(Stadium stadium, String name){
        this.stadium = stadium;
        this.name = name;
    }

    public static Seat newSeat(Stadium stadium, String name){
        return Seat.builder()
                .stadium(stadium)
                .name(name)
                .build();
    }

}
