package UMC.demo.study.validation.annotation;

import UMC.demo.study.validation.validator.SameChallengingMissionValidator;
import jakarta.validation.Payload;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Constraint(validatedBy = SameChallengingMissionValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckSameChallengingMission {

    String message() default "이미 같은 도전중인 미션이 있습니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
