package com.hero.fun.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hero.fun.models.Product;
import com.hero.fun.repositories.CategoryRepository;
import com.hero.fun.repositories.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value="/pr/product", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Iterable<Product>> getAllProducts(){
		log.info("===================getAllProducts=====================");
		Iterable<Product> products = productRepository.findAll();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pr/productadd", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> saveProduct(@RequestParam("id") UUID id, @RequestParam("categoryid") UUID categoryid, @RequestParam("typename") String typename, @RequestParam("description") String description){
		log.info("===================saveProduct=====================");
		Product product = new Product();
		product.setId(id);
		product.setCreated(new Date());
		product.setDescription(description);
		product.setTypename(typename);
		product.setCategory(categoryRepository.findOne(categoryid));
		productRepository.save(product);
		return new ResponseEntity<String>("ADD SUCCESS!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/pr/productadd2", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> saveProduct2(@RequestBody Product product){
		log.info("====================saveProduct2====================");
		productRepository.save(product);
		return new ResponseEntity<String>("ADD SUCCESS!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/pr/productget", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> getProductById(@RequestParam("productId") UUID productId){
		log.info("==================getProductById======================");
		Object result = productRepository.findOne(productId);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pr/productdel", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> deleteProduct(@RequestParam("productId") UUID productId){
		log.info("===================deleteProduct=====================");
		productRepository.delete(productId);
		return new ResponseEntity<String>("DELETE SUCCESS!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/pr/productupd", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateProduct(@RequestBody Product product){
		log.info("===================updateProduct=====================");
		Product productUpdate = productRepository.findOne(product.getId());
		productUpdate.setDescription(product.getDescription());
		productUpdate.setTypename(product.getTypename());
		productUpdate.setCategory(product.getCategory());
		productRepository.save(productUpdate);
		return new ResponseEntity<String>("UPDATE SUCCESS!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/pr/productgetbynamedesc", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Product>> getProductByTypenameAndDesc(@RequestParam(value = "typename", required = true) String typename, @RequestParam(value = "description", required = true) String description){
		log.info("==================getProductByTypenameAndDesc======================");
		List<Product> result = productRepository.findByTypeNameAndDescription(typename, description);
		return new ResponseEntity<List<Product>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pr/productsetimage", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Product> setimageProduct(@RequestParam(value = "id", required =true) UUID id) throws IOException{
		log.info("==================setimageProduct======================");
		Product result = productRepository.findOne(id);
		File fi = new File("D://file2.pdf");
		byte[] fileContent = Files.readAllBytes(fi.toPath());
		result.setImage(fileContent);
		productRepository.save(result);
		return new ResponseEntity<Product>(result, HttpStatus.OK);
	}
	@RequestMapping(value="/pr/productgetimage", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getimageProduct(@RequestParam(value = "id", required =true) UUID id) throws IOException{
		log.info("==================getimageProduct======================");
		Product result = productRepository.findOne(id);
		byte[] image = result.getImage();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
		return new ResponseEntity<byte[]>(image,headers, HttpStatus.OK);
	}
}
