package UMC.demo.study.web.controller;

import UMC.demo.study.ApiPayload.ApiResponse;
import UMC.demo.study.converter.ReviewConverter;
import UMC.demo.study.domain.Review;
import UMC.demo.study.service.ReviewService.ReviewCommandService;
import UMC.demo.study.service.ReviewService.ReviewQueryService;
import UMC.demo.study.validation.annotation.CheckPage;
import UMC.demo.study.validation.annotation.ExistMember;
import UMC.demo.study.validation.annotation.ExistStore;
import UMC.demo.study.web.dto.ReviewRequestDTO;
import UMC.demo.study.web.dto.ReviewResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    private final ReviewQueryService reviewQueryService;

    @PostMapping("/{storeId}")
    public ApiResponse<ReviewResponseDTO.ReviewResultDTO> create(@ExistStore @PathVariable("storeId") Long storeId,
                                                             @RequestBody @Valid ReviewRequestDTO.ReviewDTO request,
                                                             @ExistMember @RequestParam(name = "memberId") Long memberId) {
        Review review = reviewCommandService.createReview(request, memberId, storeId);
        return ApiResponse.onSuccess(ReviewConverter.toReviewResultDTO(review));
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getStoreReviewList(@ExistStore @PathVariable("storeId") Long storeId,
                                                                                  @CheckPage @RequestParam(name = "page") Integer page) {

        Page<Review> reviewList = reviewQueryService.getStoreReviewList(storeId,page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(reviewList));
    }

    @GetMapping("/members/{memberId}")
    @Operation(summary = "자신의 리뷰 조회 API",description = "특정 멤버의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "유저의 아이디, path variable 입니다!, 추후에는 path variable이 아닌 헤더 토큰을 가져오는 것으로 수정해야합니다.")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getMemberReviewList(@ExistMember @PathVariable("memberId") Long memberId,
                                                                                   @CheckPage @RequestParam(name = "page") Integer page) {

        Page<Review> reviewList = reviewQueryService.getMemberReviewList(memberId,page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(reviewList));
    }
}
