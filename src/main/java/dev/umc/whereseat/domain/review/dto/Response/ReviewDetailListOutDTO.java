package dev.umc.whereseat.domain.review.dto.Response;

import java.util.List;
import java.util.stream.Collectors;

import dev.umc.whereseat.domain.review.entity.Review;
import dev.umc.whereseat.domain.review.entity.Score;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ReviewDetailListOutDTO {

	private Long id;
	private String image;
	private Score score;
	private String comment;
	private String details;

	static public List<ReviewDetailListOutDTO> of(List<Review> reviews) {
		return reviews.stream()
			.map(review -> ReviewDetailListOutDTO.builder()
			.id(review.getId())
			.image(review.getImage())
			.score(review.getScore())
			.comment(review.getComment())
			.details(review.getDetails())
			.build())
			.collect(Collectors.toList());
	}

}
