package dev.umc.whereseat.domain.review.dto;

import dev.umc.whereseat.domain.review.entity.Review;
import dev.umc.whereseat.domain.review.entity.Score;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ReviewCreateOutDTO {

	private Long id;
	private String image;
	private Score score;
	private String comment;
	private String block;
	private String field;

	static public ReviewCreateOutDTO of(Review review) {
		return ReviewCreateOutDTO.builder()
			.id(review.getId())
			.image(review.getImage())
			.score(review.getScore())
			.comment(review.getComment())
			.block(review.getBlock())
			.field(review.getField())
			.build();
	}

}
