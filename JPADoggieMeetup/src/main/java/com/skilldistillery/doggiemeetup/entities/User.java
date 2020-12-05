package com.skilldistillery.doggiemeetup.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@Column(name="private_profile")
	private Boolean privateProfile;
	
	@Column(name="location_private")
	private Boolean locationPrivate;
	
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



	public Boolean getPrivateProfile() {
		return privateProfile;
	}



	public void setPrivateProfile(Boolean privateProfile) {
		this.privateProfile = privateProfile;
	}



	public Boolean getLocationPrivate() {
		return locationPrivate;
	}



	public void setLocationPrivate(Boolean locationPrivate) {
		this.locationPrivate = locationPrivate;
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
		builder.append(", privateProfile=");
		builder.append(privateProfile);
		builder.append(", locationPrivate=");
		builder.append(locationPrivate);
		builder.append("]");
		return builder.toString();
	}

}
