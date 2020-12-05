package com.skilldistillery.doggiemeetup.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dog_review")
@Entity
public class DogReview {

	@EmbeddedId
	private DogReview id;

	private int rating;

	@Column(name = "img_url")
	private String imgUrl;

	@Column(name = "review_date")
	private LocalDateTime reviewDate;

	private String review;

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public LocalDateTime getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDateTime reviewDate) {
		this.reviewDate = reviewDate;
	}

	@Override
	public String toString() {
		return "DogReview [rating=" + rating + ", imgUrl=" + imgUrl + ", reviewDate=" + reviewDate + ", review="
				+ review + "]";
	}

}
