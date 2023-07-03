package dev.umc.whereseat.domain.stadium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.umc.whereseat.domain.stadium.entity.Stadium;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Integer> {
	Stadium findByName(String name);
}
