package ca.ualberta.c301w13t12recipes.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;
import android.util.Log;

/**
 * Local database class. Used to build basic database structure and functionality
 * @author dw
 * 
 */
public class LocalDB {
	private SQLiteDatabase db;

	/**
	 * Constructor to create a singleton for database instance
	 * @param Context
	 */
	public LocalDB(Context context) {
		DatabaseSingleton ds = DatabaseSingleton.getInstance(context);
		db = ds.getDB();
	}

	/**
	 * Add recipe to local database
	 * @param Recipe Recipe wish to store into local database
	 * @throws Exception
	 */
	public void addLocal_Recipe_Table(Recipe re) {
		ContentValues cv = new ContentValues();
		try {
			Log.v("Add to Table---------------", re.getName());
			cv.put("id", re.getId());
			cv.put("content", re.toJson().toString());
			// cv.put(StrResource.COL_CONTENT, Recipe.toJson().toString() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (isLocalIdExists(re)) {
			updateRemoteRecipe(re);
		} else
			db.insert(StrResource.LOCAL_RECIPE_TABLE_NAME, null, cv);
	}

	/**
	 * Add ingredient to local database
	 * @param Ingredient Ingredients wish to add into local database
	 * @throws Exception
	 */
	public void addLocal_Ingredient_Table(Ingredient in) {
		ContentValues cv = new ContentValues();
		try {
			cv.put("id", in.getId());
			cv.put("name", in.getName());
			cv.put("amount", in.getAmount());
			// cv.put(StrResource.COL_CONTENT, Recipe.toJson().toString() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		db.insert(StrResource.LOCAL_INGREDIENT_TABLE_NAME, null, cv);
	}
	

	/**
	 * To get a list of ingredients from the local database
	 * 
	 * @return ArrayList<Ingredient> - A list of ingredient in the local fridge
	 */
	public ArrayList<Ingredient> getLocal_Ingredient_List() {
		ArrayList<Ingredient> out = new ArrayList<Ingredient>();
		Cursor c = db.rawQuery("SELECT * FROM "
				+ StrResource.LOCAL_INGREDIENT_TABLE_NAME, new String[] {});
		if (c.moveToFirst()) {
			while (c.isAfterLast() == false) {
				out.add(new Ingredient(c.getString(0),c.getString(1), c.getString(2)));
				Log.v("getLocalIngre",c.getString(0));
				c.moveToNext();
			}
			return out;
		}
		c.close();
		return out;
	}
	
	/**
	 * Remove an ingredient from the local database
	 * @param Ingredient Ingredient wish to remove from local fridge
	 */
	public void removeLocal_Ingredient_List(Ingredient ingredient){
		
		String id = ingredient.getId();
		Log.v("ingredient:",id);
		db.delete(StrResource.LOCAL_INGREDIENT_TABLE_NAME, "id" + " =?",new String[] { id, });
	}
	
	/**
	 * Get the local recipe list.
	 * @return ArrayList<Recipe> An array of recipes in local database
	 * @throws JSONException
	 */
	public ArrayList<Recipe> getLocal_Recipe_List() {
		try {
			ArrayList<Recipe> out = new ArrayList<Recipe>();
			Cursor c = db.rawQuery("SELECT * FROM "
					+ StrResource.LOCAL_RECIPE_TABLE_NAME, new String[] {});
			if (c.moveToFirst()) {
				while (c.isAfterLast() == false) {
					JSONObject obj = toJsonRecipe(c.getString(1));
					out.add(toRecipe(obj));
					c.moveToNext();
				}
				return out;
			}
			c.close();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ArrayList<Recipe>();
	}

	/**
	 * Search local recipes by keywords
	 * 
	 * @param String keywords for the search
	 * @return ArrayList<Recipe> - An array of Recipe math the keywords
	 * @throws JSONException
	 */
	public ArrayList<Recipe> searchRecipebyKeyword(String keyword) {
		ArrayList<Recipe> in = new ArrayList<Recipe>();
		ArrayList<Recipe> out = new ArrayList<Recipe>();
		in = getLocal_Recipe_List();
		for (int i = 0; i < in.size(); i++) {
			Recipe re = in.get(i);
			Log.v("recipe", re.toString());
			if (re.toString().contains(keyword)) {
				out.add(re);
			}
		}
		return out;
	}

	/**
	 * Auto complete the recipe search keywords for user
	 * @return ArrayList<String> - A list of name of recipe exiting in local database
	 * matching part of the search keywords
	 */
	public ArrayList<String> getAutoCompleteKeyword() {
		ArrayList<Recipe> in = new ArrayList<Recipe>();
		ArrayList<String> out = new ArrayList<String>();
		in = getLocal_Recipe_List();
		for (int i = 0; i < in.size(); i++) {
			Recipe re = in.get(i);
			Log.v("recipe", re.toString());
			out.add(re.getName());
		}
		return out;
	}

	/**
	 * Check if local recipe exists
	 * 
	 * @param Recipe Recipe wish to check
	 * @return boolean - Return True if recipe existed locally, False otherwise.
	 */
	public boolean isLocalIdExists(Recipe recipe) {

		Cursor c = db.rawQuery("SELECT * FROM "
				+ StrResource.LOCAL_RECIPE_TABLE_NAME + " WHERE id" + "=?",
				new String[] { recipe.getId(), });
		if (c == null || c.getCount() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Check if recipe exists on web server
	 * 
	 * @param Recipe Recipe wish to check
	 * @return boolean - Return True if recipe existed on web server, False otherwise.
	 */
	public boolean isRemoteIdExists(Recipe recipe) {

		Cursor c = db.rawQuery("SELECT * FROM "
				+ StrResource.REMOTE_RECIPE_TABLE_NAME + " WHERE id =?",
				new String[] { recipe.getId(), });
		if (c == null || c.getCount() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Post a task to the remote table of the database
	 * 
	 * @param Recipe Recipe to be added to remote
	 * @return boolean - Return True if recipe successfully added to table remote, False otherwise.
	 */

	public boolean postRemote(Recipe re) {
		ContentValues cv = new ContentValues(); 
		cv.put("id", re.getId());
		cv.put("content", re.toJson().toString());
		Log.v(re.getId(), re.toString());
		try {
			if (isRemoteIdExists(re))
				db.insert(StrResource.REMOTE_RECIPE_TABLE_NAME, null, cv);
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isRemoteIdExists(re);
	}

	/**
	 * Deletes a task from the "local" table of the database
	 * 
	 * @param String The id of the task to be deleted.
	 */
	public void delete_Local_Recipe(String id) {
		db.delete(StrResource.LOCAL_RECIPE_TABLE_NAME, "id" + " =?",
				new String[] { id, });
	}

	/**
	 * Gets a task (if exists) from the "local" table of the database.
	 * 
	 * @param String  ID of task to be performed
	 * @return Recipe - Return Recipe object if found, null otherwise
	 * @throws JSONException
	 */

	public Recipe get_Local_Recipe(String id) {
		try {
			Cursor c = db.rawQuery("SELECT * FROM "
					+ StrResource.LOCAL_RECIPE_TABLE_NAME+" WHERE id =?", new String[] {id,});
			if (c == null || c.getCount() == 0) {
				return null;
			} else {
				c.moveToFirst();
				String string = c.getString(0);
				JSONObject jsonObject = new JSONObject(string);
				return toRecipe(jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Convert JSON object to Recipe object
	 * 
	 * @param JSONObject JSON object containing the recipe
	 * @return Recipe - Return the recipe in the JSON object, null if object contains nothing
	 * @throws JSONException
	 */
	public Recipe toRecipe(JSONObject j) throws JSONException {
		Recipe re;
		if (j == null) {
			return null;
		} else {
			re = new Recipe(j.getString("id"), j.getString("user"),
					j.getString("name"), toIngredients(j),
					j.getString("directions"));
			re.setImageList(toImages(j));
			re.setPassword(j.getString("password"));
			return re;
		}
	}

	/**
	 * Convert the list of ingredients from JSON Object to list of Ingredient
	 * 
	 * @param JSONObject JSON object containing the ingredients
	 * @return List<Ingredient> - Return a list of Ingredient object
	 * @throws JSONException
	 */
	private static List<Ingredient> toIngredients(JSONObject jsonTask)
			throws JSONException {
		try {
			JSONArray jsonArray = jsonTask.getJSONArray("Ingredients");
			ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
			// ResponseFactory respFactory =
			// getRespFactory(jsonTask.getString("Ingredients"));
			for (int i = 0; i < jsonArray.length(); i++) {
				// Response resp =
				// respFactory.createResponse(jsonArray.getJSONObject(i).getString("annotation"),
				// jsonArray.getJSONObject(i).getString("content"));
				// resp.setTimestamp(new
				// SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(jsonArray.getJSONObject(i).getString("timestamp")));
				Ingredient in = new Ingredient(jsonArray.getJSONObject(i)
						.getString("name"), jsonArray.getJSONObject(i)
						.getString("amount"));
				ingredients.add(in);
			}
			return ingredients;
		} catch (ParseException e) {
			System.err.println("Could not parse date");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Convert the images corresponding to the recipe from a JSON object
	 * @param JSONObject JSON object containing the images
	 * @return ArrayList<Image> - An arraylist of image objects
	 * @throws JSONException
	 */
	private static ArrayList<Image> toImages(JSONObject jsonTask)
			throws JSONException {
		try {
			JSONArray jsonArray = jsonTask.getJSONArray("image");
			ArrayList<Image> ingredients = new ArrayList<Image>();
			// ResponseFactory respFactory =
			// getRespFactory(jsonTask.getString("Ingredients"));
			for (int i = 0; i < jsonArray.length(); i++) {
				Image in = new Image(jsonArray.getJSONObject(i).getString(
						"HD_path"), jsonArray.getJSONObject(i).getString(
						"TN_path"), Image.getTime());
				ingredients.add(in);
			}
			return ingredients;
		} catch (ParseException e) {
			System.err.println("Could not parse date");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Convert String to JSON object
	 * 
	 * @param String Contents of the recipe
	 * @return JSONObject - JSON onject of that recipe
	 */
	private JSONObject toJsonRecipe(String recipeContent) throws JSONException {
		JSONObject jsonRecipe = new JSONObject(recipeContent);
		return jsonRecipe;
	}

	/**
	 * Gets a recipe from the remote table of the database
	 * 
	 * @param String ID of recipe to search for
	 * @return Recipe - Return recipe if found, null otherwise
	 * @throws JSONException
	 */
	public Recipe getRemoteRecipe(String id) {
		try {
			Cursor c = db.rawQuery("SELECT * FROM "
					+ StrResource.REMOTE_RECIPE_TABLE_NAME + " WHERE id" + "=?",
					new String[] { id, });
			c.moveToFirst();
			if (c == null || c.getCount() == 0) {
				return null;
			} else {
				String taskContent = c.getString(1);
				JSONObject jsonRecipe = toJsonRecipe(taskContent);
				return toRecipe(jsonRecipe);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get the remote recipe list.
	 * 
	 * @return ArrayList<Recipe> - An arraylist of tasks in the remote table of the database
	 * @throws JSONException
	 */
	public ArrayList<Recipe> getRemoteRecipeList() {
		try {
			Log.d("refresh", "STARTING REMOTE TASK LIST");
			ArrayList<Recipe> out = new ArrayList<Recipe>();
			Cursor c = db.rawQuery("SELECT * FROM "
					+ StrResource.REMOTE_RECIPE_TABLE_NAME, new String[] {});
			if (c.moveToFirst()) {
				while (c.isAfterLast() == false) {
					JSONObject obj = toJsonRecipe(c.getString(1));
					out.add(toRecipe(obj));
					c.moveToNext();
				}
			}
			Log.d("refresh", "sizeof remotetasklist = " + out.size());
			return out;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Updates the local database with the passed in task based on the id.
	 * 
	 * Looks through both local and remote tables for a matching task.
	 * 
	 * @param Recipe Recipe user wishes to change
	 */
	public void updateLocalRecipe(Recipe re) {
		try {
			ContentValues cv = new ContentValues();
			cv.put("id", re.getId());
			cv.put("content", re.toJson().toString());
			// cv.put(StrResource.COL_ID,task.getId());

			int n = db.delete(StrResource.LOCAL_RECIPE_TABLE_NAME, "id" + "=?",
					new String[] { re.getId(), });
			if (n == 1) {
				db.insert(StrResource.LOCAL_RECIPE_TABLE_NAME, null, cv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the remote database with the passed in task based on the id.
	 * 
	 * Looks through both local and remote tables for a matching task.
	 * 
	 * @param Recipe Recipe user wishes to change
	 */
	public void updateRemoteRecipe(Recipe re) {
		try {
			ContentValues cv = new ContentValues();
			cv.put("id", re.getId());
			cv.put("content", re.toJson().toString());
			// cv.put(StrResource.COL_ID,task.getId());

			int n = db.delete(StrResource.REMOTE_RECIPE_TABLE_NAME,
					"id" + "=?", new String[] { re.getId(), });
			if (n == 1) {
				db.insert(StrResource.LOCAL_RECIPE_TABLE_NAME, null, cv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete all recipes from remote server
	 */
	public void clear_Remote() {
		db.delete(StrResource.REMOTE_RECIPE_TABLE_NAME, null, null);
	}

	/**
	 * Delete all recipes from both local storage and remote server
	 */
	// fixed
	public void clear_All() {
		db.delete(StrResource.LOCAL_RECIPE_TABLE_NAME, null, null);
		// db.delete(StrResource.REMOTE_RECIPE_TABLE_NAME, null, null);
	}

	/**
	 * Delete all recipes from local storage
	 */
	// fixed
	public void clear_Local() {
		db.delete(StrResource.LOCAL_RECIPE_TABLE_NAME, null, null);
	}

	/**
	 * Close database instance
	 */
	public void close() {
		db.close();
	}

	/**
	 * Transfer the recipe in remote database to local
	 * @param Recipe Recipe wish to transfer
	 */
	public void transferFromRemoteToLocal(Recipe re) {
		System.out.println("Transfer"+re.toString());
		addLocal_Recipe_Table(re);
		if (isLocalIdExists(re)) {
			Log.v("LocalDb:", "Successful transfer from remote db to local db");
		}
		delete_Local_Recipe(re.getId());
	}
}
