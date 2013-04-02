package ca.ualberta.c301w13t12recipes.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.content.Context;
<<<<<<< HEAD
import android.os.AsyncTask;
=======
import android.graphics.drawable.Drawable;
>>>>>>> 4462160f896a03675a6346de0994259b8f9af0f1
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.model.Ingredient;
import ca.ualberta.c301w13t12recipes.model.Recipe;

/**
 * TODO return the adapter of recipe
 * 
 * @author YUWEI DUAN
 * 
 */
public class RecipeAdapter {
	String[] from = new String[] { "name", "direction", "image" };
	int[] to = new int[] { R.id.item_recipe_name, R.id.item_recipe_direction,
			R.id.item_recipe_image };

	/**
	 * @param ct
	 * @param type
	 *            if type is "All" the adpter will return all recipe otherwise
	 *            it will return the recipe with keyword in type
	 * @return ListAdapter
	 */
<<<<<<< HEAD
	@SuppressWarnings("unchecked")
	public ListAdapter getAdapter(Context ct, String type,ArrayList<Ingredient> ar) {
		List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
=======
	public ListAdapter getAdapter(Context ct, String type,
			ArrayList<Ingredient> ar) {
		List<HashMap<String, Object>> fillMaps = new ArrayList<HashMap<String, Object>>();
>>>>>>> 4462160f896a03675a6346de0994259b8f9af0f1
		List<Recipe> li = new ArrayList<Recipe>();
		Log.v("Key", type);
		if (type.compareTo("All") == 0) {
			li = (new DatabaseController(ct)).getDB().getLocal_Recipe_List();
		} else if (type.length() > 4) {
			if ((type.substring(0, 4)).compareTo("web_") == 0) {
				try {
					li = (new WebSearch()).searchRecipes(
							type.substring(4, type.length()), ct);
					(new DatabaseController(ct)).postRemote(li);
				} catch (Exception e) {
					e.printStackTrace();
				}
<<<<<<< HEAD
			}else if (type.compareTo("INGREDIENT_") == 0) {
				try {
					//Log.v(ar.get(0).getAmount(), ar.get(0).getName());
					li= (List<Recipe>) new GetTask().execute(convertTo(ar));
					Log.v(">>>>>>>>>>>>>>", li.toString());
					//li = (new WebSearch()).searchRecipesByIngredient("*",convertTo(ar));
=======
			} else if ((type.substring(0, 11)).compareTo("INGREDIENT_") == 0) {
				try {
					// li = (new WebSearch()).searchRecipes();
>>>>>>> 4462160f896a03675a6346de0994259b8f9af0f1
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else
				li = (new DatabaseController(ct)).getDB()
						.searchRecipebyKeyword(type);
		} else
			li = (new DatabaseController(ct)).getDB().searchRecipebyKeyword(
					type);
		// Log.v("hello", li.get(0).getPassWord());
		// if database does not contain any row, getDB() will return null to li
		// we need if condition to check when li equals null or not
		if (li != null) {
			for (Recipe re : li) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("name", re.getName());
				map.put("direction", re.getDirections());
				if (re.getImage().size() != 0) {
					map.put("image", re.getImage(0).getTN_Path());
				} else {
					map.put("image",R.drawable.view_listview_no_photo);
				}
				fillMaps.add(map);
			}
		}
		return new SimpleAdapter(ct, fillMaps, R.layout.item_recipe, from, to);
	}
}
