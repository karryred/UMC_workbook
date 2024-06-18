package UMC.demo.study.domain.mapping;

import UMC.demo.study.domain.Member;
import UMC.demo.study.domain.ReceiveNotice;
import UMC.demo.study.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isConfirmed;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(length = 20)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "receive_id")
    private ReceiveNotice receiveNotice;
}
