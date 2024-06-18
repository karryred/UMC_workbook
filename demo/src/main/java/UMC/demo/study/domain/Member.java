package UMC.demo.study.domain;

import UMC.demo.study.domain.common.BaseEntity;
import UMC.demo.study.domain.enums.Gender;
import UMC.demo.study.domain.enums.MemberStatus;
import UMC.demo.study.domain.enums.SocialType;
import UMC.demo.study.domain.mapping.MemberAgree;
import UMC.demo.study.domain.mapping.MemberMission;
import UMC.demo.study.domain.mapping.MemberPrefer;
import UMC.demo.study.domain.mapping.Notice;
import UMC.demo.study.validation.annotation.CheckSameChallengingMission;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
/*
@DynamicUpdate
@DynamicInsert
JPA가 save로 DB에 저장할 때, null인 값에 대해서 default값을 적용하지 않고
그냥 null로 넣는 것을 막기위하여 쓰는 어노테이션
 */
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    private LocalDate birthday;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40)
    private String specAddress;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    private Boolean phoneCheck;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus status;

    private LocalDate inactiveDate;

    @Column(nullable = false, length = 20)
    private String email;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer point;

    private Integer successMissionCount;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Notice> noticeList = new ArrayList<>();

    @Validated
    public void addMemberMission(MemberMission memberMission) {
        memberMissionList.add(memberMission);
    }
}
