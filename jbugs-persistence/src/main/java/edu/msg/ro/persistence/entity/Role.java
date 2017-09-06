package edu.msg.ro.persistence.entity;

import java.io.Serializable;
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

@SuppressWarnings("serial")
@Entity
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", unique = true, nullable = false)
	private int roleId;

	@Column(name = "role_name", unique = true, nullable = false, length = 25)
	private String roleName;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	@ManyToMany
	@JoinTable(name = "Role_Permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private List<Permission> permissions;

	public Role() {
		super();
		users = new ArrayList<User>();
		permissions = new ArrayList<Permission>();
	}

	public Role(int roleId, String roleName, List<User> users, List<Permission> permissions) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.users = users;
		this.permissions = permissions;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}