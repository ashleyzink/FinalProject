package com.skilldistillery.doggiemeetup.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="dog_park_comment")
public class DogParkComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@CreationTimestamp
	@Column(name="comment_date")
	private LocalDateTime commentDate;
	
	@Column(name="comment_text")
	private String commentText;
	
	private String title;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="reply_to_comment_id")
	private DogParkComment replyToComment;
	
	@JsonIgnore
	@OneToMany(mappedBy="replyToComment")
	private List<DogParkComment> replies;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDateTime commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		DogParkComment other = (DogParkComment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DogParkComment [id=");
		builder.append(id);
		builder.append(", commentDate=");
		builder.append(commentDate);
		builder.append(", commentText=");
		builder.append(commentText);
		builder.append(", title=");
		builder.append(title);
		builder.append(", user=");
		builder.append(user);
		builder.append(", replyToComment=");
		builder.append(replyToComment);
		builder.append("]");
		return builder.toString();
	}

	public DogParkComment() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DogParkComment getReplyToComment() {
		return replyToComment;
	}

	public void setReplyToComment(DogParkComment replyToComment) {
		this.replyToComment = replyToComment;
	}

	public List<DogParkComment> getReplies() {
		return replies;
	}

	public void setReplies(List<DogParkComment> replies) {
		this.replies = replies;
	}
}
