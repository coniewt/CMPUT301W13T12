package ca.ualberta.c301w13t12recipes.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.model.Ingredient;

/**
 * Return an Adapter of ingredients for ListView
 *
 */
public class IngredientsAdapter {
	String[] from = new String[]{"name","amount"};
	int[] to = new int[] { R.id.textView_item_name,R.id.textView_item_amount};
	
	/**
	 * Get Adapter for ListView
	 * @param Context
	 * @param List<Ingredient> Array of ingredient
	 * @return ListAdapter - Adapter for ListView
	 */
	public ListAdapter getAdapter(Context ct,List<Ingredient> li){
		List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();		
		for(Ingredient in:li){
			 HashMap<String, String> map = new HashMap<String, String>();
			 map.put("name", in.getName());
			 map.put("amount", in.getAmount());
			 fillMaps.add(map);
		}
		return new SimpleAdapter(ct,fillMaps,R.layout.item_ingredient,from,to);
	}
	

}
