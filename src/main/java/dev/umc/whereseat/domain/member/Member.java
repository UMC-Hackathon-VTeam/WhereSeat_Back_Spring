package dev.umc.whereseat.domain.member;

import dev.umc.whereseat.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor @Getter @Setter
@Table(name = "Member")
@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long idx;

    @Column(length = 20)
    private String nickname;

    @Column(length = 20)
    private String password;

    public Member(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
