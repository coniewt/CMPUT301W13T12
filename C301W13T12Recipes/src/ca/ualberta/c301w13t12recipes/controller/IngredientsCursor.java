package ca.ualberta.c301w13t12recipes.controller;

import java.util.ArrayList;

import android.widget.SimpleCursorAdapter;

import ca.ualberta.c301w13t12recipes.model.Ingredient;

public class IngredientsCursor {
	private ArrayList<Ingredient> ingredientsCollection;
	private SimpleCursorAdapter cursorAdapter;
	public IngredientsCursor(){
		
		
		
	}
	public void addIngredient(String ingredient, String amount){
		
		this.ingredientsCollection.add(new Ingredient(ingredient, amount));
		
	}
	public void removeIngredient(){
		
	}
}
