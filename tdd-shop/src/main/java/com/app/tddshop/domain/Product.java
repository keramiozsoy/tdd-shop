package com.app.tddshop.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "PRICE")
	private Double price;

	@ManyToOne(fetch = FetchType.EAGER) // EAGER LAZY yapmak için mapper yapacağım
	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
	private Category category;
	
	@ManyToOne(fetch = FetchType.EAGER) // EAGER LAZY yapmak için mapper yapacağım
	@JoinColumn(name = "CAMPAING_ID", referencedColumnName = "ID")
	private Campaign campaing;

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public Long getId() {
		return id;
	}

	public Double getPrice() {
		return price;
	}

	public Category getCategory() {
		return category;
	}
	
	public Campaign getCampaing() {
		return campaing;
	}

	public void setCampaing(Campaign campaing) {
		this.campaing = campaing;
	}

}