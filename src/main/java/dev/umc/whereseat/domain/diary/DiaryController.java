package dev.umc.whereseat.domain.diary;

import dev.umc.whereseat.common.SuccessResponse;
import dev.umc.whereseat.domain.diary.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

import static dev.umc.whereseat.common.SuccessStatus.*;

@RequiredArgsConstructor
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/diarys")
    public SuccessResponse<CreateDiaryResponse> createDiary(@RequestBody CreateDiaryRequest diaryRequest, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("currentMember");
        Long diaryId = diaryService.createDiary(memberId, diaryRequest);
        return new SuccessResponse<>(CREATE_DIARY, CreateDiaryResponse.of(diaryId));
    }

    @PatchMapping("/diarys/{id}")
    public SuccessResponse<UpdateDiaryResponse> updateDiary(@PathVariable Long id, @RequestBody UpdateDiaryRequest diaryRequest){
        Long diaryId = diaryService.updateDiary(id, diaryRequest);
        return new SuccessResponse<>(UPDATE_DIARY, UpdateDiaryResponse.of(diaryId));
    }

    @DeleteMapping("/diarys/{id}")
    public SuccessResponse<Long> deleteDiary(@PathVariable Long id, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("currentMember");
        diaryService.deleteDiary(memberId, id);
        return new SuccessResponse<>(DELETE_DIARY, id);
    }

    @GetMapping("/diarys")
    public SuccessResponse<GetDiaryResponse> getDiary(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        GetDiaryResponse response = diaryService.getDiary(date);
        return new SuccessResponse<>(GET_DIARY, response);
    }

    @GetMapping("/diarys/calendar")
    public SuccessResponse<List<String>> getCalendar(@RequestParam int year, @RequestParam int month, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("currentMember");
        List<String> responses = diaryService.getCalendar(memberId, year, month);
        return new SuccessResponse<>(GET_CALENDAR, responses);
    }


}
