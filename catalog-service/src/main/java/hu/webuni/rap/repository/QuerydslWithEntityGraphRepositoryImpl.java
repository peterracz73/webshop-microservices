package hu.webuni.rap.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import hu.webuni.rap.model.Product;

public class QuerydslWithEntityGraphRepositoryImpl extends SimpleJpaRepository<Product, Integer> 

	implements QuerydslWithEntityGraphRepository<Product>{
	
	private final EntityManager entityManager;
	private final EntityPath<Product> path;
	private final PathBuilder<Product> builder;
	private final Querydsl querydsl;
	
	public QuerydslWithEntityGraphRepositoryImpl(EntityManager em) {
		super(Product.class, em);
		this.entityManager = em;
		this.path = SimpleEntityPathResolver.INSTANCE.createPath(Product.class);
		this.builder = new PathBuilder<>(path.getType(), path.getMetadata());
		this.querydsl = new Querydsl(em, builder);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll(Predicate predicate, String entityGraphName, Sort sort) {
		JPAQuery<Product> query = (JPAQuery<Product>)querydsl.applySorting(sort, createQuery(predicate).select(path));
		query.setHint(EntityGraph.EntityGraphType.LOAD.getKey(), entityManager.getEntityGraph(entityGraphName));
		return query.fetch();
	}
	
	@SuppressWarnings("rawtypes")
	private JPAQuery createQuery(Predicate predicate) {
		return querydsl.createQuery(path).where(predicate);
	}

	@Override
	public List<Product> findAll(Predicate predicate, String entityGraphName) {
		@SuppressWarnings("unchecked")
		JPAQuery<Product> query = createQuery(predicate).select(path);
		query.setHint(EntityGraph.EntityGraphType.LOAD.getKey(), entityManager.getEntityGraph(entityGraphName));
		return query.fetch();
	}

}
