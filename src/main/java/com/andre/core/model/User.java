package com.andre.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.andre.core.CoreConstants;

/**
 * 基础数据模型：用户。
 * 
 * @author Andre
 */
@Entity
@Table(name = "user")
@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class User extends DataObject {

	private static final long serialVersionUID = -4051007854697446181L;

	/**
	 * UID. Unique.
	 */
	@Column(nullable = false, unique = true, length = CoreConstants.USER_NAME_MAX_LENGTH)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(name = "first_name", length = CoreConstants.USER_FIRST_LAST_NAME_MAX_LENGTH)
	private String firstName;

	@Column(name = "last_name", length = CoreConstants.USER_FIRST_LAST_NAME_MAX_LENGTH)
	private String lastName;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public User() {

	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
