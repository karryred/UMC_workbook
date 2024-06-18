package UMC.demo.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class JoinDTO{

        @NotBlank
        private String name;

        @Size(min=5, max=12)
        private String location;
    }
}
