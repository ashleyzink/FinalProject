package com.skilldistillery.doggiemeetup.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DogParkReviewId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="dog_park_id")
	private int dogParkId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDogParkId() {
		return dogParkId;
	}

	public void setDogParkId(int dogParkId) {
		this.dogParkId = dogParkId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dogParkId;
		result = prime * result + userId;
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
		DogParkReviewId other = (DogParkReviewId) obj;
		if (dogParkId != other.dogParkId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DogParkReviewId [userId=");
		builder.append(userId);
		builder.append(", dogParkId=");
		builder.append(dogParkId);
		builder.append("]");
		return builder.toString();
	}

	public DogParkReviewId() {
		super();
	}

	public DogParkReviewId(int userId, int dogParkId) {
		super();
		this.userId = userId;
		this.dogParkId = dogParkId;
	}

}
