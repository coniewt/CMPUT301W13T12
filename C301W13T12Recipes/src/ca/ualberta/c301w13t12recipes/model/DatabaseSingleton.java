package ca.ualberta.c301w13t12recipes.model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 *  Singleton class to make sure only one instance of SQLiteDatabase is opened
 *  during the life of the application
 */
public class DatabaseSingleton
{
	private static volatile DatabaseSingleton instance = null;
	private SQLiteDatabase db;
	
	/**
	 * Returns instance of DatabaseSingleton class
	 * @param context
	 * @return instance
	 */
	public static DatabaseSingleton getInstance(Context context)
	{
		if(instance==null)
		{
			synchronized(DatabaseSingleton.class)
			{
				if(instance==null)
				{
					instance = new DatabaseSingleton(context.getApplicationContext());
				}
			}
		}
		return instance;
	}
	/**
	 * @param context
	 */
	public DatabaseSingleton(Context context)
	{
		db = new DatabaseOpenHelper(context).getWritableDatabase();
	}
	/**
	 * @return SQLiteDatabase
	 */
	public SQLiteDatabase getDB(){
		return this.db;
	}
}
