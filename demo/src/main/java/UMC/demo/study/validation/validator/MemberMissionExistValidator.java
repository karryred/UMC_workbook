package UMC.demo.study.validation.validator;

import UMC.demo.study.ApiPayload.code.status.ErrorStatus;
import UMC.demo.study.service.MissionService.MissionCommandService;
import UMC.demo.study.validation.annotation.ExistMemberMission;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMissionExistValidator implements ConstraintValidator<ExistMemberMission, Long> {

    private final MissionCommandService missionCommandService;

    @Override
    public void initialize(ExistMemberMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = missionCommandService.validExistMemberMission(value);

        if (!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString())
                    .addConstraintViolation();
        }

        return isValid;
    }
}
