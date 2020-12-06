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
@Table(name = "meetup_comment")
public class MeetupComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@CreationTimestamp
	@Column(name = "comment_date")
	private LocalDateTime commentDate;

	@Column(name = "comment_text")
	private String commentText;

	@ManyToOne
	@JoinColumn(name = "meetup_id")
	private Meetup meetUp;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "reply_to_comment_id")
	private MeetupComment replyToComment;

	@JsonIgnore
	@OneToMany(mappedBy = "replyToComment")
	private List<MeetupComment> replies;

	public MeetupComment getReplyToComment() {
		return replyToComment;
	}

	public void setReplyToComment(MeetupComment replyToComment) {
		this.replyToComment = replyToComment;
	}

	public List<MeetupComment> getReplies() {
		return replies;
	}

	public void setReplies(List<MeetupComment> replies) {
		this.replies = replies;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Meetup getMeetUp() {
		return meetUp;
	}

	public void setMeetUp(Meetup meetUp) {
		this.meetUp = meetUp;
	}

	private String title;

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
		MeetupComment other = (MeetupComment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeneralComment [id=");
		builder.append(id);
		builder.append(", commentDate=");
		builder.append(commentDate);
		builder.append(", commentText=");
		builder.append(commentText);
		builder.append(", title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}

	public MeetupComment() {
		super();
	}
}
