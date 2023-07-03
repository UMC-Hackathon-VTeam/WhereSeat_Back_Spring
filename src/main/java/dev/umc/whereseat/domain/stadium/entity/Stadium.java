package dev.umc.whereseat.domain.stadium.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dev.umc.whereseat.common.BaseEntity;
import dev.umc.whereseat.domain.review.entity.Score;
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

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Name name;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Seat seat;

	@Builder
	public Stadium(
		Name name,
		Seat seat
	) {
		this.id = id;
		this.name = name;
		this.seat = seat;
	}


}
