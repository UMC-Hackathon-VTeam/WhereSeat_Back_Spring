package dev.umc.whereseat.domain.diary;

import dev.umc.whereseat.common.SuccessResponse;
import dev.umc.whereseat.domain.diary.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static dev.umc.whereseat.common.SuccessStatus.*;

@RequiredArgsConstructor
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    /**
     * 직관 일기 작성
     */
    @PostMapping("/diarys")
    public SuccessResponse<CreateDiaryResponse> createDiary(@RequestPart CreateDiaryRequest diaryRequest, @RequestPart MultipartFile image, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("currentMember");
        Long diaryId = diaryService.createDiary(memberId, diaryRequest, image);
        return new SuccessResponse<>(CREATE_DIARY, CreateDiaryResponse.of(diaryId));
    }

    /**
     * 직관 일기 수정
     */
    @PatchMapping("/diarys/{id}")
    public SuccessResponse<UpdateDiaryResponse> updateDiary(@PathVariable Long id, @RequestPart UpdateDiaryRequest diaryRequest, @RequestPart MultipartFile image) throws IOException {
        Long diaryId = diaryService.updateDiary(id, diaryRequest, image);
        return new SuccessResponse<>(UPDATE_DIARY, UpdateDiaryResponse.of(diaryId));
    }

    /**
     * 직관 일기 삭제
     */
    @DeleteMapping("/diarys/{id}")
    public SuccessResponse<Long> deleteDiary(@PathVariable Long id, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("currentMember");
        diaryService.deleteDiary(memberId, id);
        return new SuccessResponse<>(DELETE_DIARY, id);
    }

    /**
     * 직관 일기 날짜별 조회
     */
    @GetMapping("/diarys")
    public SuccessResponse<GetDiaryResponse> getDiary(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        GetDiaryResponse response = diaryService.getDiary(date);
        return new SuccessResponse<>(GET_DIARY, response);
    }

    /**
     * 직관 일기 작성한 날짜 조회
     */
    @GetMapping("/diarys/calendar")
    public SuccessResponse<List<String>> getCalendar(@RequestParam int year, @RequestParam int month, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("currentMember");
        List<String> responses = diaryService.getCalendar(memberId, year, month);
        return new SuccessResponse<>(GET_CALENDAR, responses);
    }


}
