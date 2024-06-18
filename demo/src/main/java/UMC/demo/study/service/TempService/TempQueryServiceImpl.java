package UMC.demo.study.service.TempService;


import UMC.demo.study.ApiPayload.code.status.ErrorStatus;
import UMC.demo.study.ApiPayload.exception.handler.TempHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService {
    @Override
    public void CheckFlag(Integer flag) {
        if (flag == 1) {
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
        }
        //요청 처리를 위한 repository 호출 메서드
    }
}
