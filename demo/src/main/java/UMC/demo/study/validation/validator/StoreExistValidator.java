package UMC.demo.study.validation.validator;

import UMC.demo.study.ApiPayload.code.status.ErrorStatus;
import UMC.demo.study.service.StoreService.StoreCommandService;
import UMC.demo.study.validation.annotation.ExistStore;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreCommandService storeCommandService;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = storeCommandService.validStoreExist(value);

        if (!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString())
                    .addConstraintViolation();
        }

        return isValid;
    }
}
