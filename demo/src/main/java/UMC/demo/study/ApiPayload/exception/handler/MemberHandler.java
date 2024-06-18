package UMC.demo.study.ApiPayload.exception.handler;

import UMC.demo.study.ApiPayload.code.BaseErrorCode;
import UMC.demo.study.ApiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
