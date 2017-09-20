package com.hero.fun.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hero.fun.models.Category;

public interface CategoryRepository  extends CrudRepository<Category, UUID>{

	@Query(value = "Select c from Category c where c.typename = :typename and c.description = :description")
	List<Category> findByTypeNameAndDescription(@Param("typename") String typename, @Param("description") String description);
}
