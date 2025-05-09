package com.desafio.hexagonal.infrastructure.adapters.output.persistence.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "produtoApi", url = "http://localhost:8080/product-service/v1/products")
@Component
public interface ProdutoApi {

	@GetMapping(value = "/cosif", produces = "*/*")
	ResponseEntity<String> produtoCosifById(@RequestParam("idProduto") Long cosif, @RequestParam("idCosif") Long idCosif);
	
	@GetMapping(value = "/produto", produces = "*/*")
	ResponseEntity<String> produtosById(@RequestParam("idProduto") Long prod);
      
}