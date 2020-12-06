package com.skilldistillery.doggiemeetup.entities;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String email;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String password;
	private String role;
	private Boolean enabled;
	
	@Column(name="date_option")
	private Boolean dateOption;
	
	@Column(name="profile_photo_url")
	private String profilePhotoUrl;
	private String bio;
	
	@CreationTimestamp
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	@Column(name="profile_private")
	private Boolean profilePrivate;
	
	@Column(name="location_private")
	private Boolean locationPrivate;
	
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	@OneToMany(mappedBy="user")
	private List <Route> routes;
	
	@OneToMany(mappedBy="user")
	private List <MeetupComment> meetupComments;
	
	@OneToMany(mappedBy="user")
	private List <Meetup> meetups;
	
	@OneToMany(mappedBy="user")
	private List<Dog> dogs;
	
	@OneToMany(mappedBy="user")
	private List <DogReview> dogReviews;
	
	@OneToMany(mappedBy="user")
	private List <DogParkReview> dogParkReviews;
	
	@OneToMany(mappedBy="user")
	private List <GeneralComment> generalComments;
	
	@ManyToMany
	@JoinTable(name="user_friend_request",
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="friend_id"))
	private List<User> friendList;
	
	//CONSTRUCTORS -------------------------------------------------------------------------------------
	 
	
	public User() {
		super();
	}
	
	
	//GETTERS/SETTERS-----------------------------------------------------------------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public Boolean getEnabled() {
		return enabled;
	}



	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}



	public Boolean getDateOption() {
		return dateOption;
	}



	public void setDateOption(Boolean dateOption) {
		this.dateOption = dateOption;
	}



	public String getProfilePhotoUrl() {
		return profilePhotoUrl;
	}



	public void setProfilePhotoUrl(String profilePhotoUrl) {
		this.profilePhotoUrl = profilePhotoUrl;
	}



	public String getBio() {
		return bio;
	}



	public void setBio(String bio) {
		this.bio = bio;
	}



	public LocalDateTime getCreateDate() {
		return createDate;
	}



	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}



	public Boolean getProfilePrivate() {
		return profilePrivate;
	}



	public void setProfilePrivate(Boolean profilePrivate) {
		this.profilePrivate = profilePrivate;
	}



	public Boolean getLocationPrivate() {
		return locationPrivate;
	}



	public void setLocationPrivate(Boolean locationPrivate) {
		this.locationPrivate = locationPrivate;
	}
	public Address getAddress() {
		return address;
	}
	
	
	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Route> getRoutes() {
		return routes;
	}
	
	
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	
	public List<MeetupComment> getMeetupComments() {
		return meetupComments;
	}
	
	
	public void setMeetupComments(List<MeetupComment> meetupComments) {
		this.meetupComments = meetupComments;
	}
	
	public List<Meetup> getMeetups() {
		return meetups;
	}


	public void setMeetups(List<Meetup> meetups) {
		this.meetups = meetups;
	}
	
	public List<Dog> getDogs() {
		return dogs;
	}
	
	
	public void setDogs(List<Dog> dogs) {
		this.dogs = dogs;
	}
	
	public List<DogReview> getDogReviews() {
		return dogReviews;
	}
	
	
	public void setDogReviews(List<DogReview> dogReviews) {
		this.dogReviews = dogReviews;
	}

	public List<DogParkReview> getDogParkReviews() {
		return dogParkReviews;
	}


	public void setDogParkReviews(List<DogParkReview> dogParkReviews) {
		this.dogParkReviews = dogParkReviews;
	}
	
	public List<GeneralComment> getGeneralComments() {
		return generalComments;
	}
	
	
	public void setGeneralComments(List<GeneralComment> generalComments) {
		this.generalComments = generalComments;
	}
	

	//HASHCODE/EQUALS (ID ONLY) ------------------------------------------------------------------------



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", email=");
		builder.append(email);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", role=");
		builder.append(role);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", dateOption=");
		builder.append(dateOption);
		builder.append(", profilePhotoUrl=");
		builder.append(profilePhotoUrl);
		builder.append(", bio=");
		builder.append(bio);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", profilePrivate=");
		builder.append(profilePrivate);
		builder.append(", locationPrivate=");
		builder.append(locationPrivate);
		builder.append("]");
		return builder.toString();
	}


	public List<User> getFriendList() {
		return friendList;
	}


	public void setFriendList(List<User> friendList) {
		this.friendList = friendList;
	}

}
