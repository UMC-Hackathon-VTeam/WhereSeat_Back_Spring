package dev.umc.whereseat.common;


import com.sun.istack.NotNull;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private BaseStatus status = BaseStatus.ACTIVE;


    /**
     * 수정 시간 업데이트
     */
    public void updateUpdatedAt(@NotNull LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Status 변경
     * @param status
     */
    public void changeStatus(BaseStatus status) {
        if (status.equals(BaseStatus.ACTIVE))
            this.status = BaseStatus.INACTIVE;
        else
            this.status=BaseStatus.ACTIVE;
    }

}