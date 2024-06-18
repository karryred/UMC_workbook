package UMC.demo.study.service.ReviewService;

import UMC.demo.study.domain.Review;
import UMC.demo.study.domain.Store;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ReviewQueryService {
    Optional<Store> findStore(Long id);

    Page<Review> getStoreReviewList(Long storeId, Integer page);

    Page<Review> getMemberReviewList(Long memberId, Integer page);
}
