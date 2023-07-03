package dev.umc.whereseat.domain.review.dto.Response;

import dev.umc.whereseat.domain.review.entity.Review;
import dev.umc.whereseat.domain.review.entity.Score;
import dev.umc.whereseat.domain.stadium.entity.Stadium;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ReviewUpdateOutDTO {

	private Long id;
	private String image;
	private Score score;
	private String comment;
	private String details;
	private Stadium stadium;

	static public ReviewUpdateOutDTO of(Review review) {
		return ReviewUpdateOutDTO.builder()
			.id(review.getId())
			.image(review.getImage())
			.score(review.getScore())
			.comment(review.getComment())
			.details(review.getDetails())
			.stadium(review.getStadium())
			.build();
	}

}
