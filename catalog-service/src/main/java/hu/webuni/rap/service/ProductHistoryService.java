package hu.webuni.rap.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.rap.api.model.ProductHistoryDto;
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
	@Cacheable(value="PriceOfProductHistory", key="#id")
	public List<ProductHistoryDto> getPriceOfProductHistory(long id){
		
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
		List<ProductHistoryDto> resultDto =new ArrayList<>();
		resultList.forEach(hd -> {
			HistoryData<Product> productHistory = (HistoryData<Product>)hd;
			if (productHistory.getRevType()!=RevisionType.DEL) {
				resultDto.add(getProductHistoryDtoFromHistory(productHistory));
			}
		});
		
		return resultDto;
	}

	private ProductHistoryDto getProductHistoryDtoFromHistory(HistoryData<Product> hd) {
		ProductHistoryDto productHistoryDto = new ProductHistoryDto();
		productHistoryDto.setName(hd.getData().getName());
		productHistoryDto.setPrice(hd.getData().getPrice());
		LocalDateTime ofInstant = LocalDateTime.ofInstant(hd.getDate().toInstant(), ZoneId.systemDefault());
		productHistoryDto.setDate(ofInstant);
		return productHistoryDto;
	}

}
