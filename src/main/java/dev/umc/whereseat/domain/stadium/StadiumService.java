package dev.umc.whereseat.domain.stadium;

import dev.umc.whereseat.domain.seat.Seat;
import dev.umc.whereseat.domain.seat.SeatRepository;
import dev.umc.whereseat.domain.stadium.entity.GetStadiumResponse;
import dev.umc.whereseat.domain.stadium.entity.Stadium;
import dev.umc.whereseat.domain.stadium.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StadiumService {

    private final StadiumRepository stadiumRepository;
    private final SeatRepository seatRepository;

    private static final Map<Long, String> stadiumImageMap = Map.of(
            1L, "https://umc-hackathon-whereseat.s3.ap-northeast-2.amazonaws.com/image/jamsil.jpeg",
            2L, "https://umc-hackathon-whereseat.s3.ap-northeast-2.amazonaws.com/image/incheon.jpeg",
            3L, "https://umc-hackathon-whereseat.s3.ap-northeast-2.amazonaws.com/image/daejeon.jpeg",
            4L, "https://umc-hackathon-whereseat.s3.ap-northeast-2.amazonaws.com/image/gocheok.jpeg",
            5L, "https://umc-hackathon-whereseat.s3.ap-northeast-2.amazonaws.com/image/gwangju.jpeg",
            6L, "https://umc-hackathon-whereseat.s3.ap-northeast-2.amazonaws.com/image/daegu.jpeg",
            7L, "https://umc-hackathon-whereseat.s3.ap-northeast-2.amazonaws.com/image/busan.jpeg",
            8L, "https://umc-hackathon-whereseat.s3.ap-northeast-2.amazonaws.com/image/suwon.jpeg",
            9L, "https://umc-hackathon-whereseat.s3.ap-northeast-2.amazonaws.com/image/masan.jpeg"
    );

    public List<GetStadiumResponse> getStadiumList() {
        List<String> stadiumNameList = Arrays.asList(
                "서울 잠실 야구장",
                "인천 SSG 랜더스 필드",
                "대전 한화생명 이글스 파크",
                "서울 고척 스카이돔",
                "광주 KIA 챔피언스 필드",
                "대구 삼성 라이온즈 파크",
                "부산 사직야구장",
                "수원 KT 위즈 파크",
                "마산 야구 센터 창원 NC 파크"
        );

        List<GetStadiumResponse> responseList = new ArrayList<>();

        for (String name : stadiumNameList) {
            Stadium newStadium = Stadium.newStadium(name);
            stadiumRepository.save(newStadium);
            List<Seat> seatList = createSeatList(newStadium);
            newStadium.updateSeat(seatList);

            // 이미지 URL 매핑을 사용하여 이미지 업데이트
            String imageUrl = stadiumImageMap.get(newStadium.getId());
            newStadium.updateImage(imageUrl);

            responseList.add(GetStadiumResponse.of(newStadium.getName(), newStadium.getImage()));
        }

        return responseList;
    }

    public List<Seat> createSeatList(Stadium stadium){
        List<Seat> responseList = new ArrayList<>();

        if (stadium.getId() == 1L) {
            responseList.addAll(Arrays.asList(
                            "중앙석",
                            "오렌지지정석",
                            "레드지정석",
                            "네이비지정석",
                            "외야일반석",
                            "블루지정석"
                    ).stream()
                    .map(seatName -> Seat.newSeat(stadium, seatName))
                    .collect(Collectors.toList()));
        }

        if (stadium.getId() == 2L) {
            responseList.addAll(Arrays.asList(
                            "내야패밀리존",
                            "응원지정석",
                            "의자지정석",
                            "홈런커플존",
                            "외야일반석",
                            "SKY 탁자석"
                    ).stream()
                    .map(seatName -> Seat.newSeat(stadium, seatName))
                    .collect(Collectors.toList()));
        }

        if (stadium.getId() == 3L) {
            responseList.addAll(Arrays.asList(
                            "내야지정석(1층)",
                            "내야지정석(2층)",
                            "의자지정석",
                            "홈런커플존",
                            "외야일반석",
                            "SKY 탁자석"
                    ).stream()
                    .map(seatName -> Seat.newSeat(stadium, seatName))
                    .collect(Collectors.toList()));
        }

        if (stadium.getId() == 4L) {
            responseList.addAll(Arrays.asList(
                            "내야지정석(1층)",
                            "내야지정석(2층)",
                            "의자지정석",
                            "홈런커플존",
                            "외야일반석",
                            "SKY 탁자석"
                    ).stream()
                    .map(seatName -> Seat.newSeat(stadium, seatName))
                    .collect(Collectors.toList()));
        }

        if (stadium.getId() == 5L) {
            responseList.addAll(Arrays.asList(
                            "내야지정석(1층)",
                            "내야지정석(2층)",
                            "의자지정석",
                            "홈런커플존",
                            "외야일반석",
                            "SKY 탁자석"
                    ).stream()
                    .map(seatName -> Seat.newSeat(stadium, seatName))
                    .collect(Collectors.toList()));
        }

        if (stadium.getId() == 6L) {
            responseList.addAll(Arrays.asList(
                            "내야지정석(1층)",
                            "내야지정석(2층)",
                            "의자지정석",
                            "홈런커플존",
                            "외야일반석",
                            "SKY 탁자석"
                    ).stream()
                    .map(seatName -> Seat.newSeat(stadium, seatName))
                    .collect(Collectors.toList()));
        }

        if (stadium.getId() == 7L) {
            responseList.addAll(Arrays.asList(
                            "내야지정석(1층)",
                            "내야지정석(2층)",
                            "의자지정석",
                            "홈런커플존",
                            "외야일반석",
                            "SKY 탁자석"
                    ).stream()
                    .map(seatName -> Seat.newSeat(stadium, seatName))
                    .collect(Collectors.toList()));
        }

        if (stadium.getId() == 8L) {
            responseList.addAll(Arrays.asList(
                            "내야지정석(1층)",
                            "내야지정석(2층)",
                            "의자지정석",
                            "홈런커플존",
                            "외야일반석",
                            "SKY 탁자석"
                    ).stream()
                    .map(seatName -> Seat.newSeat(stadium, seatName))
                    .collect(Collectors.toList()));
        }
        if (stadium.getId() == 9L) {
            responseList.addAll(Arrays.asList(
                            "내야지정석(1층)",
                            "내야지정석(2층)",
                            "의자지정석",
                            "홈런커플존",
                            "외야일반석",
                            "SKY 탁자석"
                    ).stream()
                    .map(seatName -> Seat.newSeat(stadium, seatName))
                    .collect(Collectors.toList()));
        }

        for (Seat seat : responseList) {
            seatRepository.save(seat);
        }

        return responseList;
    }



}





