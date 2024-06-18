package UMC.demo.study.ApiPayload.exception.handler;

import UMC.demo.study.ApiPayload.code.BaseErrorCode;
import UMC.demo.study.ApiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
