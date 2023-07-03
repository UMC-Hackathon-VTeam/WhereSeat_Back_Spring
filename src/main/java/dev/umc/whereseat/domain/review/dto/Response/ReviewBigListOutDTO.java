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
public class ReviewBigListOutDTO {

	private Long id;
	private Score score;
	private String block;
	private String field;

	static public List<ReviewBigListOutDTO> of(List<Review> reviews) {
		return reviews.stream()
			.map(review -> ReviewBigListOutDTO.builder()
				.id(review.getId())
				.score(review.getScore())
				.block(review.getBlock())
				.field(review.getField())
				.build())
			.collect(Collectors.toList());
	}

}
