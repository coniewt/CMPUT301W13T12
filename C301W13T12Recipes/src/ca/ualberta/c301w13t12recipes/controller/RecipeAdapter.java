package ca.ualberta.c301w13t12recipes.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.content.Context;
import android.os.AsyncTask;
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
	@SuppressWarnings("unchecked")
	public ListAdapter getAdapter(Context ct, String type,ArrayList<Ingredient> ar) {
		List<HashMap<String, Object>> fillMaps = new ArrayList<HashMap<String, Object>>();
		List<Recipe> li = new ArrayList<Recipe>();
		Log.v("Key", ""+type.compareTo("INGREDIENT_"));
		if (type.compareTo("All") == 0) {
			li = (new DatabaseController(ct)).getDB().getLocal_Recipe_List();
		} else if (type.length() > 4) {
			if ((type.substring(0, 4)).compareTo("web_") == 0) {
				try {
					li = (new DatabaseController(ct)).getDB().getLocal_Recipe_List();
							//(new WebSearch()).searchRecipes(type.substring(
							//4, type.length()),ct);
						//(new DatabaseController(ct)).postRemote(li);
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			}else if (type.compareTo("INGREDIENT_") == 0) {
				try {
					li= (List<Recipe>) new GetTask().execute(convertTo(ar));
					Log.v(">>>>>>>>>>>>>>", li.toString());

					//li = (new WebSearch()).searchRecipesByIngredient("*",convertTo(ar));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else{
				li = (new DatabaseController(ct)).getDB().searchRecipebyKeyword(
						type);
				}
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
				//map.put("image", R.drawable.view_listview_no_photo);
					map.put("image",R.drawable.view_listview_no_photo);
 				}

				fillMaps.add(map);
			}
		}
		return new SimpleAdapter(ct, fillMaps, R.layout.item_recipe, from, to);
	}
	private ArrayList<String> convertTo(ArrayList<Ingredient> in)
	{
		ArrayList<String> out = new ArrayList<String>();
		for(Ingredient ing:in){
			out.add(ing.getName());
		}
		return out;
	}
}
class GetTask extends AsyncTask<ArrayList<String>, Integer, ArrayList<Recipe>> {
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected ArrayList<Recipe> doInBackground(ArrayList<String>... params) {
		WebSearch search = new WebSearch();
		return search.searchRecipesByIngredient("*", params[0]);
	}

	@Override
	protected void onProgressUpdate(Integer... progress) {
		super.onProgressUpdate(progress);
	}
}
