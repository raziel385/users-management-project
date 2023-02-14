package com.example.usersmanagement.service.shared;

import java.util.List;


/**
 * 
 * @author benedetto.cosentino
 *
 */
public final class PageDTO<T> {

	private final List<T> content;
	private final long totalElements;
	private final boolean firstPage;
	private final boolean lastPage;

	private PageDTO(List<T> content, boolean firstPage, boolean lastPage, long totalElements) {
		if (null == content) {
			throw new IllegalArgumentException("Content must not be null!");
		}
		this.firstPage = firstPage;
		this.lastPage = lastPage;
		this.totalElements = totalElements;
		this.content = content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Page#getTotalElements()
	 */
	public long getTotalElements() {
		return totalElements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Page#isFirstPage()
	 */
	public boolean isFirstPage() {
		return firstPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Page#getContent()
	 */

	public List<T> getContent() {
		return content;
	}

	public static <T> PageDTO<T> of(List<T> content, int number, long totalElements) {
		final int totalPages = content.size() == 0 ? 1 : (int) Math.ceil((double) totalElements / (double) content.size());
		final boolean firstPage = !(number > 0);
		final boolean lastPage = !(number + 1 < totalPages);
		return new PageDTO<>(content, firstPage, lastPage, totalElements);
	}

	public static <T> PageDTO<T> of(List<T> content) {
		return PageDTO.of(content, 0, content.size());
	}
}
