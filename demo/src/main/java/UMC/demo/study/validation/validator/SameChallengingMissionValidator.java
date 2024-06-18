package UMC.demo.study.validation.validator;

import UMC.demo.study.ApiPayload.code.status.ErrorStatus;
import UMC.demo.study.domain.mapping.MemberMission;
import UMC.demo.study.service.MissionService.MissionCommandService;
import UMC.demo.study.validation.annotation.CheckSameChallengingMission;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Component
@RequiredArgsConstructor
@Validated
public class SameChallengingMissionValidator implements ConstraintValidator<CheckSameChallengingMission, MemberMission> {

    private final MissionCommandService missionCommandService;

    @Override
    public void initialize(CheckSameChallengingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberMission value, ConstraintValidatorContext context) {
        boolean isValid = missionCommandService.validSameChallengingMission(value);

        System.out.println("작동");
        if (!isValid) { // isValid가 false면 에러 발생
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.SAME_CHALLENGING_MISSION.toString())
                    .addConstraintViolation();
        }

        return isValid;
    }
}
