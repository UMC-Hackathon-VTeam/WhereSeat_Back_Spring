package dev.umc.whereseat.domain.review.api;

import static dev.umc.whereseat.common.SuccessStatus.*;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import dev.umc.whereseat.domain.review.dto.Response.ReviewDetailListOutDTO;
import dev.umc.whereseat.domain.review.dto.Response.ReviewUpdateOutDTO;
import dev.umc.whereseat.domain.review.entity.Review;
import dev.umc.whereseat.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/reviews")
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

	@DeleteMapping("/{reviewId}")
	public SuccessResponse<String> deleteReview(@PathVariable Long reviewId){
		return new SuccessResponse<>(SUCCESS, reviewService.deleteReview(reviewId));
	}

	/**
	 * 세부 리뷰 조회
	 * */
	@GetMapping("/{reviewId}")
	public SuccessResponse<List<ReviewDetailListOutDTO>> detailReview(@PathVariable Long reviewId){
		return new SuccessResponse<>(SUCCESS, reviewService.getReview(reviewId));
	}

	/**
	 * 구단별 리뷰 조회
	 * */
	@GetMapping(value = "/stadium/{stadiumName}", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public SuccessResponse<List<ReviewDetailListOutDTO>> getStadiumReview(@PathVariable String stadiumName){
		return new SuccessResponse<>(SUCCESS, reviewService.getStadiumReview(stadiumName));
	}

	/**
	 * 좌석별 리뷰 조회
	 * */
	@GetMapping(value = "/seat/{seats}", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public SuccessResponse<List<ReviewDetailListOutDTO>> getSeatReview(@PathVariable String seats){
		return new SuccessResponse<>(SUCCESS, reviewService.getSeatReview(seats));
	}

}
