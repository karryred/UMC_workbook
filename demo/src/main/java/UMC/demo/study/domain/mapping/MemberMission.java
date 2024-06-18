package UMC.demo.study.domain.mapping;

import UMC.demo.study.domain.Member;
import UMC.demo.study.domain.Mission;
import UMC.demo.study.domain.common.BaseEntity;
import UMC.demo.study.domain.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    public void setStatus(MissionStatus status) {
        this.status = status;
    }
}
