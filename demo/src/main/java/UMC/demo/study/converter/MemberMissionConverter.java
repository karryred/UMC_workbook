package UMC.demo.study.converter;

import UMC.demo.study.domain.Member;
import UMC.demo.study.domain.Mission;
import UMC.demo.study.domain.mapping.MemberMission;
import UMC.demo.study.web.dto.MemberMissionResponseDTO;

import java.time.LocalDateTime;

public class MemberMissionConverter {
    public static MemberMission toMemberMission(Member member, Mission mission) {
        MemberMission memberMission = MemberMission.builder()
                .mission(mission)
                .member(member)
                .build();

        return memberMission;
    }

    public static MemberMissionResponseDTO.UpdateResultDTO updateMemberMission(MemberMission memberMission) {
        return MemberMissionResponseDTO.UpdateResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();

    }
}
