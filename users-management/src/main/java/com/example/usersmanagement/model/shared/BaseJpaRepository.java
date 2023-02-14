package com.example.usersmanagement.model.shared;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 * @author benedetto.cosentino
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<T> {


}
