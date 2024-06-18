package UMC.demo.study.ApiPayload.exception.handler;

import UMC.demo.study.ApiPayload.code.BaseErrorCode;
import UMC.demo.study.ApiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {

    public StoreHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
