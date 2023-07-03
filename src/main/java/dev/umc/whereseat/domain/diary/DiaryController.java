package dev.umc.whereseat.domain.diary;

import dev.umc.whereseat.common.SuccessResponse;
import dev.umc.whereseat.domain.diary.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static dev.umc.whereseat.common.SuccessStatus.*;

@RequiredArgsConstructor
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/diarys")
    public SuccessResponse<CreateDiaryResponse> createDiary(@RequestBody CreateDiaryRequest request) {
        Long diaryId = diaryService.createDiary(request);
        return new SuccessResponse<>(CREATE_DIARY, CreateDiaryResponse.of(diaryId));
    }

    @PatchMapping("/diarys/{id}")
    public SuccessResponse<UpdateDiaryResponse> updateDiary(@PathVariable Long id, @RequestBody UpdateDiaryRequest request){
        Long diaryId = diaryService.updateDiary(id, request);
        return new SuccessResponse<>(UPDATE_DIARY, UpdateDiaryResponse.of(diaryId));
    }

    @DeleteMapping("/diarys/{id}")
    public SuccessResponse<Long> deleteDiary(@PathVariable Long id){
        diaryService.deleteDiary(id);
        return new SuccessResponse<>(DELETE_DIARY, id);
    }

    @GetMapping("/diarys")
    public SuccessResponse<GetDiaryResponse> getDiary(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        GetDiaryResponse response = diaryService.getDiary(date);
        return new SuccessResponse<>(GET_DIARY, response);
    }

    @GetMapping("/diarys/calendar")
    public SuccessResponse<List<String>> getCalendar(@RequestParam int year, @RequestParam int month){
        List<String> responses = diaryService.getCalendar(year, month);
        return new SuccessResponse<>(GET_DIARY, responses);
    }


}
