package UMC.demo.study.ApiPayload.exception.handler;

import UMC.demo.study.ApiPayload.code.BaseErrorCode;
import UMC.demo.study.ApiPayload.exception.GeneralException;

public class PageHandler extends GeneralException {
    public PageHandler(BaseErrorCode code) {
        super(code);
    }
}
