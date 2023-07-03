package dev.umc.whereseat.domain.diary;

import dev.umc.whereseat.domain.diary.dto.CreateDiaryRequest;
import dev.umc.whereseat.domain.diary.dto.GetDiaryResponse;
import dev.umc.whereseat.domain.diary.dto.UpdateDiaryRequest;
import dev.umc.whereseat.domain.member.Member;
import dev.umc.whereseat.domain.member.MemberRepository;
import dev.umc.whereseat.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;
    private final FileUploadUtil fileUploadUtil;

    @Transactional
    public Long createDiary(Long memberId, CreateDiaryRequest request, MultipartFile image) throws IOException {
        Member member = memberRepository.findById(memberId).get();
        String imgUrl = fileUploadUtil.uploadFile("diary", image);
        Diary newDiary = Diary.newDiary(member, imgUrl, request);
        diaryRepository.save(newDiary);

        return newDiary.getId();
    }

    @Transactional
    public Long updateDiary(Long diaryId, UpdateDiaryRequest request) {
        Diary diary = diaryRepository.findById(diaryId).get();
        diary.update(request.getImage(), request.getComment(), request.getVisitedAt());

        return diary.getId();
    }

    public void deleteDiary(Long memberId, Long id) {
        if(diaryRepository.findById(id).get().getMember().getIdx() == memberId){
            diaryRepository.deleteById(id);
        }
    }

    public GetDiaryResponse getDiary(LocalDate date) {
        Diary diary = diaryRepository.findByVisitedAt(date).get();
        return GetDiaryResponse.of(diary);
    }

    public List<String> getCalendar(Long memberId, int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate startOfMonth = yearMonth.atDay(1); // 해당 년-월의 시작일 2022-07-01
        LocalDate endOfMonth = yearMonth.atEndOfMonth(); // 해당 년-월의 마지막일 2022-07-31

        Member member = memberRepository.findById(memberId).get();
        List<Diary> result = diaryRepository.findAllByMemberAndVisitedAtBetweenOrderByVisitedAt(member, startOfMonth, endOfMonth);
        List<String> responses = new ArrayList<>();

        for (Diary diary : result){
            responses.add(diary.getVisitedAt().toString());
        }

        return responses;
    }
}
