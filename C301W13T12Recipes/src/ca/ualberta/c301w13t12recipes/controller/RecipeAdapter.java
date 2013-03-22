package ca.ualberta.c301w13t12recipes.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.model.Recipe;

/**
 * TODO return the adapter of recipe
 * 
 * @author YUWEI DUAN
 * 
 */
public class RecipeAdapter {
	String[] from = new String[] { "name" };
	int[] to = new int[] { R.id.item_recipe_name };

	/**
	 * @param ct
	 * @return ListAdapter
	 */
	public ListAdapter getAdapter(Context ct) {
		List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
		List<Recipe> li = (new DatabaseController(ct)).getDB()
				.getLocal_Recipe_List();
		// if database does not contain any row, getDB() will return null to li
		// we need if condition to check when li equals null or not
		if (li != null) {
			for (Recipe re : li) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("name", re.getName());
				fillMaps.add(map);
			}
		}
		return new SimpleAdapter(ct, fillMaps, R.layout.item_recipe, from, to);
	}
}
