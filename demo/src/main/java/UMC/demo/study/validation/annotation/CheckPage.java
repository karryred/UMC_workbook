package UMC.demo.study.validation.annotation;

import UMC.demo.study.validation.validator.PageNumberValidator;
import jakarta.validation.Payload;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {
    String message() default "유효하지 않은 페이지 숫자 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
