package UMC.demo.study.service.ReviewService;

import UMC.demo.study.domain.Member;
import UMC.demo.study.domain.Review;
import UMC.demo.study.domain.Store;
import UMC.demo.study.repository.MemberRepository;
import UMC.demo.study.repository.ReviewRepository;
import UMC.demo.study.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewQueryServiceImpl implements ReviewQueryService{

    private final ReviewRepository reviewRepository;

    private final StoreRepository storeRepository;

    private final MemberRepository memberRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public Page<Review> getStoreReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();

        Page<Review> storePage = reviewRepository.findAllByStore(store, PageRequest.of(page,10));

        return storePage;
    }

    @Override
    public Page<Review> getMemberReviewList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();

        Page<Review> memberPage = reviewRepository.findAllByMember(member, PageRequest.of(page,10));

        return memberPage;
    }


}
