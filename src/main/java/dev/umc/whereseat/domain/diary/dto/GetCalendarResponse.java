package dev.umc.whereseat.domain.diary.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetCalendarResponse {
    private String writeAt;

    public static GetCalendarResponse of(String writeAt){
        return new GetCalendarResponse(writeAt);
    }
}
