package ca.ualberta.c301w13t12recipes.model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 *  Singleton so we only have one instance of our SQLiteDatabase open
 *  during the life of our application
 */
public class DatabaseSingleton
{
	private static volatile DatabaseSingleton instance = null;
	private SQLiteDatabase db;
	
	/**
	 * Returns instance of DatabaseSingleton class
	 * @param context
	 * @return
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
