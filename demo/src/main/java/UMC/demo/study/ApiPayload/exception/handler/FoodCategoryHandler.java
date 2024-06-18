package UMC.demo.study.ApiPayload.exception.handler;

import UMC.demo.study.ApiPayload.code.BaseErrorCode;
import UMC.demo.study.ApiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {

    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
