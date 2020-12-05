package com.skilldistillery.doggiemeetup.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="dog_park_review")
public class DogParkReview {
	
	@EmbeddedId
	private DogParkReviewId id;
	
	private Integer rating;
	
	private String review;
	
	@Column(name="img_url")
	private String imgUrl;
	
	@Column(name="review_date")
	@CreationTimestamp
	private LocalDateTime reviewDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id") // DB column name
	@MapsId(value = "userId")     // Field in ID class
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "dog_park_id") // DB column name
	@MapsId(value = "dogParkId")     // Field in ID class
	private DogPark dogPark;

	public DogParkReviewId getId() {
		return id;
	}

	public void setId(DogParkReviewId id) {
		this.id = id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DogParkReview other = (DogParkReview) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DogParkReview [id=");
		builder.append(id);
		builder.append(", rating=");
		builder.append(rating);
		builder.append(", review=");
		builder.append(review);
		builder.append(", imgUrl=");
		builder.append(imgUrl);
		builder.append(", reviewDate=");
		builder.append(reviewDate);
		builder.append("]");
		return builder.toString();
	}

	public DogParkReview() {
		super();
	}

}
