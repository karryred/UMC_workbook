package UMC.demo.study.converter;

import UMC.demo.study.domain.Mission;
import UMC.demo.study.domain.enums.MissionStatus;
import UMC.demo.study.domain.mapping.MemberMission;
import UMC.demo.study.web.dto.MissionRequestDTO;
import UMC.demo.study.web.dto.MissionResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDTO.MissionResultDTO toMissionResultDTO(Mission mission){
        return MissionResponseDTO.MissionResultDTO.builder()
                .missionID(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    //DTO를 통해 Entity를 생성하는 역할(Service에서 수행하지 않을 경우 이런 식으로)
    public static Mission toMission(MissionRequestDTO.MissionDTO request) {
        return Mission.builder()
                .name(request.getName())
                .missionSpec(request.getMissionSpec())
                .reward(request.getReward())
                .build();
    }

    public static MissionResponseDTO.MemberMissionPreViewDTO memberMissionPreViewDTO(MemberMission memberMission){
        return MissionResponseDTO.MemberMissionPreViewDTO.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .missionStatus(memberMission.getStatus())
                .build();
    }

    public static MissionResponseDTO.MemberMissionPreViewListDTO memberMissionPreViewListDTO(Page<MemberMission> missionList){
        List<MissionResponseDTO.MemberMissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .filter(memberMission -> MissionStatus.CHALLENGING.equals(memberMission.getStatus())) // 필터링 추가
                .map(MissionConverter::memberMissionPreViewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MemberMissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionList.getSize())
                .missionList(missionPreViewDTOList)
                .build();
    }

    public static MissionResponseDTO.StoreMissionPreViewDTO storeMissionPreViewDTO(Mission mission){
        return MissionResponseDTO.StoreMissionPreViewDTO.builder()
                .storeName(mission.getStore().getName())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .build();
    }

    public static MissionResponseDTO.StoreMissionPreViewListDTO storeMissionPreViewListDTO(Page<Mission> missionList){
        List<MissionResponseDTO.StoreMissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(MissionConverter::storeMissionPreViewDTO).collect(Collectors.toList());

        return MissionResponseDTO.StoreMissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionList.getSize())
                .missionList(missionPreViewDTOList)
                .build();
    }
}
