package ca.ualberta.c301w13t12recipes.model;

import java.io.Serializable;

/**
 * Ingredient is one part of recipe, which stores the name of ingredient 
 * and the amount.
 * @author YUWEI DUAN
 *
 */
public class Ingredient implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name,amount;
	private int id;
	
	public Ingredient(String name,String amount){
		this.name=name;
		this.amount=amount;
	}
	
	/** 
	 * @author YWUEI DUAN
	 * @return name of ingredient
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * @param i
	 */
	public void setId(int i){
		id=i;
	}
	/**
	 * @return
	 */
	public int getId(){
		return id;
	}
	/**
	 * @return amount of ingredient
	 */
	
	public String getAmount(){
		return this.amount;
	}
	/**
	 * @return the string of an recipe
	 */
	public String toString(){
		return this.name+","+this.amount;
	}
	/**
	 * Set the Ingredient name
	 * @param name of an ingredient
	 */
	public void setName(String name){
		this.name=name;
	}
	/**
	 * Set the Ingredient amount
	 * @param amount of an ingredient
	 */
	public void setamount(String amount){
		this.amount=amount;
	}
}
