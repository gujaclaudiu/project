package edu.msg.ro.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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

@SuppressWarnings("serial")
@Entity
public class Notification implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_id", nullable = false, unique = true)
	private int notificationId;

	@Column(name = "bug_url")
	private String bugUrl;

	@Column(name = "message", length = 100, nullable = false)
	private String message;

	@Column(name = "type", length = 25, nullable = false)
	private String type;

	@Column(name = "timestamp", nullable = false)
	private LocalDate timestamp;

	@ManyToMany
	@JoinTable(name = "User_Notification", joinColumns = @JoinColumn(name = "notification_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;

	@ManyToOne
	@JoinColumn(name = "bug_id")
	private Bug bug;

	public Notification() {
		super();
		users = new ArrayList<User>();
	}

	public Notification(int notificationId, String bugUrl, String message, String type, LocalDate timestamp,
			List<User> users, Bug bug) {
		super();
		this.notificationId = notificationId;
		this.bugUrl = bugUrl;
		this.message = message;
		this.type = type;
		this.timestamp = timestamp;
		this.users = users;
		this.bug = bug;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public String getBugUrl() {
		return bugUrl;
	}

	public void setBugUrl(String bugUrl) {
		this.bugUrl = bugUrl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

}