package com.andre.core.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
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
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends DataObject implements Serializable {

	private static final long serialVersionUID = -4051007854697446181L;

	/**
	 * 主键ID。
	 */
	@Id
	@GeneratedValue(generator = "clockSeq")
	@GenericGenerator(name = "clockSeq", strategy = CoreConstants.ID_GENERATOR_CLASS)
	private Long id;
	
	@Version
	private Integer version;

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

	public User(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

}
