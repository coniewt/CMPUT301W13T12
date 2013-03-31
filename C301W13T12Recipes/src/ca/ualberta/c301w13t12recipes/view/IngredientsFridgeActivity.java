package ca.ualberta.c301w13t12recipes.view;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.controller.IngredientsAdapter;
import ca.ualberta.c301w13t12recipes.model.Ingredient;

/**
 * @author HUANG GUANQI
 *
 */
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
		ArrayList<Ingredient> li = controller.getIngredListFromIngredDB();
		if(li != null){
		ingredientsListView.setAdapter(adapter.getAdapter(this, li));
		}
	}
	
}
