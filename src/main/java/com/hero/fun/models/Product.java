package com.hero.fun.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = "Product")
public class Product implements Serializable{

	private static final long serialVersionUID = -3707120946496439012L;
	
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;
	
	@ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
	
	@Column(name = "typename")
	private String typename;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image")
	private byte[] image;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
}
