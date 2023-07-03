package dev.umc.whereseat.domain.stadium.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetStadiumResponse {
    private String stadium;
    private String image;

    public static GetStadiumResponse of(String stadium, String image){
        return new GetStadiumResponse(stadium, image);
    }

}
