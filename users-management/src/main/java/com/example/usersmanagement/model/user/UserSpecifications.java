package com.example.usersmanagement.model.user;


import org.springframework.data.jpa.domain.Specification;

import com.google.common.base.Splitter;

import static org.springframework.data.jpa.domain.Specification.where;

import java.util.List;


/**
 * 
 * @author benedetto.cosentino
 *
 */
public final class UserSpecifications {

	public static Specification<User> valid() {
        return (root, query, cb) -> {
            return cb.isNull(root.get("deletedOn"));
        };
    }
	
	public static Specification<User> containing(final String search) {
		if (search != null) {
			final List<String> searchParts = Splitter.on(" ")
					.omitEmptyStrings()
					.trimResults()
					.splitToList(search);
			if(!searchParts.isEmpty()) {
				Specification<User> spec = null;
				for (int i = 0; i < searchParts.size(); i++) {
					final String searchPart = searchParts.get(i);
					
					Specification<User> iSpec = where(firstNameLike(searchPart));
					iSpec = iSpec.or(lastNameLike(searchPart));
					spec = i == 0 ? where(iSpec) : spec.and(iSpec);
				}
				return spec.and((root, query, cb) -> {
					query.distinct(true);
					return cb.conjunction();
				});
			}
		}
		return (root, query, cb) -> {
			return cb.conjunction();
		};
	}
	
	
	public static Specification<User> firstNameLike(final String search) {
		return (root, query, cb) -> {
			return cb.like(cb.lower(root.get("firstName")), "%" + search.toLowerCase() + "%");
		};
	}
	
	public static Specification<User> lastNameLike(final String search) {
		return (root, query, cb) -> {
			return cb.like(cb.lower(root.get("lastName")), "%" + search.toLowerCase() + "%");
		};
	}
}
