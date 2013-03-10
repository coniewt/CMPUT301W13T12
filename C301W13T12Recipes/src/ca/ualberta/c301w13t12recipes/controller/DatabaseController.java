package ca.ualberta.c301w13t12recipes.controller;

import android.content.Context;
import ca.ualberta.c301w13t12recipes.model.LocalDB;


public class DatabaseController {
	LocalDB database;
	public DatabaseController(Context context){
		this.database = new LocalDB(context);
		
	}
}
