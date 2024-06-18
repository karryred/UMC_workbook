package UMC.demo.study.converter;

import UMC.demo.study.domain.Member;
import UMC.demo.study.domain.enums.Gender;
import UMC.demo.study.web.dto.MemberRequestDTO;
import UMC.demo.study.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    //Member 엔티티를 MemberResponseDTO.JoinResultDTO로 변환하는 작업을 수행
    //근데 얘는 왜 static으로 선언된걸까?
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberResponseDTO.addMissionResultDTO toAddMissionResultDTO(Member member){
        List<Long> missionIds = member.getMemberMissionList().stream()
                .map(memberMission -> memberMission.getMission().getId())
                .collect(Collectors.toList());

        return MemberResponseDTO.addMissionResultDTO.builder()
                .memberId(member.getId())
                .missionIds(missionIds)
                .createdAt(LocalDateTime.now())
                .build();
    }

    //DTO를 통해 Entity를 생성하는 역할(Service에서 수행하지 않을 경우 이런 식으로)
    public static Member toMember(MemberRequestDTO.JoinDTO request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .phoneNumber(request.getPhoneNumber())
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }
}
