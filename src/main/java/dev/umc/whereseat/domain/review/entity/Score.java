package dev.umc.whereseat.domain.review.entity;

public enum Score {
	ZERO(0),
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5);

	private final int value;

	Score(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
