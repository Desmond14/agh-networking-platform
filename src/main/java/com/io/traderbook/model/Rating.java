package com.io.traderbook.model;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne
	private User rater;
	
	@OneToOne
	private User ratee;
	
	private boolean isPositive;
	
	public Rating(Integer id, User rater, User ratee, boolean isPositive) {
		this.id = id;
		this.rater = rater;
		this.ratee = ratee;
		this.isPositive = isPositive;
	}
	
	public Rating() {
		
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public User getRater() {
		return this.rater;
	}
	
	public User getRatee() {
		return this.ratee;
	}
	
	public boolean isPositive() {
		return this.isPositive;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setRater(User rater) {
		this.rater = rater;
	}
	
	public void setRatee(User ratee) {
		this.ratee = ratee;
	}
	
	public void setPositive(boolean isPositive) {
		this.isPositive = isPositive;
	}
	
}