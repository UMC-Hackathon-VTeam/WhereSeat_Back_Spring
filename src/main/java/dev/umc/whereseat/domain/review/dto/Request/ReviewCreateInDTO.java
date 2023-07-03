package dev.umc.whereseat.domain.review.dto.Request;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.Column;

import dev.umc.whereseat.domain.review.entity.Review;
import dev.umc.whereseat.domain.review.entity.Score;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewCreateInDTO {

	private String image;

	private Score score;

	private String comment;

	private String details;

	public Review toEntity(){
		return Review.builder()
			.image(getImage())
			.score(getScore())
			.comment(getComment())
			.details(getDetails())
			.build();
	}

}