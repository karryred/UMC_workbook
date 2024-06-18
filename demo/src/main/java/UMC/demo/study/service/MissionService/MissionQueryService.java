package UMC.demo.study.service.MissionService;

import UMC.demo.study.domain.Mission;
import UMC.demo.study.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MissionQueryService {
    Optional<Mission> findMission(Long id);

    Page<Mission> getStoreMissionList(Long storeId, Integer page);

    Page<MemberMission> getMemberMissionList(Long memberId, Integer page);
}
