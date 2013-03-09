package ca.ualberta.c301w13t12recipes.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//import org.apache.http.client.fluent.Response;
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
 * @author dw
 * 
 */
public class LocalDB {
	private SQLiteDatabase db;

	/**
	 * Create a new database manager with the "database" being saved to a file.
	 * 
	 * @param filename
	 */
	public LocalDB(Context context) {
		DatabaseSingleton ds = DatabaseSingleton.getInstance(context);
		db = ds.getDB();
	}

	/**
	 * Post a task to the "local" table of the database.
	 * 
	 * @param task
	 *            The task to be added.
	 * @return The task that was added along with it's id.
	 */
	private String getUniqueId() {
		String id = "";
		do {
			id = "local@" + UUID.randomUUID().toString();
		} while (this.localIdExists(id));
		return id;
	}

	/**
	 * @param re
	 */
	public void addLocal_Recipe_Table(Recipe re) {
		ContentValues cv = new ContentValues();
		// String INSERT_NEW_RECIPE =
		// "INSERT INTO "+StrResource.LOCAL_RECIPE_TABLE_NAME+" values";
		// db.execSQL(INSERT_NEW_RECIPE+"("+re.getId()+","+re.getName()+","+re.getUser()+
		// ","+re.getUser()+","+re.getDirections());
		try {
			cv.put(re.getId(), re.toJson().toString());
			// cv.put(StrResource.COL_CONTENT, Recipe.toJson().toString() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		db.insert(StrResource.LOCAL_RECIPE_TABLE_NAME, null, cv);
	}

	/**
	 * Get the local task list.
	 * 
	 * @return A list of tasks in the local table of the database
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
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Recipe> searchRecipebyKeyword(String keyword) {
		try {
			ArrayList<Recipe> out = new ArrayList<Recipe>();
			Cursor c = db.rawQuery("SELECT * FROM "
					+ StrResource.LOCAL_RECIPE_TABLE_NAME
					+ "WHERE Content LIKE %?%", new String[] { keyword, });
			if (c.moveToFirst()) {
				while (c.isAfterLast() == false) {
					JSONObject obj = toJsonRecipe(c.getString(1));
					out.add(toRecipe(obj));
					c.moveToNext();
				}
				return out;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean localIdExists(String id) {
		Cursor c = db.rawQuery("SELECT * FROM "
				+ StrResource.LOCAL_RECIPE_TABLE_NAME + " WHERE id" + "=?",
				new String[] { id, });
		if (c == null || c.getCount() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Post a task to the "remote" table of the database.
	 * 
	 * @param task
	 *            The task to be added.
	 * @return The task that was added along with it's id.
	 */
	/*
	 * public Recipe postRemote(Recipe re) { ContentValues cv = new
	 * ContentValues(); // cv.put(StrResource.COL_ID, task.getId()); try { //
	 * cv.put(StrResource.COL_CONTENT, task.toJson().toString()); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * // db.insert(StrResource.REMOTE_TASK_TABLE_NAME, StrResource.COL_ID, //
	 * cv); return null; }
	 */

	/**
	 * Deletes a task from the "local" table of the database.
	 * 
	 * @param id
	 *            The id of the task to be deleted.
	 */
	public void delete_Local_Recipe(String id) {
		db.delete(StrResource.LOCAL_RECIPE_TABLE_NAME, "id" + " =?",
				new String[] { id, });
	}

	/**
	 * Deletes a task from the "local" table of the database.
	 * 
	 * @param id
	 *            The id of the task to be deleted.
	 */
	private void delete_Remote_Recipe(String id) {
		// db.delete(StrResource.REMOTE_TASK_TABLE_NAME, StrResource.COL_ID +
		// " =?", new String[]{id,});
	}

	/**
	 * Gets a task (if exists) from the "local" table of the database.
	 * 
	 * @param id
	 *            ID of task to search for
	 * @return Task found, if nothing found returns null.
	 * @throws JSONException
	 */
	/*
	 * public Recipe get_Local_Recipe(String id) { try { Cursor c =
	 * db.rawQuery("SELECT * FROM "+StrResource.LOCAL_RECIPE_TABLE_NAME, null);
	 * if (c == null || c.getCount() == 0) { return null; } else {
	 * c.moveToFirst(); String string = c.getString(1); JSONObject jsonObject =
	 * new JSONObject(); return new Recipe(); } } catch (Exception e) {
	 * e.printStackTrace(); } return null;
	 * 
	 * }
	 * 
	 * private JSONObject getJsonRecipe(String string) { try { return
	 * toJsonRecipe(string); } catch (JSONException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } return null; }
	 * 
	 * /** Converts json string into a task object and returns.
	 * 
	 * @param jsonTask , task object in json format.
	 * 
	 * @return Task
	 * 
	 * @throws JSONException
	 */
	private Recipe toRecipe(JSONObject j) throws JSONException {
		if (j == null) {
			return null;
		} else {
			return new Recipe(j.getString("id"), j.getString("user"),
					j.getString("name"), toIngredients(j),
					j.getString("directions"));
		}
	}

	/**
	 * Gets list of ingredients from jsonObject and returns
	 * 
	 * @param jsonRecipe
	 *            , task object in json format.
	 * @return List<Recipe>
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
	 * Convert String to json object
	 * @param String
	 * @return JSONObject
	 */
	private JSONObject toJsonRecipe(String recipeContent) throws JSONException {
		JSONObject jsonRecipe = new JSONObject(recipeContent);
		return jsonRecipe;
	}

	/**
	 * Gets a task (if exists) from the "remote" table of the database.
	 * 
	 * @param id
	 *            ID of task to search for
	 * @return Task found, if nothing found returns null.
	 * @throws JSONException
	 */
	/*
	 * public Recipe getRemoteRecipe(String id) { try { Cursor c =
	 * db.rawQuery("SELECT * FROM " + StrResource.REMOTE_TASK_TABLE_NAME +
	 * " WHERE " + "=?", new String[]{id,}); c.moveToFirst();
	 * if(c==null||c.getCount()==0) { return null; } else { String taskContent =
	 * "";//c.getString(c.getColumnIndex(StrResource.COL_CONTENT)); JSONObject
	 * jsonRecipe = toJsonRecipe(taskContent); return toRecipe(jsonRecipe); } }
	 * catch(JSONException e) { e.printStackTrace(); } return null; }
	 */

	/**
	 * Get the remote task list.
	 * 
	 * @return A list of tasks in the remote table of the database
	 * @throws JSONException
	 */
	// fixed
	/*
	 * public ArrayList<Recipe> getRemoteRecipeList() { try {
	 * Log.d("refresh","STARTING REMOTE TASK LIST"); ArrayList<Recipe> out = new
	 * ArrayList<Recipe>(); Cursor c =
	 * db.rawQuery("SELECT * FROM "+StrResource.REMOTE_TASK_TABLE_NAME, new
	 * String[]{}); if(c.moveToFirst()) { while(c.isAfterLast()==false) {
	 * JSONObject obj =
	 * toJsonTask(c.getString(c.getColumnIndex(StrResource.COL_CONTENT)));
	 * out.add(toTask(obj)); c.moveToNext(); } }
	 * Log.d("refresh","sizeof remotetasklist = "+out.size()); return out;
	 * 
	 * } catch(Exception e) { e.printStackTrace(); } return null; }
	 */

	/**
	 * Updates the database with the passed in task based on the id.
	 * 
	 * Looks through both local and remote tables for a matching task.
	 * 
	 * @param task
	 *            The task that you want changed
	 * @return
	 */
	public void updateRecipe(Recipe re) {
		try {
			ContentValues cv = new ContentValues();
			cv.put("id", re.getId());
			cv.put("content", re.toJson().toString());
			// cv.put(StrResource.COL_ID,task.getId());

			int n = db.delete(StrResource.LOCAL_RECIPE_TABLE_NAME, "id" + "=?",
					new String[] { re.getId(), });
			if (n == 1) {
				db.insert(StrResource.LOCAL_RECIPE_TABLE_NAME, null, cv);
			}/*
			 * else {int b = db.delete(StrResource.REMOTE_TASK_TABLE_NAME,
			 * StrResource.COL_ID + "=?", new String[]{task.getId(),}); if(b==1)
			 * { db.insert(StrResource.REMOTE_TASK_TABLE_NAME,
			 * StrResource.COL_ID, cv); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clear_Remote() {
		db.delete(StrResource.REMOTE_RECIPE_TABLE_NAME, null, null);
	}

	// fixed
	public void clear_All() {
		db.delete(StrResource.LOCAL_RECIPE_TABLE_NAME, null, null);
		db.delete(StrResource.REMOTE_RECIPE_TABLE_NAME, null, null);
	}

	// fixed
	public void clear_Local() {
		db.delete(StrResource.LOCAL_RECIPE_TABLE_NAME, null, null);
	}

	public void close() {
		db.close();
	}

	/*
	 * public void hideTask(String taskid) { Task task =
	 * this.getLocalTask(taskid); task.setStatus(5); ContentValues cv = new
	 * ContentValues(); cv.put(StrResource.COL_ID, task.getId()); try {
	 * cv.put(StrResource.COL_CONTENT, task.toJson().toString());
	 * db.delete(StrResource.LOCAL_TASK_TABLE_NAME, StrResource.COL_ID+"=?", new
	 * String[]{taskid,}); db.insert(StrResource.LOCAL_TASK_TABLE_NAME,
	 * StrResource.COL_ID, cv); } catch (JSONException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */
}
