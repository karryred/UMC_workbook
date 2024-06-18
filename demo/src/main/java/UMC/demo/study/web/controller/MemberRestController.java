package UMC.demo.study.web.controller;

import UMC.demo.study.ApiPayload.ApiResponse;
import UMC.demo.study.converter.MemberConverter;
import UMC.demo.study.domain.Member;
import UMC.demo.study.service.MemberService.MemberCommandService;
import UMC.demo.study.web.dto.MemberRequestDTO;
import UMC.demo.study.web.dto.MemberResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
}
