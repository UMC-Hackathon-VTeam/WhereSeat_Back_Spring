package dev.umc.whereseat.domain.stadium;

import dev.umc.whereseat.common.SuccessResponse;
import dev.umc.whereseat.common.SuccessStatus;
import dev.umc.whereseat.domain.stadium.entity.GetStadiumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StadiumController {

    private final StadiumService stadiumService;

    @GetMapping("/home")
    public SuccessResponse<List<GetStadiumResponse>> getStadiumList(){
        List<GetStadiumResponse> stadiumList = stadiumService.getStadiumList();
        return new SuccessResponse<>(SuccessStatus.CREATE_DIARY,stadiumList);
    }


}
