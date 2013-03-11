package ca.ualberta.c301w13t12recipes.controller;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
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
	/*public String getUUID(){
		return this.database.getUniqueId();
	}*/
	/*
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeValue(database);
	}
	
	public static final Parcelable.Creator<DatabaseController> CREATOR = new Creator<DatabaseController>(){
		//TODO create a new DatabaseController object and then change 
		//its attributes to one that just passed in
		public DatabaseController createFromParcel(Parcel source){
			DatabaseController controller = new DatabaseController(null);
			controller.database = (LocalDB) source.readValue(LocalDB.class.getClassLoader());
			return controller;
		}
		public DatabaseController[] newArray(int size){
			// TODO create an array of DatabaseController objects
			return new DatabaseController[size];
		}
		
		
	};
	*/

}
