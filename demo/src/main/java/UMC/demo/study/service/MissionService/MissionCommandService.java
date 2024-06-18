package UMC.demo.study.service.MissionService;

import UMC.demo.study.domain.Member;
import UMC.demo.study.domain.Mission;
import UMC.demo.study.domain.Review;
import UMC.demo.study.domain.mapping.MemberMission;
import UMC.demo.study.web.dto.MissionRequestDTO;
import org.springframework.transaction.annotation.Transactional;

public interface MissionCommandService {
    @Transactional
    Mission createMission(MissionRequestDTO.MissionDTO request, Long storeId);

    @Transactional
    MemberMission updateMission(Long storeId);

    Member addMissionToMember(Long memberId, Long storeId);

    Member addMissionToMember2(Long memberId, Long storeId);

    boolean validSameChallengingMission(MemberMission memberMission);

    boolean validExistMemberMission(Long memberMissionId);
}
