package com.skilldistillery.doggiemeetup.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Dog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String breed;
	
	private String temperament;
	
	@Column(name="dog_profile_pic_url")
	private String profilePicUrl;
	
	@Column(name="activity_level")
	private String activityLevel;
	
	private String size;
	
	private String bio;
	
	private LocalDate birthday;
	
	@Column(name="rainbow_bridge")
	private LocalDate rainbowBridge;
	@CreationTimestamp
	@Column(name="create_date")
	private LocalDate createDate;
	
	private String gender;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy="dog")
	private List<DogReview> dogReviews;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="dog_has_meetup",
	joinColumns=@JoinColumn(name="dog_id"),
	inverseJoinColumns=@JoinColumn(name="meetup_id"))
	private List<Meetup> meetups;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getTemperament() {
		return temperament;
	}

	public void setTemperament(String temperament) {
		this.temperament = temperament;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	public String getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public LocalDate getRainbowBridge() {
		return rainbowBridge;
	}

	public void setRainbowBridge(LocalDate rainbowBridge) {
		this.rainbowBridge = rainbowBridge;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Dog other = (Dog) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Dog() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dog [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", breed=");
		builder.append(breed);
		builder.append(", temperament=");
		builder.append(temperament);
		builder.append(", profilePicUrl=");
		builder.append(profilePicUrl);
		builder.append(", activityLevel=");
		builder.append(activityLevel);
		builder.append(", size=");
		builder.append(size);
		builder.append(", bio=");
		builder.append(bio);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", rainbowBridge=");
		builder.append(rainbowBridge);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<DogReview> getDogReviews() {
		return dogReviews;
	}

	public void setDogReviews(List<DogReview> dogReviews) {
		this.dogReviews = dogReviews;
	}

	public List<Meetup> getMeetups() {
		return meetups;
	}

	public void setMeetups(List<Meetup> meetups) {
		this.meetups = meetups;
	}
	

}
