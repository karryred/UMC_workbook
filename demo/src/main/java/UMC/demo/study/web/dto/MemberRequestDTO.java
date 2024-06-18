package UMC.demo.study.web.dto;

import UMC.demo.study.domain.enums.SocialType;
import UMC.demo.study.validation.annotation.ExistCategories;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

/**
 * 클라이언트가 실제로 입력하거나 수정할 필요가 없는 필드는 DTO에서 제외될 수 있습니다.
 * InactiveDate와 같은 필드는 사용자의 활동 상태와 관련된 정보로, 서버에서 특정 이벤트가 발생했을 때(예: 계정 비활성화) 자동으로 설정할 가능성이 큽니다.
 * 클라이언트가 이를 지정하는 것은 적절하지 않을 수 있습니다.
 */
public class MemberRequestDTO {

    @Getter
    public static class JoinDTO{

        @NotBlank
        private String name;

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate birthday;

        @Size(min=5, max=12)
        private String address;

        @Size(min=5, max=12)
        private String specAddress;

        @NotNull
        private String phoneNumber;

        private Boolean phoneCheck;

        //MemberConverter에서 정수값으로 case문 쓰기 위해 Integer값으로 선언
        @NotNull
        private Integer gender;

        private SocialType socialType;

        private String email;

        @ExistCategories
        private List<Long> preferCategory;
    }
}
