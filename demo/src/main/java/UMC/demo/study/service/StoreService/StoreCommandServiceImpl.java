package UMC.demo.study.service.StoreService;

import UMC.demo.study.domain.Store;
import UMC.demo.study.repository.StoreRepository;
import UMC.demo.study.web.dto.StoreRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;

    @Override
    public Store joinStore(StoreRequestDTO.JoinDTO request) {
        return null;
    }

    @Override
    public Boolean validStoreExist(Long id) {
        boolean isValid = storeRepository.existsById(id);

        return isValid;
    }
}
