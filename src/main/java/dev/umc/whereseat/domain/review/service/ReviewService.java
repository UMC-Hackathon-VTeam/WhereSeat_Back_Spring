package dev.umc.whereseat.domain.review.service;

import static dev.umc.whereseat.common.ErrorStatus.*;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.umc.whereseat.domain.review.dto.Request.ReviewCreateInDTO;
import dev.umc.whereseat.domain.review.dto.Request.ReviewUpdateInDTO;
import dev.umc.whereseat.domain.review.dto.Response.ReviewCreateOutDTO;
import dev.umc.whereseat.domain.review.dto.Response.ReviewDetailListOutDTO;
import dev.umc.whereseat.domain.review.dto.Response.ReviewUpdateOutDTO;
import dev.umc.whereseat.domain.review.entity.Review;
import dev.umc.whereseat.domain.review.exception.ReviewException;
import dev.umc.whereseat.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

	private final ReviewRepository reviewRepository;

	/**
	 * 리뷰 생성
	 */
	public ReviewCreateOutDTO createReview(ReviewCreateInDTO dto) {
		// 리뷰 엔티티 생성
		Review review = dto.toEntity();
		reviewRepository.save(review);

		return ReviewCreateOutDTO.of(review);
	}

	/**
	 * 리뷰 업데이트
	 */
	public ReviewUpdateOutDTO updateReview(Long reviewId, ReviewUpdateInDTO reviewUpdateInDTO) {
		Review review = reviewRepository.findById(reviewId);

		Review updatedReview = review.update(reviewUpdateInDTO);
		reviewRepository.save(updatedReview);

		return ReviewUpdateOutDTO.of(updatedReview);
	}

	/**
	 * 리뷰 삭제
	 */
	public String deleteReview(Long reviewId){

		Review review = reviewRepository.findById(reviewId);
		reviewRepository.delete(review);
		return "리뷰 삭제 완료";
	}
	/**
	 * 리뷰 구장별 조회
	 */


	/**
	 * 리뷰 좌석별 조회
	 */


	/**
	 * 리뷰 상세 조회
	 */
	@Transactional(readOnly = true)
	public List<ReviewDetailListOutDTO> getReview(Long reviewId){
		List<Review> findReviews = reviewRepository.findAllById(reviewId).orElse(null);
		return ReviewDetailListOutDTO.of(findReviews);
	}
}
