package dev.umc.whereseat.domain.review.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.umc.whereseat.domain.review.entity.Review;
import dev.umc.whereseat.domain.stadium.entity.Stadium;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	Review findById(Long reviewId);

	Optional<List<Review>> findAllById(Long reviewId);
	Optional<List<Review>> findAllByStadium(Stadium stadium);

	Optional<List<Review>> findAllByDetails(String details);

	void deleteById(Long reviewId);
}
