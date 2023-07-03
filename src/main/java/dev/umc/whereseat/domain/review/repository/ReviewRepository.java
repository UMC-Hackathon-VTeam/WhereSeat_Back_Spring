package dev.umc.whereseat.domain.review.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.umc.whereseat.domain.review.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	Review findById(Long reviewId);
}
