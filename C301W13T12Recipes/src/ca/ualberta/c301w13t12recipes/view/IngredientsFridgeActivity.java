package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class IngredientsFridgeActivity extends AddIngredWizardActivity{
	private DatabaseController controller;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fridge);
		this.setupWidgets();
		//this.refreshList();
		
	
	}
	protected void setupWidgets(){
		ingredientsListView = (ListView)findViewById(R.id.fridge_listView);
	}
	private void getFridgeIngredientsFromDB(){
		//controller.
	}
}
