package UMC.demo.study.validation.validator;

import UMC.demo.study.ApiPayload.code.status.ErrorStatus;
import UMC.demo.study.service.MemberService.MemberCommandService;
import UMC.demo.study.validation.annotation.ExistMember;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberExistValidator implements ConstraintValidator<ExistMember, Long> {

    private final MemberCommandService memberCommandService;

    @Override
    public void initialize(ExistMember constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = memberCommandService.validMemberExist(value);

        if (!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_MISSION_NOT_FOUND.toString())
                    .addConstraintViolation();
        }

        return isValid;
    }
}
