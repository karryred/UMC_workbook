package UMC.demo.study.web.controller;

import UMC.demo.study.ApiPayload.ApiResponse;
import UMC.demo.study.converter.MemberConverter;
import UMC.demo.study.converter.MemberMissionConverter;
import UMC.demo.study.converter.MissionConverter;
import UMC.demo.study.domain.Member;
import UMC.demo.study.domain.Mission;
import UMC.demo.study.domain.mapping.MemberMission;
import UMC.demo.study.service.MissionService.MissionCommandService;
import UMC.demo.study.service.MissionService.MissionQueryService;
import UMC.demo.study.validation.annotation.CheckPage;
import UMC.demo.study.validation.annotation.ExistMember;
import UMC.demo.study.validation.annotation.ExistMemberMission;
import UMC.demo.study.validation.annotation.ExistStore;
import UMC.demo.study.web.dto.MemberMissionResponseDTO;
import UMC.demo.study.web.dto.MemberResponseDTO;
import UMC.demo.study.web.dto.MissionRequestDTO;
import UMC.demo.study.web.dto.MissionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    private final MissionQueryService missionQueryService;

    @PostMapping("/owner/{storeId}")
    public ApiResponse<MissionResponseDTO.MissionResultDTO> createMission(@ExistStore @PathVariable("storeId") Long storeId,
                                                                 @RequestBody @Valid MissionRequestDTO.MissionDTO request){
        Mission mission = missionCommandService.createMission(request, storeId);
        return ApiResponse.onSuccess(MissionConverter.toMissionResultDTO(mission));
    }

    @PostMapping("/{memberId}")
    public ApiResponse<MemberResponseDTO.addMissionResultDTO> addMemberMission(@ExistMember @Valid @PathVariable("memberId") Long memberId,
                                                                @ExistStore @Valid @RequestParam("storeId") Long storeId) {
        Member member = missionCommandService.addMissionToMember(memberId, storeId);
        return ApiResponse.onSuccess(MemberConverter.toAddMissionResultDTO(member));
    }

    @PatchMapping("/{memberMissionId}")
    public ApiResponse<MemberMissionResponseDTO.UpdateResultDTO> updateMission(@ExistMemberMission @PathVariable("memberMissionId") Long memberMissionId) {
        @Valid MemberMission memberMission = missionCommandService.updateMission(memberMissionId);
        return ApiResponse.onSuccess(MemberMissionConverter.updateMemberMission(memberMission));
    }

    @PostMapping("/test/{missionId}")
    public ApiResponse<MemberResponseDTO.addMissionResultDTO> add2(@PathVariable("missionId") Long missionId,
                                                                  @ExistMember @RequestParam("memberId") Long memberId) {
        Member member = missionCommandService.addMissionToMember2(memberId, missionId);
        return ApiResponse.onSuccess(MemberConverter.toAddMissionResultDTO(member));
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회 API",description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDTO.StoreMissionPreViewListDTO> getStoreMissionList(@ExistStore @PathVariable("storeId") Long storeId,
                                                                                          @CheckPage @RequestParam(name = "page") Integer page) {

        Page<Mission> missionList = missionQueryService.getStoreMissionList(storeId,page);
        return ApiResponse.onSuccess(MissionConverter.storeMissionPreViewListDTO(missionList));
    }

    @GetMapping("/members/{memberId}")
    @Operation(summary = "자신의 미션 조회 API",description = "특정 멤버의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "유저의 아이디, path variable 입니다!, 추후에는 path variable이 아닌 헤더 토큰을 가져오는 것으로 수정해야합니다.")
    })
    public ApiResponse<MissionResponseDTO.MemberMissionPreViewListDTO> getMemberMissionList(@ExistMember @PathVariable("memberId") Long memberId,
                                                                                            @CheckPage  @RequestParam(name = "page") Integer page) {

        Page<MemberMission> missionList = missionQueryService.getMemberMissionList(memberId,page);
        return ApiResponse.onSuccess(MissionConverter.memberMissionPreViewListDTO(missionList));
    }
}

