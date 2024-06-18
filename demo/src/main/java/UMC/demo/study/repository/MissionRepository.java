package UMC.demo.study.repository;

import UMC.demo.study.domain.Mission;
import UMC.demo.study.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findByStoreId(Long storeId);
    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);
}
