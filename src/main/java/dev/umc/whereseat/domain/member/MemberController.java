package dev.umc.whereseat.domain.member;

import dev.umc.whereseat.common.SuccessResponse;
import dev.umc.whereseat.domain.member.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static dev.umc.whereseat.common.SuccessStatus.CREATE_USER;
import static dev.umc.whereseat.common.SuccessStatus.SUCCESS;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public SuccessResponse<Long> joinMember(@RequestBody MemberRequestDto requestDto) {
        Long memberId = memberService.join(requestDto);
        return new SuccessResponse<>(CREATE_USER, memberId);
    }

     @PostMapping("/login")
    public SuccessResponse<Long> loginMember(@RequestBody MemberRequestDto requestDto, HttpServletRequest request) {
         Long memberId = memberService.check(requestDto);

         if(memberId == 0L) {  // 로그인 실패 예외처리 필요
             // System.out.println("로그인 실패");
         }

         HttpSession session = request.getSession();
         session.setAttribute("currentMember", memberId);

         // 현재 사용자 세션 값 가져오는 부분
         // Long currentMember = (Long) session.getAttribute("currentMember");
         // System.out.println("currentMember = " + currentMember);

         return new SuccessResponse<>(SUCCESS, memberId);
     }

}
