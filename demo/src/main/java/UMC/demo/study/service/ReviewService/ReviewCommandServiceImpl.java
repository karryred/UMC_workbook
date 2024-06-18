package UMC.demo.study.service.ReviewService;

import UMC.demo.study.converter.ReviewConverter;
import UMC.demo.study.domain.Review;
import UMC.demo.study.domain.Store;
import UMC.demo.study.repository.MemberRepository;
import UMC.demo.study.repository.ReviewRepository;
import UMC.demo.study.repository.StoreRepository;
import UMC.demo.study.web.dto.ReviewRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;

    private final StoreRepository storeRepository;

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Review createReview(ReviewRequestDTO.ReviewDTO request, Long memberId, Long storeId) {
        Review newReview = ReviewConverter.toReview(request);
        newReview.setMember(memberRepository.findById(memberId).get());
        newReview.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(newReview);
    }
}
