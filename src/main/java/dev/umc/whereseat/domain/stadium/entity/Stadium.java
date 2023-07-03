package dev.umc.whereseat.domain.stadium.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import dev.umc.whereseat.common.BaseEntity;
import dev.umc.whereseat.domain.seat.Seat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Stadium extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "stadium", cascade = CascadeType.ALL)
	private List<Seat> seat = new ArrayList<>();

	private String image;

	@Builder(access = AccessLevel.PRIVATE)
	public Stadium(String name) {
		this.name = name;
	}

	public void updateSeat(List<Seat> seat) {
		this.seat = seat;
	}

	public void updateImage(String image) {
		this.image = image;
	}

	public static Stadium newStadium(String name){
		return Stadium.builder()
				.name(name)
				.build();
	}


}
