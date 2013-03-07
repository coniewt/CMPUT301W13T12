package ca.ualberta.c301w13t12recipes.model;

public class Ingredient {
	private String name,amount;
	public Ingredient(){
		
	}
	public Ingredient(String name,String amount){
		super();
		this.name=name;
		this.amount=amount;
	}
	/*
	 * @return name of ingredient
	 */
	public String getNmae(){
		return this.name;
	}
	/*
	 * @return amount of ingredient
	 */
	
	public String getAcount(){
		return this.amount;
	}
	/*
	 * @return the string of an recipe
	 */
	public String toString(){
		return this.name+","+this.amount;
	}
	/*
	 * Set the Ingredient name
	 * @param name of an ingredient
	 */
	public void setName(String name){
		this.name=name;
	}
	/*
	 * Set the Ingredient amount
	 * @param amount of an ingredient
	 */
	public void setamount(String amount){
		this.amount=amount;
	}
	
}
