package UMC.demo.study.ApiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ReasonDTO {
    private Boolean isSuccess;
    private String code;
    private String message;
    private HttpStatus httpStatus;
}
