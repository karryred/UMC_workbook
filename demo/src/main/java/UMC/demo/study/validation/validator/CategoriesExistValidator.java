package UMC.demo.study.validation.validator;

import UMC.demo.study.ApiPayload.code.status.ErrorStatus;
import UMC.demo.study.service.FoodCategoryService.FoodCategoryService;
import UMC.demo.study.validation.annotation.ExistCategories;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

    private final FoodCategoryService foodCategoryService;

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     *
     * @param value object to validate DTO에서 Long 타입으로 받기 때문에 @ExistCategories 에서 에러가 발생하면 일로 넘어와 value를 넣어줌
     * @param context context in which the constraint is evaluated
     *
     * @return
     */
    @Override
    public boolean isValid(List<Long> value, ConstraintValidatorContext context) {

        boolean isValid=foodCategoryService.validCategoriesExist(value);

        if (!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString())
                    .addConstraintViolation();
        }

        return isValid;
    }
}
