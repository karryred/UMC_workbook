package UMC.demo.study.service.MemberService;

import UMC.demo.study.domain.Member;
import UMC.demo.study.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    public Member joinMember(MemberRequestDTO.JoinDTO request);
    public Boolean validMemberExist (Long id);
}
