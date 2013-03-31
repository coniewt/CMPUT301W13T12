package ca.ualberta.c301w13t12recipes.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import ca.ualberta.c301w13t12recipes.model.Ingredient;
import ca.ualberta.c301w13t12recipes.model.LocalDB;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import ca.ualberta.c301w13t12recipes.model.StrResource;

/**
 * Database controller class, used for various background handling purpose.
 * 
 * @author GUANQI HUANG
 * 
 */

public class DatabaseController {
	private LocalDB database;
	private Context context;

	/**
	 * @param context
	 */
	public DatabaseController(Context context) {
		this.database = new LocalDB(context);
		this.context = context;
	}
	/**
	 * Get local database
	 * 
	 * @return localDB object
	 */
	public LocalDB getDB() {
		return this.database;
	}

	/**
	 * Add recipe into local database
	 * 
	 * @param recipe
	 *            object
	 */
	public void addRecipe(Recipe re) {
		this.database.addLocal_Recipe_Table(re);
	}

	/**
	 * remove all recipe in local database
	 */
	public void removeAll() {
		this.database.clear_All();
	}

	/**
	 * remove all recipe in local database
	 */
	public void delete(Recipe recipe) {
		this.database.delete_Local_Recipe(recipe.getId());
	}

	/**
	 * @param key
	 *            a string of keyword
	 * @return list<recipe>
	 */
	public List<Recipe> search(String key) {
		return this.database.searchRecipebyKeyword(key);
	}

	/**
	 * @return the name list
	 */
	public List<String> getNameList() {
		return this.database.getAutoCompleteKeyword();
	}

	/**
	 * @param recipe
	 * @return if the recipe is in local database,return true, else return false
	 */
	public boolean isLocalRecipeExists(Recipe recipe) {
		return this.database.isLocalIdExists(recipe);
	}

	/**
	 * @param recipe
	 * @return if the recipe is in remote database,return true, else return
	 *         false
	 */
	public boolean isRemoteRecipeExists(Recipe recipe) {
		return this.database.isRemoteIdExists(recipe);
	}

	/**
	 * @param recipe
	 */
	public void postRemote(List<Recipe> li) {
		for (Recipe re : li) {
			if (this.database.postRemote(re))
				Log.v("Database", "add to remote database");
		}
	}

	/**
	 * Get a list of ingredients from local ingredients database
	 * 
	 * @return ArrayList<Ingredient>
	 */
	public ArrayList<Ingredient> getIngredListFromIngredDB() {
		return this.database.getLocal_Ingredient_List();

	}

	/**
	 * Remove the item at specific position
	 * @param pos the position of database
	 */
	public void removeIngredFromIngredDB(int pos) {
		this.database.removeLocal_Ingredient_List(pos);// this.database.
	}

	/**
	 * Add the ingredients
	 * @param in the ingredient object
	 */
	public void addIngredFromIngredDB(Ingredient in) {
		this.database.addLocal_Ingredient_Table(in);
	}
}
