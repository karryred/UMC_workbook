package UMC.demo.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * 클라이언트가 실제로 입력하거나 수정할 필요가 없는 필드는 DTO에서 제외될 수 있습니다.
 * InactiveDate와 같은 필드는 사용자의 활동 상태와 관련된 정보로, 서버에서 특정 이벤트가 발생했을 때(예: 계정 비활성화) 자동으로 설정할 가능성이 큽니다.
 * 클라이언트가 이를 지정하는 것은 적절하지 않을 수 있습니다.
 */
public class ReviewRequestDTO {

    @Getter
    public static class ReviewDTO{

        private String body;

        @NotNull
        private Float score;
    }

}
