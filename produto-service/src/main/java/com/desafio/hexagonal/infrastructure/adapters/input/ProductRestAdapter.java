package com.desafio.hexagonal.infrastructure.adapters.input;

import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.hexagonal.application.ports.input.GetProductsUseCase;
import com.desafio.hexagonal.domain.model.Product;
import com.desafio.hexagonal.domain.model.ProductCosif;
import com.desafio.hexagonal.infrastructure.adapters.input.rest.data.response.ProductCosifResponse;
import com.desafio.hexagonal.infrastructure.adapters.input.rest.data.response.ProductResponse;
import com.desafio.hexagonal.infrastructure.adapters.output.persistence.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductRestAdapter {
    
    private final GetProductsUseCase getProductsUseCase;

    @Autowired
    private ModelMapper mapper;
    
    private ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){
        List<Product> allProducts = getProductsUseCase.getAllProducts();
        // Domain to response
        List<ProductResponse> allentities = allProducts.stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(allentities, HttpStatus.OK);
    }
    
    @GetMapping("/select")
    public ResponseEntity<JSONArray> getProductsAndCosifs(){
        List<Product> allProducts = getProductsUseCase.getAllProducts();

        JSONArray arr = new JSONArray();
        
        for(Product product : allProducts) {
        	JSONObject json = new JSONObject();
        	JSONObject element = new JSONObject();
        	json.put("id", product.getId());
        	json.put("descricao", product.getDescription());
        	element.put("produto", json);
        	
        	
        	JSONArray array = new JSONArray();
        	
        	for(ProductCosif cosif : product.getProductCosifList()) {
        		JSONObject item = new JSONObject();
        		item.put("id", cosif.getPk().getIdCosif());
        		item.put("descricao", cosif.getPk().getIdCosif() + "-" + cosif.getClassifier());
        		array.add(item);
        	}
        	

        	element.put("cosif", array);
        	
        	arr.add(element);
        }
        
        
        return new ResponseEntity<JSONArray>(arr, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<List<ProductCosifResponse>> getProductsCosif(@PathVariable("id") Long id){
        List<ProductCosif> allProducts = getProductsUseCase.findAllProductCosifByProductId(id);
        // Domain to response
        List<ProductCosifResponse> allCosifs = allProducts.stream().map(productCosif -> mapper.map(productCosif, ProductCosifResponse.class))
        										.collect(Collectors.toList());
        return new ResponseEntity<>(allCosifs, HttpStatus.OK);
    }


    
}
