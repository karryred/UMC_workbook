package UMC.demo.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MissionRequestDTO {

    @Getter
    public static class MissionDTO{

        @NotBlank
        private String name;

        @NotBlank
        private String missionSpec;

        @NotNull
        private Integer reward;  // 미션 보상

    }
}
