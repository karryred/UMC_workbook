package UMC.demo.study.converter;

import UMC.demo.study.domain.Review;
import UMC.demo.study.web.dto.ReviewRequestDTO;
import UMC.demo.study.web.dto.ReviewResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDTO.ReviewResultDTO toReviewResultDTO(Review review){
        return ReviewResponseDTO.ReviewResultDTO.builder()
                .reviewID(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    //DTO를 통해 Entity를 생성하는 역할(Service에서 수행하지 않을 경우 이런 식으로)
    public static Review toReview(ReviewRequestDTO.ReviewDTO request) {
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .body(review.getBody())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreViewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewList.getSize())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
