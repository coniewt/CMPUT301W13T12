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
 * @author YUWEI DUAN
 *
 */
public class RecipeAdapter {
	String[] from = new String[]{"name"};
	int[] to = new int[] { R.id.item_recipe_name};
	/**
	 * @param ct
	 * @return ListAdapter
	 */
	public ListAdapter getAdapter(Context ct)
	{
		List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();		
		List<Recipe> li =(new DatabaseController(ct)).getDB().getLocal_Recipe_List();
		HashMap<String, String> map = new HashMap<String, String>();;
		for(Recipe re:li){
			Log.v("Hello",re.getName());
			map.put("name,",re.getName());
			Log.v("map:",map.get("name").toString());
			fillMaps.add(map);
		}
		return new SimpleAdapter(ct,fillMaps,R.layout.item_recipe,from,to);
	}
}
