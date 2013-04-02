package ca.ualberta.c301w13t12recipes.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SimpleAdapter;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.model.Ingredient;

/**
 * Adapter for fridge ListView
 *
 */
public class IngredientsFridgeAdapter {
	
	String[] from = new String[]{"name","amount","checked"};
	int[] to = new int[] { R.id.fridge_item_name,R.id.fridge_item_amount,R.id.fridge_item_checkbox};

	/**
	 * Get Adapter for listView
	 * @param Context
	 * @param List<Ingredient> Array of ingredient
	 * @return SimpleAdapter - Adapter for fridge ListView
	 */
	public SimpleAdapter getAdapter(Context ct,List<Ingredient> li){
		List<HashMap<String, Object>> fillMaps = new ArrayList<HashMap<String, Object>>();		
		for(Ingredient in:li){
			 HashMap<String, Object> map = new HashMap<String, Object>();
			 map.put("name", in.getName());
			 map.put("amount", in.getAmount());
			 map.put("checked",false);
			 fillMaps.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(ct,fillMaps,R.layout.item_ingredient_fridge,from,to){
			 public View getView(final int position, View convertView, ViewGroup parent) {
		          View view = super.getView(position, convertView, parent);  
		                @SuppressWarnings("unchecked")  
		                final HashMap<String,Object> map = (HashMap<String, Object>) this.getItem(position);  
		                CheckBox checkBox = (CheckBox)view.findViewById(R.id.fridge_item_checkbox);  
		                checkBox.setChecked((Boolean) map.get("checked"));  
		                checkBox.setOnClickListener(new View.OnClickListener() {  
		                  
		                    public void onClick(View view) {  
		                        map.put("checked", ((CheckBox)view).isChecked());
		                    }  
		                });  
		                return view;
		         }
		};
		return adapter;
	}
	
}
