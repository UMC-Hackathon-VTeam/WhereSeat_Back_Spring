package dev.umc.whereseat.domain.diary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Optional<Diary> findByVisitedAt(LocalDate date);
    List<Diary> findAllByVisitedAtBetweenOrderByVisitedAt(LocalDate startDate, LocalDate endDate);


}
