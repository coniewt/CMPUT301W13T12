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
 * Database controller class, used for background database handling.
 * 
 * @author GUANQI HUANG
 * 
 */

public class DatabaseController {
	private LocalDB database;
	private Context context;

	/**
	 * Constructor
	 * @param Context
	 */
	public DatabaseController(Context context) {
		this.database = new LocalDB(context);
		this.context = context;
	}
	
	/**
	 * Get local database instance
	 * 
	 * @return LocalDB - Current instance of the database
	 */
	public LocalDB getDB() {
		return this.database;
	}

	/**
	 * Add a recipe into local database
	 * 
	 * @param Recipe A recipe object
	 */
	public void addRecipe(Recipe re) {
		this.database.addLocal_Recipe_Table(re);
	}

	/**
	 * Delete a recipe from the local database
	 * 
	 * @param Recipe A recipe object
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
	 * @param String A string of keyword for searching
	 * 
	 * @return List<Recipe> - A list of Recipe object
	 */
	public List<Recipe> search(String key) {
		return this.database.searchRecipebyKeyword(key);
	}

	/**
	 * Pop up suggest search keyword for auto complete functionality
	 * 
	 * @return List<String> - A list of suggested keywords
	 */
	public List<String> getNameList() {
		return this.database.getAutoCompleteKeyword();
	}

	/**
	 * Check if the recipe is existed locally
	 * @param Recipe A recipe object
	 * @return boolean - Return True if recipe is already existed locally, False if not
	 */
	public boolean isLocalRecipeExists(Recipe recipe) {
		return this.database.isLocalIdExists(recipe);
	}

	/**
	 * Check if the recipe is existed on web server
	 * @param Recipe A recipe object
	 * @return boolean - Return True if recipe is already existed on web server, False if not
	 */
	public boolean isRemoteRecipeExists(Recipe recipe) {
		return this.database.isRemoteIdExists(recipe);
	}

	/**
	 * Post recipe to web server
	 * 
	 * @param Recipe A recipe object
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
	 * @return ArrayList<Ingredient> - A list of ingredient
	 */
	public ArrayList<Ingredient> getIngredListFromIngredDB() {
		return this.database.getLocal_Ingredient_List();

	}

	/**
	 * Remove an ingredient from a recipe in local database
	 * 
	 * @param Ingredient An ingredient object
	 */
	public void removeIngredFromIngredDB(Ingredient ingredient) {
		this.database.removeLocal_Ingredient_List(ingredient);// this.database.
	}

	/**
	 * Add an ingredient to a recipe in local database
	 * 
	 * @param Ingredient An ingredient object
	 */
	public void addIngredFromIngredDB(Ingredient in) {
		this.database.addLocal_Ingredient_Table(in);
	}
}
