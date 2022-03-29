package hu.webuni.rap.repository;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.querydsl.core.types.Predicate;

public interface QuerydslWithEntityGraphRepository<T> {

	List<T> findAll(Predicate predicate, String entityGraphName, Sort sort);
	List<T> findAll(Predicate predicate, String entityGraphName);
}
