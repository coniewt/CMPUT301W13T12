package ca.ualberta.c301w13t12recipes.controller;

import java.util.ArrayList;

import ca.ualberta.c301w13t12recipes.model.Ingredient;
import ca.ualberta.c301w13t12recipes.view.IngredientItemViewHolder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class IngredientsAdapter extends BaseAdapter{
	private ArrayList<Ingredient> ingredientsCollection;
	private Context context;
	LayoutInflater inflater;
	public IngredientsAdapter(Context context, ArrayList<Ingredient> ingredientsCollection){
		super();
		this.ingredientsCollection = ingredientsCollection;
		this.context = context;
		inflater = LayoutInflater.from(this.context);
	}
	@Override
	public int getCount() {
		return this.ingredientsCollection ==  null? 0: this.ingredientsCollection.size();
	}

	@Override
	public Object getItem(int location) {
		// TODO get ingredient at position
		return this.ingredientsCollection.get(location);
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
		
		
		return null;
	}

}
