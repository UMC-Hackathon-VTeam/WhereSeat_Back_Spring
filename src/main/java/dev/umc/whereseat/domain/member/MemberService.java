package dev.umc.whereseat.domain.member;

import dev.umc.whereseat.domain.member.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(MemberRequestDto requestDto) {
        return memberRepository.save(new Member(requestDto.getNickname(), requestDto.getPassword())).getIdx();
    }

    public Long check(MemberRequestDto requestDto) {
        Optional<Member> findMember = memberRepository.findByNicknameAndPassword(requestDto.getNickname(), requestDto.getPassword());

        if(findMember.isPresent()) {
            return findMember.get().getIdx();
        }

        return 0L;
    }
}
