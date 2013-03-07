package ca.ualberta.c301w13t12recipes.model;

import java.text.ParseException;
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
import android.util.Log;

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
	 * @param task The task to be added.
	 * @return The task that was added along with it's id.
	 */
	private String getUniqueId()
	{
		String id  = "";
		do {
			id = "local@" + UUID.randomUUID().toString();
		} while (this.localIdExists(id));
		return id;
	}

	private void addRecipe_LocaleTable(Recipe re)
	{
		ContentValues cv = new ContentValues();
		//cv.put(StrResource.COL_ID, re.getId());
		try
		{
			//cv.put(StrResource.COL_CONTENT, Recipe.toJson().toString() );
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		db.insert(StrResource.LOCAL_TASK_TABLE_NAME, "id", cv);
	}

	private boolean localIdExists(String id) {
		Cursor c = db.rawQuery("SELECT * FROM "+ StrResource.LOCAL_TASK_TABLE_NAME+" WHERE id"+"=?", new String[]{id,});
		if(c==null||c.getCount()==0)
		{
			return false;
		}
		return true;
	}

	/**
	 * Post a task to the "remote" table of the database.
	 *
	 * @param task The task to be added.
	 * @return The task that was added along with it's id.
	 */
	public Recipe postRemote(Recipe re) {
		ContentValues cv = new ContentValues();
		//cv.put(StrResource.COL_ID, task.getId());
		try
		{
			//cv.put(StrResource.COL_CONTENT, task.toJson().toString());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//db.insert(StrResource.REMOTE_TASK_TABLE_NAME, StrResource.COL_ID, cv);
		return null;
	}

	/**
	 * Deletes a task from the "local" table of the database.
	 *
	 * @param id The id of the task to be deleted.
	 */
	public void delete_Local_Recipe(String id) {
		//db.delete(StrResource.LOCAL_TASK_TABLE_NAME, StrResource.COL_ID + " =?", new String[]{id,});
	}

	/**
	 * Deletes a task from the "local" table of the database.
	 *
	 * @param id The id of the task to be deleted.
	 */
	@SuppressWarnings("unused")
	// fixed
	private void delete_Remote_Recipe(String id) {
		//db.delete(StrResource.REMOTE_TASK_TABLE_NAME, StrResource.COL_ID + " =?", new String[]{id,});
	}

	/**
	 * Gets a task (if exists) from the "local" table of the database.
	 *
	 * @param id ID of task to search for
	 * @return Task found, if nothing found returns null.
	 * @throws JSONException 
	 */
	public Recipe get_Local_Recipe(String id) {
		try
		{
			Cursor c = db.rawQuery("SELECT * FROM ", null);
			if(c==null||c.getCount()==0)
			{ 
				return null;
			}
			else
			{
				c.moveToFirst();
				//String string = c.getString(c.getColumnIndex(StrResource.COL_CONTENT));

				return new Recipe();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;

	}

	private JSONObject getJsonRecipe(String string)
	{
		try
		{
			return toJsonRecipe(string);
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Converts json string into a task object and returns.
	 * @param jsonTask , task object in json format.
	 * @return Task
	 * @throws JSONException
	 */
	private static Recipe toTask(JSONObject jsonTask) throws JSONException
	{
		if(jsonTask==null)
		{
			return null;
		}
		else
		{
			return new Recipe();
		}
	}

	/**
	 * Gets list of responses from jsonObject and returns
	 * @param jsonTask , task object in json format.
	 * @return List<Response>
	 * @throws JSONException
	 */
	private static List<Ingredient> toResponses(JSONObject jsonTask) throws JSONException
	{
		try
		{
			JSONArray jsonArray = jsonTask.getJSONArray("responses");
			List<Ingredient> responses = new ArrayList<Ingredient>();

			//ResponseFactory respFactory = getRespFactory(jsonTask.getString("type"));

			for(int i = 0; i < jsonArray.length(); i++)
			{
				//Response resp = respFactory.createResponse(jsonArray.getJSONObject(i).getString("annotation"), jsonArray.getJSONObject(i).getString("content"));
				//resp.setTimestamp(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(jsonArray.getJSONObject(i).getString("timestamp")));
				//responses.add(resp);			
			}	
			return responses;
		}
		catch(Exception e)
		{
			System.err.println("Could not parse date");
			e.printStackTrace();
		}
		return null;
	}
/*
	private static ResponseFactory getRespFactory(String type)
	{
		if(type.equals(TextResponse.class.toString())){
			return new TextResponseFactory();
		} else if (type.equals(PictureResponse.class.toString())) {
			return  new PictureResponseFactory();
		} else {
			throw new UnsupportedOperationException("Not implemented");
		}
	}*/

	private JSONObject toJsonRecipe(String taskContent) throws JSONException
	{
		JSONObject jsonRecipe = new JSONObject(taskContent);
		return jsonRecipe;
	}

	/**
	 * Get the local task list.
	 *
	 * @return A list of tasks in the local table of the database
	 * @throws JSONException 
	 */
	public ArrayList<Recipe> getLocalTaskList()  {
		try
		{
			ArrayList<Recipe> out = new ArrayList<Recipe>();

			Cursor c = db.rawQuery("SELECT * FROM ", new String[]{});
			if(c.moveToFirst())
			{
				while(c.isAfterLast()==false)
				{
					JSONObject obj = null;
					//toJsonTask(c.getString(c.getColumnIndex(StrResource.COL_CONTENT)));
					out.add(toTask(obj));
					c.moveToNext();
				}
				return out;
			}
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets a task (if exists) from the "remote" table of the database.
	 *
	 * @param id ID of task to search for
	 * @return Task found, if nothing found returns null.
	 * @throws JSONException 
	 */
	public Recipe getRemoteRecipe(String id)  {
		try
		{
			Cursor c = db.rawQuery("SELECT * FROM " + StrResource.REMOTE_TASK_TABLE_NAME + " WHERE "  + "=?", new String[]{id,});
			c.moveToFirst();
			if(c==null||c.getCount()==0)
			{ 
				return null;
			}
			else
			{
				String taskContent = "";//c.getString(c.getColumnIndex(StrResource.COL_CONTENT));
				JSONObject jsonRecipe = toJsonRecipe(taskContent);
				return toTask(jsonRecipe);
			}
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get the remote task list.
	 *
	 * @return A list of tasks in the remote table of the database
	 * @throws JSONException 
	 */
	//fixed
	public ArrayList<Recipe> getRemoteRecipeList()  {
		try
		{
			Log.d("refresh","STARTING REMOTE TASK LIST");
			ArrayList<Recipe> out = new ArrayList<Recipe>();
			Cursor c = db.rawQuery("SELECT * FROM "+StrResource.REMOTE_TASK_TABLE_NAME, new String[]{});
			if(c.moveToFirst())
			{
				while(c.isAfterLast()==false)
				{
					/*JSONObject obj = toJsonTask(c.getString(c.getColumnIndex(StrResource.COL_CONTENT)));
					out.add(toTask(obj));
					c.moveToNext();*/
				}
			}
			Log.d("refresh","sizeof remotetasklist = "+out.size());
			return out;

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Updates the database with the passed in task based on the id.
	 *
	 * Looks through both local and remote tables for a matching task.
	 *
	 * @param task The task that you want changed
	 * @return
	 */
	public void updateTask(Recipe re)
	{	
		//try {
			//ContentValues cv = new ContentValues();
			//cv.put(StrResource.COL_CONTENT, task.toJson().toString());	
			//cv.put(StrResource.COL_ID,task.getId());
			/*int n = db.delete(StrResource.LOCAL_TASK_TABLE_NAME, StrResource.COL_ID + "=?", new String[]{task.getId(),});
			if(n==1)
			{
				db.insert(StrResource.LOCAL_TASK_TABLE_NAME, StrResource.COL_ID, cv);;
			}
			int b = db.delete(StrResource.REMOTE_TASK_TABLE_NAME, StrResource.COL_ID + "=?", new String[]{task.getId(),});
			if(b==1)
			{
				db.insert(StrResource.REMOTE_TASK_TABLE_NAME, StrResource.COL_ID, cv);
			}


		} catch (JSONException e) {
			e.printStackTrace();
		}*/
	}

	public void clear_Remote() {
		db.delete(StrResource.REMOTE_TASK_TABLE_NAME, null, null);
	}

	//fixed
	public void clear_All() {
		db.delete(StrResource.LOCAL_TASK_TABLE_NAME, null, null);
		db.delete(StrResource.REMOTE_TASK_TABLE_NAME, null, null);
	}

	//fixed
	public void clear_Local() {
		db.delete(StrResource.LOCAL_TASK_TABLE_NAME, null, null);
	}

	public void close()
	{
		db.close();
	}

	/*public void hideTask(String taskid)
	{
		Task task = this.getLocalTask(taskid);
		task.setStatus(5);
		ContentValues cv = new ContentValues();
		cv.put(StrResource.COL_ID, task.getId());
		try
		{
			cv.put(StrResource.COL_CONTENT, task.toJson().toString());
			db.delete(StrResource.LOCAL_TASK_TABLE_NAME, StrResource.COL_ID+"=?", new String[]{taskid,});
			db.insert(StrResource.LOCAL_TASK_TABLE_NAME, StrResource.COL_ID, cv);			
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
