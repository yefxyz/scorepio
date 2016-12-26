package com.andre.core.model;

import java.util.Date;

import javax.persistence.Basic;
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

@Entity
@Table(name = "parameter")
@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class Parameter extends DataObject {

	private static final long serialVersionUID = 6787306978151899988L;

	/**
	 * 参数代码。唯一标识。
	 */
	@Column(nullable = false, unique = true)
	private Integer code;

	/**
	 * 参数类型。0：一般参数 1：构造config对象的xml配置参数。
	 */
	private Integer type;

	/**
	 * 参数值。
	 */
	@Basic
	@Column(columnDefinition = "LONGTEXT")
	private String value;

	/**
	 * 参数解析器。（解析器类名。）
	 */
	@Column(nullable = false)
	private String parser;

	/**
	 * 参数描述。
	 */
	private String description;

	/**
	 * 最后更新时间。
	 */
	private Date lastUpdateTime;

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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getParser() {
		return parser;
	}

	public void setParser(String parser) {
		this.parser = parser;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
