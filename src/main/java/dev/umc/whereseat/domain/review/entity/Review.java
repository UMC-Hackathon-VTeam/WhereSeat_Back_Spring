package dev.umc.whereseat.domain.review.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import dev.umc.whereseat.domain.member.Member;
import dev.umc.whereseat.domain.stadium.Stadium;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String image;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Score score;

	@Column(nullable = false)
	private String comment;

	@Column(nullable = false)
	private String block;

	@Column(nullable = false)
	private String field;

	@Column(nullable = true)
	private Date visited_at;

	@Column(nullable = true)
	private Date created_at;

	@Column(nullable = true)
	private Date updated_at;

	@Column(nullable = true)
	private String status;

	//@OneToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "member")
	//private Member member;

	//@OneToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "stadium")
	//private Stadium stadium;

	@Builder
	public Review(Long id,
		String image,
		Score score,
		String comment,
		String block,
		String field,
		Date visited_at,
		Date created_at,
		Date updated_at,
		String status
		//Member member,
		//Stadium stadium
		) {
		this.id = id;
		this.image = image;
		this.score = score;
		this.comment = comment;
		this.block = block;
		this.field = field;
		this.visited_at = visited_at;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.status = status;
		//this.member = member;
		//this.stadium = stadium;
	}


}
