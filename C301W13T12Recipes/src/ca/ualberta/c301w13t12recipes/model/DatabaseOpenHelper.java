package ca.ualberta.c301w13t12recipes.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *	Opens/updates local SQLite Database 
 *	
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "Ingredients_DB";
	private static final int DATABASE_VERSION = 4;
	private static final String LOCAL_RECIPE_TABLE_CREATE =
			"CREATE TABLE IF NOT EXISTS "+
	StrResource.LOCAL_RECIPE_TABLE_NAME+" (id TEXT PRIMARY KEY, Content TEXT)" ;
	private static final String REMOTE_RECIPE_TABLE_CREATE ="CREATE TABLE IF NOT EXISTS "+
			StrResource.REMOTE_RECIPE_TABLE_NAME+" (id TEXT PRIMARY KEY, Content TEXT)";
	private static final String LOCAL_INGREDIENT_TABLE_CREATE ="CREATE TABLE IF NOT EXISTS "+
			StrResource.LOCAL_INGREDIENT_TABLE_NAME+" (id TEXT PRIMARY KEY, name TEXT,amount TEXT)";
	/**
	 * This is extended constructor from the super class
	 * SQLiteOpenHelper
	 * @param context
	 */
	public DatabaseOpenHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**
	 * Override onCreate method
	 */
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(LOCAL_INGREDIENT_TABLE_CREATE);
		db.execSQL(LOCAL_RECIPE_TABLE_CREATE);
		db.execSQL(REMOTE_RECIPE_TABLE_CREATE);
	}
	
	/**
	 * Override onUpgrade method
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		if(oldVersion < 4)
		{
			final String DROP_LOCAL_RECIPE_TABLE =
					"DROP TABLE local_recipe_table;";
			final String DROP_REMOTE_RECIPE_TABLE =
					"DROP TABLE "+StrResource.REMOTE_RECIPE_TABLE_NAME;
			final String DROP_INGREDIENT_TABLE =
					"DROP TABLE "+StrResource.LOCAL_INGREDIENT_TABLE_NAME;
			db.execSQL(DROP_LOCAL_RECIPE_TABLE);
			db.execSQL(DROP_REMOTE_RECIPE_TABLE);
			db.execSQL(DROP_INGREDIENT_TABLE);
		}
	}
}
