package UMC.demo.study.converter;

import UMC.demo.study.domain.FoodCategory;
import UMC.demo.study.domain.mapping.MemberPrefer;
import java.util.List;
import java.util.stream.Collectors;

public class MemberPreferConverter {

    //Member와 MemberPrefer가 1:N이니까 리스트 형태로 받아와야지
    //MemberPrefer row를 만든다고 생각하면 될 듯(여러 개)
    public static List<MemberPrefer> toMemberPreferList(List<FoodCategory> foodCategoryList){
        return foodCategoryList.stream()
                .map(foodCategory ->
                        MemberPrefer.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
