package dev.umc.whereseat.domain.review.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import dev.umc.whereseat.common.BaseEntity;
import dev.umc.whereseat.domain.member.Member;
import dev.umc.whereseat.domain.review.dto.Request.ReviewCreateInDTO;
import dev.umc.whereseat.domain.review.dto.Request.ReviewUpdateInDTO;
import dev.umc.whereseat.domain.seat.Seat;
import dev.umc.whereseat.domain.stadium.entity.Stadium;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String image;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Score score;

	@Column(nullable = false)
	private String comment;

	@Column(nullable = false)
	private String details;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member")
	private Member member;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stadium")
	private Stadium stadium;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seat")
	private Seat seat;

	@Builder
	public Review(
		String image,
		Score score,
		String comment,
		String details,
		Member member,
		Stadium stadium,
		Seat seat
		) {
		this.image = image;
		this.score = score;
		this.comment = comment;
		this.details = details;
		this.member = member;
		this.stadium = stadium;
		this.seat = seat;
	}

	public static Review create(Member member, String image, ReviewCreateInDTO reviewCreateInDTO, Stadium stadium, Seat seat){
		return Review.builder()
			.image(image)
			.score(reviewCreateInDTO.getScore())
			.comment(reviewCreateInDTO.getComment())
			.details(reviewCreateInDTO.getDetails())
			.member(member)
			.stadium(stadium)
			.seat(seat)
			.build();
	}


	public Review update(ReviewUpdateInDTO reviewUpdateInDTO, String image, Stadium stadium, Seat seat) {
		this.image = image;
		this.score = reviewUpdateInDTO.getScore();
		this.comment = reviewUpdateInDTO.getComment();
		this.details = reviewUpdateInDTO.getDetails();
		this.stadium = stadium;
		this.seat = seat;

		return this;
	}
}
