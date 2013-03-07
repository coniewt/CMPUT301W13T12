package ca.ualberta.c301w13t12recipes.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *	Opens/updates our local SQLite Database
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "Ingredients_DB";
	private static final int DATABASE_VERSION = 4;
	private static final String LOCAL_RECIPE_TABLE_CREATE =
			"CREATE TABLE IF NOT EXISTS recipe (id TEXT PRIMARY KEY, name TEXT,user TEXT,directions TEXT)" ;
	private static final String LOCAL_INGREDIENTS_TABLE_CREATE =
			"CREATE TABLE IF NOT EXISTS ingredients(id TEXT,I_name TEXT,amount TEXT)" ;
	//private static final String REMOTE_TASK_TABLE_CREATE ="CREATE TABLE " ;

	public DatabaseOpenHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(LOCAL_INGREDIENTS_TABLE_CREATE);
		db.execSQL(LOCAL_RECIPE_TABLE_CREATE);
		//db.execSQL(REMOTE_TASK_TABLE_CREATE);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		if(oldVersion < 4)
		{
			final String DROP_RECIPE_TABLE =
					"DROP TABLE recipe;";
			final String DROP_INGREDIENT_TABLE =
					"DROP TABLE ingre;";
			db.execSQL(DROP_RECIPE_TABLE);
			db.execSQL(DROP_INGREDIENT_TABLE);
			//db.execSQL(LOCAL_TASK_TABLE_CREATE);
			//db.execSQL(REMOTE_TASK_TABLE_CREATE);		
		}
	}

}
