package ca.ualberta.c301w13t12recipes.model;

import java.io.Serializable;

/**
 * Part of the recipe class, stores ingredients and associated amount.
 * @author YUWEI DUAN
 *
 */
public class Ingredient implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name,amount;
	private int id;
	
	public Ingredient(String name,String amount){
		this.name=name;
		this.amount=amount;
	}
	
	/** 
	 * Get name of the ingredient
	 * @return Name of ingredient
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Set ID
	 * @param i
	 */
	public void setId(int i){
		id=i;
	}
	
	/**
	 * Get ID
	 * @return id
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * Get amount of the ingredient
	 * @return Amount of ingredient
	 */
	public String getAmount(){
		return this.amount;
	}
	
	/**
	 * Convert ingredient plus associated amount into one string
	 * @return Single string of ingredient + amount
	 */
	public String toString(){
		return this.name+","+this.amount;
	}
	
	/**
	 * Set the name of the ingredient
	 * @param Name of ingredient
	 */
	public void setName(String name){
		this.name=name;
	}
	
	/**
	 * Set the amount of the ingredient
	 * @param Amount of ingredient
	 */
	public void setamount(String amount){
		this.amount=amount;
	}
}
