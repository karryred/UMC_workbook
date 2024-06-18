package UMC.demo.study.service.MemberService;

import UMC.demo.study.ApiPayload.code.status.ErrorStatus;
import UMC.demo.study.ApiPayload.exception.handler.FoodCategoryHandler;
import UMC.demo.study.converter.MemberConverter;
import UMC.demo.study.converter.MemberPreferConverter;
import UMC.demo.study.domain.FoodCategory;
import UMC.demo.study.domain.Member;
import UMC.demo.study.domain.mapping.MemberPrefer;
import UMC.demo.study.repository.FoodCategoryRepository;
import UMC.demo.study.repository.MemberRepository;
import UMC.demo.study.web.dto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDTO request) {
        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category->{
                    return foodCategoryRepository.findById(category).get();
                }).collect(Collectors.toList());

        //foodCategory 엔티티를 가져와서 MemberPrefer 엔티티를 만들어줄거야
        //여기서 MemberPrefer의 foodCategory 필드를 초기화 시켜서 객체를 돌려줌
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        //각 MemberPrefer 객체(foodCategory만 다른)에 멤버값을 동일하게 전부 넣어줌
        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    @Override
    public Boolean validMemberExist (Long id){
        boolean isValid = memberRepository.existsById(id);

        return isValid;
    }
}
