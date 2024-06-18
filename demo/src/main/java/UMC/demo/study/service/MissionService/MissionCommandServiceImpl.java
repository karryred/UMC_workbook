package UMC.demo.study.service.MissionService;

import UMC.demo.study.converter.MemberMissionConverter;
import UMC.demo.study.converter.MissionConverter;
import UMC.demo.study.domain.Member;
import UMC.demo.study.domain.Mission;
import UMC.demo.study.domain.Store;
import UMC.demo.study.domain.enums.MissionStatus;
import UMC.demo.study.domain.mapping.MemberMission;
import UMC.demo.study.repository.MemberMissionRepository;
import UMC.demo.study.repository.MemberRepository;
import UMC.demo.study.repository.MissionRepository;
import UMC.demo.study.repository.StoreRepository;
import UMC.demo.study.validation.annotation.CheckSameChallengingMission;
import UMC.demo.study.web.dto.MissionRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;

    private final MemberRepository memberRepository;

    private final MissionRepository missionRepository;

    private final MemberMissionRepository memberMissionRepository;


    @Override
    @Transactional
    public Mission createMission(MissionRequestDTO.MissionDTO request, Long storeId) {
        Mission newMission = MissionConverter.toMission(request);
        newMission.setStore(storeRepository.findById(storeId).get());
        newMission.setMissionStatus(MissionStatus.INACTIVE);

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid store ID"));
        store.addMission(newMission); // Store 엔티티에 미션 추가 및 설정

        return missionRepository.save(newMission);
    }

    @Override
    @Transactional
    public MemberMission updateMission(Long memberMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId).get();
        memberMission.setStatus(MissionStatus.COMPLETE);
        return memberMissionRepository.save(memberMission);
    }

    @Override
    public Member addMissionToMember(Long memberId, Long storeId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid store ID"));

        // 해당 스토어의 모든 미션을 가져옵니다.
        List<Mission> missions = missionRepository.findByStoreId(storeId);

        // 각 미션에 대해 MemberMission 객체를 생성하고 Member에 추가합니다.
        for (Mission mission : missions) {
            MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);
            memberMission.setStatus(MissionStatus.CHALLENGING);
            member.addMemberMission(memberMission); // Member 엔티티에 미션 추가 및 설정
        }

        return memberRepository.save(member); // 업데이트된 멤버 반환
    }

    @Override
    public Member addMissionToMember2(Long memberId, Long missionId){
        Member member = memberRepository.findById(memberId).get();

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid mission ID"));

        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);

        memberMission.setStatus(MissionStatus.CHALLENGING);

        member.addMemberMission(memberMission);

        return memberRepository.save(member); // 업데이트된 멤버 반환
    }

    @Override
    public boolean validSameChallengingMission(MemberMission memberMission) {
        System.out.println("memberMission = " + memberMission);
        Member member = memberMission.getMember();
        Long missionIdToCheck = memberMission.getMission().getId();

        // member 객체의 memberMissions 리스트에서 동일한 미션 ID를 가지고 있는지 확인
        boolean hasSameMission = member.getMemberMissionList().stream()
                .anyMatch(existingMemberMission ->
                        existingMemberMission.getMission().getId().equals(missionIdToCheck) &&
                                existingMemberMission.getStatus().equals(MissionStatus.CHALLENGING)
                );

        return !hasSameMission;
    }

    @Override
    public boolean validExistMemberMission(Long memberMissionId) {
        boolean isValid = memberMissionRepository.existsById(memberMissionId);

        return isValid;
    }
}
