package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
	 
	@Entity
	public class Pet implements Serializable {
	 
	private static final long serialVersionUID = -3009157732242241606L;
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long id;
	  @Column(name = "Name")
	  private String name;
	 
	  public Pet(String name) {
		  this.name = name;
	  }
	  
	  @Override
	  public String toString() {
	    return "" + id +", " + name;
	  }
	}
