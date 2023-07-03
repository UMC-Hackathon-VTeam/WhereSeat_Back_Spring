package dev.umc.whereseat.domain.seat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
	Seat findByName(String name);

	Seat findByNameContaining(String seat);
}
