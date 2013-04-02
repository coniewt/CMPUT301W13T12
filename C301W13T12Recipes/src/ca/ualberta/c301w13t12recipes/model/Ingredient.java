package ca.ualberta.c301w13t12recipes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Part of the recipe class, stores ingredients and associated amount.
 * 
 * @author YUWEI DUAN
 * 
 */
public class Ingredient implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name, amount;
	private String id;

	/**
	 * Constructor for new ingredients
	 * @param String ID of the ingredient
	 * @param String Name of the ingredient
	 * @param String Amount of the ingredient
	 */
	public Ingredient(String id,String name, String amount) {
		this.id=id;
		this.name = name;
		this.amount = amount;
	}
	
	/**
	 * Constructor for existing ingredients
	 * @param String Name of the ingredient
	 * @param String Amount of the ingredient
	 */
	public Ingredient(String name, String amount) {
		this.id="ingre@"+System.currentTimeMillis();
		this.name = name;
		this.amount = amount;
	}
	
	/**
	 * Get ID of the ingredient
	 * @return String - ID of the ingredient
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * Get name of the ingredient
	 * 
	 * @return String - Name of ingredient
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get amount of the ingredient
	 * 
	 * @return String - Amount of ingredient
	 */
	public String getAmount() {
		return this.amount;
	}

	/**
	 * Convert ingredient plus associated amount into one string
	 * 
	 * @return String - Single string of ingredient + amount
	 */
	public String toString() {
		return this.name + "," + this.amount;
	}

	/**
	 * Set the name of the ingredient
	 * 
	 * @param String Name of the ingredient
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the amount of the ingredient
	 * 
	 * @param String Amount of the ingredient
	 */
	public void setamount(String amount) {
		this.amount = amount;
	}

	/**
	 * Convert jsonArray to ingredients list
	 * @param JSONArray JSON object array wish to convert to ingredient list
	 * @return List<Ingredient> List of ingredients which contained in the JSON object array
	 * @exception JSONException
	 */
	public List<Ingredient> toIngredientsList(JSONArray jsA) {
		List<Ingredient> li = new ArrayList<Ingredient>();
		if (jsA == null)
			return null;
		else {
			try {
				for (int i = 0; i < jsA.length(); i++) {
					JSONObject jso = jsA.getJSONObject(i);
					li.add(new Ingredient(jso.getString("name"), jso
							.getString("amount")));
				}
			} catch (JSONException jse) {
				jse.printStackTrace();
			}
		}
		return li;
	}
}
