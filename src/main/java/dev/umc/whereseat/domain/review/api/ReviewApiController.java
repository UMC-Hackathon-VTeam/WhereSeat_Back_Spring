package dev.umc.whereseat.domain.review.api;

import static dev.umc.whereseat.common.SuccessStatus.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	/**
	 * 리뷰 작성
	 * */

	@PostMapping
	public SuccessResponse<ReviewCreateOutDTO> createReview(@Validated @RequestPart ReviewCreateInDTO dto,
		@RequestPart MultipartFile image,
		HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		Long memberId = (Long) session.getAttribute("currentMember");
		ReviewCreateOutDTO reviewCreateOutDTO = reviewService.createReview(memberId, image, dto);
		return new SuccessResponse<>(CREATE_REVIEW, reviewCreateOutDTO);
	}
	/**
	 * 리뷰 업데이트
	 * */
	@PatchMapping("/{reviewId}")
	public SuccessResponse<ReviewUpdateOutDTO> updateReview(@PathVariable Long reviewId,
		@Validated @RequestPart ReviewUpdateInDTO dto,
		@RequestPart MultipartFile image) throws IOException {
		ReviewUpdateOutDTO reviewUpdateOutDTO = reviewService.updateReview(reviewId, dto, image);
		return new SuccessResponse<>(UPDATE_REVIEW, reviewUpdateOutDTO);
	}

	/***
	 *개별 리뷰 삭제
	 */
	@DeleteMapping("/{reviewId}")
	public SuccessResponse<String> deleteReview(@PathVariable Long reviewId, HttpServletRequest request){
		HttpSession session = request.getSession();
		Long memberId = (Long) session.getAttribute("currentMember");
		return new SuccessResponse<>(SUCCESS, reviewService.deleteReview(memberId,reviewId));
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
	@GetMapping(value = "/stadium/{stadiumName}")
	public SuccessResponse<List<Review>> getStadiumReview(@PathVariable String stadiumName){
		return new SuccessResponse<>(SUCCESS, reviewService.getStadiumReview(stadiumName));
	}

	/**
	 * 좌석별 리뷰 조회
	 * */
	@GetMapping(value = "/seat/{seats}")
	public SuccessResponse<List<ReviewDetailListOutDTO>> getSeatReview(@PathVariable String seats){
		return new SuccessResponse<>(SUCCESS, reviewService.getSeatReview(seats));
	}

}
