package edu.msg.ro.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Attachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attachment_id", nullable = false, unique = true)
	private int attachmentId;

	@Column(name = "file_path", nullable = false)
	private String filePath;

	@ManyToOne
	@JoinColumn(name = "bug_id")
	private Bug bug;

	public Attachment() {
		super();
	}

	public Attachment(int attachmentId, String filePath, Bug bug) {
		super();
		this.attachmentId = attachmentId;
		this.filePath = filePath;
		this.bug = bug;
	}

	public int getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

}