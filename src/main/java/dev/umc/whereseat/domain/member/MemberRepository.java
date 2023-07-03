package dev.umc.whereseat.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "SELECT * FROM MEMBER WHERE NICKNAME = :nickname AND PASSWORD = :password", nativeQuery = true)
    Optional<Member> findByNicknameAndPassword(@Param("nickname") String nickname, @Param("password") String password);
}
