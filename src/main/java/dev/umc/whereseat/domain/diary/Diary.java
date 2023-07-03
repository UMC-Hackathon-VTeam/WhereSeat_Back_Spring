package dev.umc.whereseat.domain.diary;

import dev.umc.whereseat.common.BaseEntity;
import dev.umc.whereseat.domain.diary.dto.CreateDiaryRequest;
import dev.umc.whereseat.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Diary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false, columnDefinition="TEXT")
    private String image;

    @Column(nullable = false, columnDefinition="TEXT")
    private String comment;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate visitedAt;

    @Builder(access = AccessLevel.PRIVATE)
    private Diary(Member member, String image, String comment, LocalDate visitedAt){
        this.member = member;
        this.image = image;
        this.comment = comment;
        this.visitedAt = visitedAt;
    }
    public static Diary newDiary(Member member, String image, CreateDiaryRequest request){
        return Diary.builder()
                .member(member)
                .image(image)
                .comment(request.getComment())
                .visitedAt(request.getVisitedAt())
                .build();
    }

    public void updateImage(String image){
        this.image = image;
    }

    public void updateComment(String comment){
        this.comment = comment;
    }

    public void updateVisitedAt(LocalDate visitedAt){
        this.visitedAt = visitedAt;
    }


}
