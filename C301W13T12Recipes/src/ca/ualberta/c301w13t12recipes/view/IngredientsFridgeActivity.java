package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.controller.IngredientsAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class IngredientsFridgeActivity extends AddIngredWizardActivity{
	
	private DatabaseController controller;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_fridge);
		controller = new DatabaseController(getApplicationContext());
		this.setupWidgets();
		this.refreshList();
		
		
		ingredientsListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> listView, View view,
					int pos, long id) {
				// TODO long click to delete selected item and then remove
				
				Toast.makeText(IngredientsFridgeActivity.this,
						 " is removed", 3)
						.show();
				controller.removeIngredFromIngredDB(pos);
				refreshList();
				return false;
			}

		});
	}
	

	protected void setupWidgets(){
		ingredientsListView = (ListView)findViewById(R.id.fridge_listView);
	}
	
	protected void refreshList() {
		adapter = new IngredientsAdapter();
		if(controller.getIngredListFromIngredDB() != null){
		ingredientsListView.setAdapter(adapter.getAdapter(this, controller.getIngredListFromIngredDB()));
		}
	}
	
}
