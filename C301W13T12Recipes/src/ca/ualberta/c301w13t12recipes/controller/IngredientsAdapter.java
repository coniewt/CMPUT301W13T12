package ca.ualberta.c301w13t12recipes.controller;


import java.util.ArrayList;
import java.util.Map;

import ca.ualberta.c301w13t12recipes.model.Ingredient;
import ca.ualberta.c301w13t12recipes.view.IngredientItemViewHolder;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import ca.ualberta.c301w13t12recipes.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class IngredientsAdapter extends BaseAdapter{
	
	private ArrayList<Ingredient> ingredientsList;
	private Map<Integer,Boolean> isCheckedMap;
	private Context context;
	LayoutInflater inflater;
	public IngredientsAdapter(Context context, ArrayList<Ingredient> ingredientsCollection){
		super();
		this.ingredientsList = ingredientsCollection;
		this.context = context;
		inflater = LayoutInflater.from(this.context);
	}
	@Override
	public int getCount() {
		return this.ingredientsList ==  null? 0: this.ingredientsList.size();
	}

	@Override
	public Object getItem(int location) {
		// TODO get ingredient at position
		return this.ingredientsList.get(location);
	}

	@Override
	public long getItemId(int position) {
		// TODO return position
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		IngredientItemViewHolder holder = null;
		Ingredient ingredient = ingredientsList.get(position);
		
		final int ingredientsListPos = position;
		final int id = ingredient.getId();
		
		if(convertView == null){
			holder = new IngredientItemViewHolder();
			convertView = inflater.inflate(R.layout.item_ingredient,null);
			
			holder.ingredientName = (TextView)convertView.findViewById(R.id.textView_item_name);
			holder.amount = (TextView)convertView.findViewById(R.id.textView_item_amount);
			holder.cBox =(CheckBox)convertView.findViewById(R.id.checkBox_item_ingredient);
			
			convertView.setTag(holder);
					
		}else{
			holder = (IngredientItemViewHolder)convertView.getTag();
		}
		// for debug
		Log.d("AddIngredWizardActivity","id="+id);
		holder.cBox.setChecked(isCheckedMap.get(id));
		holder.ingredientName.setText(ingredient.g);
		
		return null;
	}

}
