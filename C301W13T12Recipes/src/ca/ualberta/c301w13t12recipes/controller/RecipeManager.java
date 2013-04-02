package ca.ualberta.c301w13t12recipes.controller;

import ca.ualberta.c301w13t12recipes.model.Recipe;
import android.content.Context;

/**
 * Recipe manage class, responsible for creating database controller and image manager instance
 *
 */
public class RecipeManager {
	private DatabaseController database;
	private ImageManager imageManager;

	public RecipeManager(Context context) {
		database = new DatabaseController(context);
		imageManager = new ImageManager();
	}
	
	/**
	 * remove all the info and pictures that relate to this recipe away from phone
	 * @param Recipe recipe
	 */
	public void deteleRecipe(Recipe recipe) {
		database.delete(recipe);
		for (int i = 0; i < recipe.getImage().size(); i++) {
			imageManager.removeImageFilesFromLocal(recipe.getImage(i));
		}
	}
}
