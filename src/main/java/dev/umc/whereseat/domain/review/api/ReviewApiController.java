package dev.umc.whereseat.domain.review.api;

import static dev.umc.whereseat.common.SuccessStatus.*;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.umc.whereseat.common.SuccessResponse;
import dev.umc.whereseat.domain.review.dto.ReviewCreateInDTO;
import dev.umc.whereseat.domain.review.dto.ReviewCreateOutDTO;
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

}
