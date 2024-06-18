package UMC.demo.study.service.StoreService;

import UMC.demo.study.domain.Store;
import UMC.demo.study.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    public Store joinStore(StoreRequestDTO.JoinDTO request);
    public Boolean validStoreExist (Long id);
}
