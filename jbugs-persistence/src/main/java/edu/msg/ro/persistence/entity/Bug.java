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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Bug implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bug_id", nullable = false, unique = true)
	private int bugId;

	@Column(name = "title", length = 50, nullable = false, unique = true)
	private String title;

	@Column(name = "description", length = 250, nullable = false)
	private String description;

	@Column(name = "version", length = 25, nullable = false)
	private String version;

	@Column(name = "version_fixed", length = 25, nullable = false)
	private String versionFixed;

	@Column(name = "target_date", nullable = false)
	private LocalDate date;

	@Column(name = "severity", length = 25, nullable = false)
	private String severity;

	@Column(name = "status", length = 25, nullable = false)
	private String status;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;

	@ManyToOne
	@JoinColumn(name = "assigned_to")
	private User assignedTo;

	@OneToMany(mappedBy = "bug")
	private List<Attachment> attachments;

	@OneToMany(mappedBy = "bug")
	private List<Notification> notifications;

	public Bug() {
		super();
		attachments = new ArrayList<Attachment>();
		notifications = new ArrayList<Notification>();
	}

	public Bug(int bugId, String title, String description, String version, String versionFixed, LocalDate date,
			String severity, String status, User createdBy, User assignedTo, List<Attachment> attachments,
			List<Notification> notifications) {
		super();
		this.bugId = bugId;
		this.title = title;
		this.description = description;
		this.version = version;
		this.versionFixed = versionFixed;
		this.date = date;
		this.severity = severity;
		this.status = status;
		this.createdBy = createdBy;
		this.assignedTo = assignedTo;
		this.attachments = attachments;
		this.notifications = notifications;
	}

	public int getBugId() {
		return bugId;
	}

	public void setBugId(int bugId) {
		this.bugId = bugId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersionFixed() {
		return versionFixed;
	}

	public void setVersionFixed(String versionFixed) {
		this.versionFixed = versionFixed;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

}