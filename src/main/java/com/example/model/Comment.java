package com.example.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Comment {
	
	@NotNull
	private Post commentedPost;
	@NotBlank
	private String content;
	@NotNull
	private User commenter;

	public Comment(Post commentedPost, String content, User commenter) {
		super();
		this.setCommentedPost(commentedPost);
		this.setContent(content);
		this.setCommenter(commenter);
	}

	public Comment() {
	}

	public Post getCommentedPost() {
		return commentedPost;
	}

	public void setCommentedPost(Post commentedPost) {
		if (isNotNull(commentedPost)) {
			this.commentedPost = commentedPost;
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (isNotNull(content)) {
			this.content = content;
		}
	}

	public User getCommenter() {
		return commenter;
	}

	public void setCommenter(User commenter) {
		if (isNotNull(commenter)) {
			this.commenter = commenter;
		}
	}

	public boolean isNotNull(Object o) {
		if (o.equals(null)) {
			return false;
		} else {
			return true;
		}
	}

}
