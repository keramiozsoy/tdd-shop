package com.app.tddshop.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.tddshop.enums.DiscountType;

@Entity
@Table(name = "CAMPAIGN")
public class Campaign {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "DISCOUNT_AMOUNT")
	private BigDecimal discountAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "DISCOUNT_TYPE")
    private DiscountType discountType;
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

}
