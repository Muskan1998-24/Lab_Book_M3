package com.capg.controller;

import java.util.List;

import com.capg.entities.Product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capg.dto.ProductDto;
import com.capg.entities.Product;
import com.capg.exceptions.ProductNotFoundException;
import com.capg.service.IProductService;



     @RestController
     public class ProductRestController
     {
	 @Autowired
	 private IProductService service;
	
	 @GetMapping("/products/find/{id}")
	 public ResponseEntity<Product> getProduct(@PathVariable("id") int id )
	 {
		Product product=service.findProductById(id);
		if(product==null)
		{
			 ResponseEntity<Product> response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new ProductNotFoundException("Product does not exist for "+id);
		}
		else {
			ResponseEntity<Product> response=new ResponseEntity<>(HttpStatus.OK);
			return response;
		}
	}
	
	@PostMapping("/products/add")
	public ResponseEntity<Product> addProduct(@RequestBody ProductDto dto){
		Product product = convert(dto);
        product = service.save(product);
        ResponseEntity<Product> response = new ResponseEntity<>(product, HttpStatus.OK);
        return response;
    }

        Product convert(ProductDto dto) 
        {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return product;
    
		
	}
	@GetMapping("/products")
	public ResponseEntity<List<Product>> fetchAll()
	{
		List<Product> list=service.showAllProducts();
		ResponseEntity<List<Product>> response=new ResponseEntity<>(list,HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("products/delete/{id}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") int id )
	{
		boolean result=service.remove(id);
		ResponseEntity<Boolean> response=new ResponseEntity<>(result,HttpStatus.OK);
		return response;
	}
	@PutMapping("/products/update/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody ProductDto dto , @PathVariable("id") int id){
		Product product=convert(dto);
		product.setId(id);
		product = service.save(product);
		ResponseEntity<Product> response=new ResponseEntity<>(HttpStatus.OK);
		return response;
	}
}