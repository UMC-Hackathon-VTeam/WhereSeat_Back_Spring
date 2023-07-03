package dev.umc.whereseat.domain.diary.dto;

import dev.umc.whereseat.domain.diary.Diary;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetDiaryResponse {
    private String image;
    private LocalDate visitedAt;
    private String title;
    private String comment;

    public static GetDiaryResponse of(Diary diary){
        return new GetDiaryResponse(diary.getImage(), diary.getVisitedAt(), diary.getTitle(), diary.getComment());
    }
}
