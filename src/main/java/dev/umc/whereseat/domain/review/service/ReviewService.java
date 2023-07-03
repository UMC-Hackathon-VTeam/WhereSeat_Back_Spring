package dev.umc.whereseat.domain.review.service;

import static dev.umc.whereseat.common.ErrorStatus.*;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.cloudformation.model.transform.StackDriftInformationStaxUnmarshaller;

import dev.umc.whereseat.domain.member.Member;
import dev.umc.whereseat.domain.member.MemberRepository;
import dev.umc.whereseat.domain.review.dto.Request.ReviewCreateInDTO;
import dev.umc.whereseat.domain.review.dto.Request.ReviewUpdateInDTO;
import dev.umc.whereseat.domain.review.dto.Response.ReviewCreateOutDTO;
import dev.umc.whereseat.domain.review.dto.Response.ReviewDetailListOutDTO;
import dev.umc.whereseat.domain.review.dto.Response.ReviewUpdateOutDTO;
import dev.umc.whereseat.domain.review.entity.Review;
import dev.umc.whereseat.domain.review.exception.ReviewException;
import dev.umc.whereseat.domain.review.repository.ReviewRepository;
import dev.umc.whereseat.domain.seat.Seat;
import dev.umc.whereseat.domain.seat.SeatRepository;
import dev.umc.whereseat.domain.stadium.entity.Stadium;
import dev.umc.whereseat.domain.stadium.repository.StadiumRepository;
import dev.umc.whereseat.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final StadiumRepository stadiumRepository;
	private final MemberRepository memberRepository;
	private final FileUploadUtil fileUploadUtil;
	private final SeatRepository seatRepository;

	/**
	 * 리뷰 생성
	 */
	public ReviewCreateOutDTO createReview(Long memberId, MultipartFile image, ReviewCreateInDTO dto) throws IOException{

		Member member = memberRepository.findById(memberId).get();
		Stadium stadium = Stadium.newStadium(dto.getName());
		stadium = stadiumRepository.save(stadium);
		Seat seat = Seat.newSeat(stadium, dto.getSeat());
		seat = seatRepository.save(seat);
		String imgUrl = fileUploadUtil.uploadFile("diary", image);

		// 리뷰 엔티티 생성
		Review review = Review.create(member, imgUrl, dto, stadium);
		reviewRepository.save(review);

		return ReviewCreateOutDTO.of(review);
	}

	/**
	 * 리뷰 업데이트
	 */
	public ReviewUpdateOutDTO updateReview(Long reviewId, ReviewUpdateInDTO reviewUpdateInDTO, MultipartFile image) throws
		IOException {
		Review review = reviewRepository.findById(reviewId);
		String imgUrl = fileUploadUtil.uploadFile("diary", image);
		Stadium stadium = Stadium.newStadium(reviewUpdateInDTO.getName());
		stadiumRepository.save(stadium);
		Review updatedReview = review.update(reviewUpdateInDTO, imgUrl, stadium);
		reviewRepository.save(updatedReview);
		return ReviewUpdateOutDTO.of(updatedReview);
	}

	/**
	 * 리뷰 삭제
	 */
	public String deleteReview(Long memberId, Long reviewId){
		if(reviewRepository.findById(reviewId).getMember().getIdx() == memberId){
			reviewRepository.deleteById(reviewId);
		}
		return "리뷰 삭제 완료";
	}

	/**
	 * 리뷰 구장별 조회
	 */
	@Transactional(readOnly = true)
	public List<ReviewDetailListOutDTO> getStadiumReview(String name){
		List<Stadium> stadiums = stadiumRepository.findAllByName(name);
		List<Review> findReviews = reviewRepository.findAllByStadiumIn(stadiums);
		return ReviewDetailListOutDTO.of(findReviews);
	}


	/**
	 * 리뷰 좌석별 조회
	 */
	@Transactional(readOnly = true)
	public List<ReviewDetailListOutDTO> getSeatReview(String details){
		List<Review> findReviews = reviewRepository.findAllByDetails(details).orElse(null);
		return ReviewDetailListOutDTO.of(findReviews);
	}



	/**
	 * 리뷰 상세 조회
	 */
	@Transactional(readOnly = true)
	public List<ReviewDetailListOutDTO> getReview(Long reviewId){
		List<Review> findReviews = reviewRepository.findAllById(reviewId).orElse(null);
		return ReviewDetailListOutDTO.of(findReviews);
	}
}
