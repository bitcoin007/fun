package com.hero.fun.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.hero.fun.models.Product;


public interface ProductRepository extends CrudRepository<Product, UUID>{

	@Query(value = "Select p from Product p where p.typename = :typename and p.description = :description")
	List<Product> findByTypeNameAndDescription(@Param("typename") String typename, @Param("description") String description);
	
	@Query(value = "Select p from Product p JOIN p.category c where c.typename = :typename")
	List<Product> findByTypeName(@Param("typename") String typename);
}
