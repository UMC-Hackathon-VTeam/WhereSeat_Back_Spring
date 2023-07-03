package dev.umc.whereseat.domain.review.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.umc.whereseat.domain.review.dto.ReviewCreateInDTO;
import dev.umc.whereseat.domain.review.dto.ReviewCreateOutDTO;
import dev.umc.whereseat.domain.review.entity.Review;
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
	public ReviewCreateOutDTO createReview(ReviewCreateInDTO dto){
		// 리뷰 엔티티 생성
		Review review = dto.toEntity();
		reviewRepository.save(review);

		return ReviewCreateOutDTO.of(review);
	}

}
