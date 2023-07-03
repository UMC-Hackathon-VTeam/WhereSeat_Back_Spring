package dev.umc.whereseat.domain.stadium.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.umc.whereseat.domain.stadium.entity.Stadium;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Integer> {
	Stadium findByName(String name);

	Stadium findById(Long stadiumId);

	List<Stadium> findAllByName(String name);

	Stadium findByNameContaining(String name);
}
