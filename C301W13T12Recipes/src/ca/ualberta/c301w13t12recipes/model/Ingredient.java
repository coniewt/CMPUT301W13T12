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
	 * @param name
	 * @param amount
	 */
	public Ingredient(String name, String amount) {
		this.id="ingre@"+System.currentTimeMillis();
		this.name = name;
		this.amount = amount;
	}
	/**
	 * 
	 * @return the id of the ingredient
	 */
	
	public String getId(){
		return this.id;
	}

	/**
	 * Get name of the ingredient
	 * 
	 * @return Name of ingredient
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get amount of the ingredient
	 * 
	 * @return Amount of ingredient
	 */
	public String getAmount() {
		return this.amount;
	}

	/**
	 * Convert ingredient plus associated amount into one string
	 * 
	 * @return Single string of ingredient + amount
	 */
	public String toString() {
		return this.name + "," + this.amount;
	}

	/**
	 * Set the name of the ingredient
	 * 
	 * @param Name
	 *            of ingredient
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the amount of the ingredient
	 * 
	 * @param Amount
	 *            of ingredient
	 */
	public void setamount(String amount) {
		this.amount = amount;
	}

	/**
	 * Convert jsonArray to ingredients list
	 * @exception JSONException
	 * @return list<Ingredient>
	 * @param JSONArray
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
