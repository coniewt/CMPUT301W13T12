package ca.ualberta.c301w13t12recipes.controller;

import java.util.List;

import android.content.Context;
import ca.ualberta.c301w13t12recipes.model.LocalDB;
import ca.ualberta.c301w13t12recipes.model.Recipe;
/**
 * Database controller class, used for various background handling purpose.
 * 
 * @author GUANQI HUANG 
 *
 */

public class DatabaseController {
	private LocalDB database;
	/**
	 * @param context
	 */
	public DatabaseController(Context context){
		this.database = new LocalDB(context);	
	}
	/**
	 * Get local database
	 * @return localDB object 
	 */
	public LocalDB getDB(){
		return this.database;
	}
	
	/**
	 * Add recipe into local database
	 * @param recipe object
	 */
	public void addRecipe(Recipe re){
		this.database.addLocal_Recipe_Table(re);	
	}	
	/**
	 * remove all recipe in local database
	 */
	public void removeAll(){
		this.database.clear_All();
	}
	/**
	 * remove all recipe in local database
	 */
	public void delete(String id){
		this.database.delete_Local_Recipe(id);
	}
	/**
	 * @param key a string of keyword
	 * @return list<recipe>
	 */
	public List<Recipe> search(String key){
		return this.database.searchRecipebyKeyword(key);
	}
	/**
	 * @return the name list
	 */
	public List<String> getNameList(){
		return this.database.getAutoCompleteKeyword();
	}

}
