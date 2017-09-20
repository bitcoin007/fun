package com.hero.fun.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = "Category")
public class Category implements Serializable{
	
	private static final long serialVersionUID = -6964107475551380950L;
	
	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;
	
	@Column(name = "typename")
	private String typename;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();
}
