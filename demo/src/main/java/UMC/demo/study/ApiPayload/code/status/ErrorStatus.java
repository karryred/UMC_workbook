package UMC.demo.study.ApiPayload.code.status;

import UMC.demo.study.ApiPayload.code.BaseErrorCode;
import UMC.demo.study.ApiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    //미션 관련 에러
    SAME_CHALLENGING_MISSION(HttpStatus.BAD_REQUEST, "MISSION4001", "이미 해당 미션 도전중입니다"),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4002", "해당 미션이 존재하지 않습니다"),

    //멤버 관련 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "해당 사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수입니다."),

    //가게 관련 에러
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE4001", "해당 가게가 없습니다"),

    //아티클 관련 에러
    ARTICLE_NOT_FOUND(HttpStatus.BAD_REQUEST, "ARTICLE4001", "게시글이 없습니다"),

    //음식카테고리 관련 에러
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "FOOD_CATEGORY001", "해당 카테고리가 없습니다"),

    //페이지 관련 에러
    INVALID_PAGE_NUMBER(HttpStatus.BAD_REQUEST, "PAGE4001", "페이지는 1미만이면 안됩니다."),

    //For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),
    ;

    private HttpStatus httpStatus;

    private String code;

    private String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .code(code)
                .message(message)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .code(code)
                .message(message)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
