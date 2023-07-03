package dev.umc.whereseat.domain.diary.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateDiaryResponse {
    private Long id;

    public static CreateDiaryResponse of(Long id){
        return new CreateDiaryResponse(id);
    }
}
