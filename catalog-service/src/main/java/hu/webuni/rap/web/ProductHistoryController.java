package hu.webuni.rap.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import hu.webuni.rap.api.HistoryControllerApi;
import hu.webuni.rap.api.model.ProductHistoryDto;
import hu.webuni.rap.mapper.HistoryDataMapper;
import hu.webuni.rap.model.HistoryData;
import hu.webuni.rap.model.Product;
import hu.webuni.rap.service.ProductHistoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductHistoryController implements HistoryControllerApi{

	private final NativeWebRequest nativeWebRequest;
	private final ProductHistoryService service;
	private final HistoryDataMapper mapper;

	@Override
	public Optional<NativeWebRequest> getRequest() {
		return Optional.of(nativeWebRequest);
	}

	@Override
	public ResponseEntity<List<ProductHistoryDto>> getProductHistory(@Valid Long productId) {
		List<ProductHistoryDto> productHistoryDtos = service.getPriceOfProductHistory(productId);
		return ResponseEntity.ok(productHistoryDtos);
	}

	
}
