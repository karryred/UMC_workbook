package UMC.demo.study.service.ReviewService;

import UMC.demo.study.domain.Review;
import UMC.demo.study.web.dto.ReviewRequestDTO;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewCommandService {
    Review createReview(ReviewRequestDTO.ReviewDTO request, Long memberId, Long storeId);
}
