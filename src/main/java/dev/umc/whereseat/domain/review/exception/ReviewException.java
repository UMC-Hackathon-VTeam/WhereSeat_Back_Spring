package dev.umc.whereseat.domain.review.exception;

import dev.umc.whereseat.common.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewException extends Throwable {

	private ErrorStatus error;
}
