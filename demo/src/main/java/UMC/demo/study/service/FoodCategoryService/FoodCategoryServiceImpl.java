package UMC.demo.study.service.FoodCategoryService;

import UMC.demo.study.repository.FoodCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    public Boolean validCategoriesExist (List<Long> value){
        boolean isValid = value.stream()
                .allMatch(id -> foodCategoryRepository.existsById(id));

        return isValid;
    }
}
