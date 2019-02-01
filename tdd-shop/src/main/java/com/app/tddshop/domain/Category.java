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
@Table(name = "CATEGORY")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@ManyToOne(fetch = FetchType.EAGER) // EAGER LAZY yapmak için mapper yapacağım
	@JoinColumn(name = "CAMPAING_ID", referencedColumnName = "ID")
	private Campaign campaing;

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCampaing(Campaign campaing) {
		this.campaing = campaing;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Campaign getCampaing() {
		return campaing;
	}

}
