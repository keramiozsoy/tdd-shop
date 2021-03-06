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
@Table(name = "MERGE_CATEGORY_CAMPAIGN")
public class MergeCategoryCampaign {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER) // EAGER LAZY yapmak için mapper yapacağım
	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
	private Category category;

	@ManyToOne(fetch = FetchType.EAGER) // EAGER LAZY yapmak için mapper yapacağım
	@JoinColumn(name = "CAMPAING_ID", referencedColumnName = "ID")
	private Campaign campaing;

	public Campaign getCampaing() {
		return campaing;
	}

	public void setCampaing(Campaign campaing) {
		this.campaing = campaing;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Category getCategory() {
		return category;
	}
}
