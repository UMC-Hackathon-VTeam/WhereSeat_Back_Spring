package dev.umc.whereseat.domain.review.api;

import static dev.umc.whereseat.common.SuccessStatus.*;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.umc.whereseat.common.SuccessResponse;
import dev.umc.whereseat.domain.review.dto.Request.ReviewCreateInDTO;
import dev.umc.whereseat.domain.review.dto.Request.ReviewUpdateInDTO;
import dev.umc.whereseat.domain.review.dto.Response.ReviewCreateOutDTO;
import dev.umc.whereseat.domain.review.dto.Response.ReviewUpdateOutDTO;
import dev.umc.whereseat.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewApiController {

	private final ReviewService reviewService;

	@PostMapping
	public SuccessResponse<ReviewCreateOutDTO> createReview(@Validated @RequestBody ReviewCreateInDTO dto) {
		ReviewCreateOutDTO reviewCreateOutDTO = reviewService.createReview(dto);
		return new SuccessResponse<>(CREATE_REVIEW, reviewCreateOutDTO);
	}

	@PatchMapping("/{reviewId}")
	public SuccessResponse<ReviewUpdateOutDTO> updateReview(@PathVariable Long reviewId,
		@Validated @RequestBody ReviewUpdateInDTO dto){
		ReviewUpdateOutDTO reviewUpdateOutDTO = reviewService.updateReview(reviewId, dto);
		return new SuccessResponse<>(UPDATE_REVIEW, reviewUpdateOutDTO);
	}

}
