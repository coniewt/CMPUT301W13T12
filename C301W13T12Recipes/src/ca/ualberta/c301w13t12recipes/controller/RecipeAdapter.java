package ca.ualberta.c301w13t12recipes.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

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
	 * @param type
	 *            if type is "All" the adpter will return all recipe otherwise
	 *            it will return the recipe with keyword in type
	 * @return ListAdapter
	 */
	public ListAdapter getAdapter(Context ct, String type) {
		List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
		List<Recipe> li = new ArrayList<Recipe>();
		Log.v("Key", type);
		if (type.compareTo("All") == 0) {
			li = (new DatabaseController(ct)).getDB().getLocal_Recipe_List();
		} else if (type.length() > 4) {
			if ((type.substring(0, 4)).compareTo("web_") == 0) {
				try {
					li = (new WebSearch()).searchRecipes(type.substring(
							4, type.length()),ct);
						(new DatabaseController(ct)).postRemote(li);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else
				li = (new DatabaseController(ct)).getDB().searchRecipebyKeyword(
						type);
		} else
			li = (new DatabaseController(ct)).getDB().searchRecipebyKeyword(
					type);
		// Log.v("hello", li.get(0).getPassWord());
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
