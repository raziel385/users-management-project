package com.example.usersmanagement.model.shared;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * @author benedetto.cosentino
 *
 * @param <T>
 */
@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity> extends BaseJpaRepository<T, String> {

	default T findExisting(String id) {
		final Optional<T> entity = this.findById(id);
		if (!entity.isPresent())
			throw new IllegalArgumentException(String.format("Unable to found entity with id '%s'", id));
		return entity.get();
	}

	default T findExisting(Specification<T> spec) {
		final Optional<T> entity = this.findOne(spec);
		if (!entity.isPresent())
			throw new IllegalArgumentException("Unable to found entity with spec");
		return entity.get();
	}

}
