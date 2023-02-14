package com.example.usersmanagement.service.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author benedetto.cosentino
 *
 */
public final class IdentityDTO {

	private final String id;

	@JsonCreator
	private IdentityDTO(@JsonProperty("id") String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public static IdentityDTO of(String id) {
		return new IdentityDTO(id);
	}

}
