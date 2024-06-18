package UMC.demo.study.service.MissionService;

import UMC.demo.study.domain.Member;
import UMC.demo.study.domain.Mission;
import UMC.demo.study.domain.Review;
import UMC.demo.study.domain.Store;
import UMC.demo.study.domain.mapping.MemberMission;
import UMC.demo.study.repository.MemberMissionRepository;
import UMC.demo.study.repository.MemberRepository;
import UMC.demo.study.repository.MissionRepository;
import UMC.demo.study.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MissionQueryServiceImpl implements MissionQueryService{

    private final MemberRepository memberRepository;

    private final StoreRepository storeRepository;

    private final MissionRepository missionRepository;

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Optional<Mission> findMission(Long id) {
        return missionRepository.findById(id);
    }

    @Override
    public Page<Mission> getStoreMissionList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();

        Page<Mission> storePage = missionRepository.findAllByStore(store, PageRequest.of(page,10));

        return storePage;
    }

    @Override
    public Page<MemberMission> getMemberMissionList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();

        Page<MemberMission> memberPage = memberMissionRepository.findAllByMember(member, PageRequest.of(page,10));

        return memberPage;
    }
}
