package UMC.demo.study.domain.common;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@MappedSuperclass //JPA Entity클래스가 해당 추상 클래스를 상속할 경우 자식클래스에게 매핑 정보 전달(부모 필드를 컬럼으로 인식)
@EntityListeners(AuditingEntityListener.class) //엔티티를 DB에 적용하기 이전, 이후에 콜백 리스너를 요청
@Getter
public abstract class BaseEntity {

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;
}