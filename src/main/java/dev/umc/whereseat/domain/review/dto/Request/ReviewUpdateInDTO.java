package dev.umc.whereseat.domain.review.dto.Request;

import dev.umc.whereseat.domain.review.entity.Review;
import dev.umc.whereseat.domain.review.entity.Score;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewUpdateInDTO {

	private Score score;

	private String comment;

	private String details;

	private String name;

	private String seat;

}
