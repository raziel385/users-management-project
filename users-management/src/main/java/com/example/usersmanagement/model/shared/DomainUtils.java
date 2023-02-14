/**
 * 
 */
package com.example.usersmanagement.model.shared;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * 
 * @author benedetto.cosentino
 *
 */
public final class DomainUtils {

	private DomainUtils() {
	}

	public static <T> T checkNotNull(T reference, Object errorMessage) {
		if (reference == null) {
			throw new IllegalArgumentException(String.valueOf(errorMessage));
		}
		if (reference instanceof String && ((String) reference).isEmpty()) {
			throw new IllegalArgumentException(String.valueOf(errorMessage));
		}
		return reference;
	}

	public static <T> Collection<T> checkNotEmpty(Collection<T> reference, Object errorMessage) {
		if (reference == null || reference.isEmpty()) {
			throw new IllegalArgumentException(String.valueOf(errorMessage));
		}
		return reference;
	}

	public static void checkCondition(boolean condition, Object errorMessage) {
		if (!condition) {
			throw new IllegalArgumentException(String.valueOf(errorMessage));
		}
	}

	public static <T> ImmutableList<T> toImmutable(List<T> items) {
		return items == null ? ImmutableList.of() : ImmutableList.copyOf(items);
	}
	
	public static <T> ImmutableList<T> toImmutable(Collection<T> items) {
		return items == null ? ImmutableList.of() : ImmutableList.copyOf(items);
	}

	public static <T> ImmutableSet<T> toImmutable(Set<T> items) {
		return items == null ? ImmutableSet.of() : ImmutableSet.copyOf(items);
	}
	
	public static <K,V> ImmutableMap<K,V> toImmutable(Map<K,V> items) {
		return items == null ? ImmutableMap.of() : ImmutableMap.copyOf(items);
	}

	public static void throwException(String errorMessage) {
		throw new IllegalStateException(errorMessage);
	}
}
