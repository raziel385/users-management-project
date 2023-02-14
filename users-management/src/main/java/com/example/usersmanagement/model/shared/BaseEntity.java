package com.example.usersmanagement.model.shared;


import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 
 * @author benedetto.cosentino
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseEntity.class);

	@Id
	private String id;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedOn;

	protected BaseEntity() {
		super();
		this.id = UUID.randomUUID().toString();
		this.createdOn = new Date();
	}

	protected BaseEntity(String id) {
		DomainUtils.checkNotNull(id, "existent entity must have an id");
		this.id = id;
	}
	
	public String id() {
		return id;
	}

	public Date createdOn() {
		return createdOn;
	}
	
	public Date updatedOn() {
		return updatedOn;
	}
	
	public Date deletedOn() {
		return deletedOn;
	}
	
	public void delete() {
		this.deletedOn = new Date();
	}
	
	public boolean isValid() {
		return this.deletedOn == null;
	}
	
	protected void update() {
		this.updatedOn = new Date();
	}
	
	protected static void log(String message, Object... args) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(message, args);
		}
	}
	
	@Override
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BaseEntity)) {
			return false;
		}
		final BaseEntity castOther = (BaseEntity) other;
		return Objects.equals(id, castOther.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
