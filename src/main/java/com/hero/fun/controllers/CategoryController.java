package com.hero.fun.controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hero.fun.models.Category;
import com.hero.fun.repositories.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value="/ca/category", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Iterable<Category>> getAllCategories(){
		log.info("===================getAllCategories=====================");
		Iterable<Category> categories = categoryRepository.findAll();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@RequestMapping(value="/ca/categoryadd", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> saveCategory(@RequestParam("id") UUID id, @RequestParam("typename") String typename, @RequestParam("description") String description){
		log.info("=====================saveCategory===================");
		Category category = new Category();
		category.setDescription(description);
		category.setTypename(typename);
		category.setId(id);
		categoryRepository.save(category);
		return new ResponseEntity<>("ADD SUCCESS!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/ca/categoryadd2", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> saveCategory2(@RequestBody Category category){
		log.info("===================saveCategory2=====================");
		categoryRepository.save(category);
		return new ResponseEntity<>("ADD SUCCESS!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/ca/categoryget", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> getCategoryById(@RequestParam("categoryId") UUID categoryId){
		log.info("===================getCategoryById=====================");
		Object result = categoryRepository.findOne(categoryId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/ca/categorydel", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> deleteCategory(@RequestParam("categoryId") UUID categoryId){
		log.info("===================deleteCategory=====================");
		categoryRepository.delete(categoryId);
		return new ResponseEntity<>("DELETE SUCCESS!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/ca/categoryupd", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateCategory(@RequestBody Category category){
		log.info("===================updateCategory=====================");
		Category categoryUpdate = categoryRepository.findOne(category.getId());
		categoryUpdate.setDescription(category.getDescription());
		categoryUpdate.setTypename(category.getTypename());
		categoryRepository.save(categoryUpdate);
		return new ResponseEntity<>("UPDATE SUCCESS!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/ca/categorygetbynamedesc", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Category>> getCategoryByTypeNameAndDesc(@RequestParam(value = "typename", required = true) String typename, @RequestParam(value = "description", required = true) String description){
		log.info("===================getCategoryByTypeNameAndDesc=====================");
		List<Category> result = categoryRepository.findByTypeNameAndDescription(typename, description);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
