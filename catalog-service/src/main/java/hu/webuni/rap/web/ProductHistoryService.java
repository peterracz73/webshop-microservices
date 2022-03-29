package hu.webuni.rap.web;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.rap.model.HistoryData;
import hu.webuni.rap.model.Product;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductHistoryService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	@SuppressWarnings({"rawtypes", "unchecked"})
	public List<HistoryData<Product>> getAirportHistory(long id){
		
		List resultList = AuditReaderFactory.get(em)
		.createQuery()
		.forRevisionsOfEntity(Product.class, false, true)
		.add(AuditEntity.property("id").eq(id))
		.getResultList()
		.stream()
		.map(o ->{
			Object[] objArray = (Object[])o;
			DefaultRevisionEntity revisionEntity = (DefaultRevisionEntity)objArray[1];
			Product product = (Product)objArray[0];
			
			return new HistoryData<Product>(
				product,
				(RevisionType)objArray[2],
				revisionEntity.getId(),
				revisionEntity.getRevisionDate()
			);
		}).toList();
		
		return resultList;
	}

}
