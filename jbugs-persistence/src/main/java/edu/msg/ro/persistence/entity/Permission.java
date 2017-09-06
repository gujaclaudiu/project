package edu.msg.ro.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
public class Permission implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "permission_id", nullable = false, unique = true)
	private int permissionId;

	@Column(name = "permission_name", nullable = false, unique = true)
	private String permissionName;

	@Column(name = "details", nullable = false, unique = true)
	private String details;

	@ManyToMany(mappedBy = "permissions")
	private List<Role> roles;

	public Permission() {
		super();
		roles = new ArrayList<Role>();
	}

	public Permission(int permissionId, String permissionName, String details, List<Role> roles) {
		super();
		this.permissionId = permissionId;
		this.permissionName = permissionName;
		this.details = details;
		this.roles = roles;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}