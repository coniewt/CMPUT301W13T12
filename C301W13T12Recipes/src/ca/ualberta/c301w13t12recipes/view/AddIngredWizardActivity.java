package ca.ualberta.c301w13t12recipes.view;


import java.util.Map;

import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.R.layout;
import ca.ualberta.c301w13t12recipes.R.menu;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.controller.IngredientsAdapter;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class AddIngredWizardActivity extends Activity {
	private ImageButton addIngredButton;
	private Recipe recipe;
	private IngredientsAdapter adapter;
	private ListView lv;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_add_ingred_wizard);
	    
	    this.setupListView();
	    this.setupButtons();
	    this.getRecipeFromIntent();
	    this.refreshList();
	  
	    lv.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> listView, View view,
					int pos, long id) {
				// TODO long click to delete selected item and then remove
				recipe.removeIngredient(pos);
				Toast.makeText(AddIngredWizardActivity.this,recipe.getIngredientName(pos), 1).show();
				refreshList();
				return false;
			}
	    	
	    });
	    
	    
	    // TODO Auto-generated method stub
	}
	private void setupButtons(){
		addIngredButton = (ImageButton)findViewById(R.id.imgBtn_add_ingredient_button);
		
	}
	private void setupListView(){
		
		 lv = (ListView)findViewById(R.id.listView_ingredients_list);
	}
	private void refreshList(){
		adapter = new IngredientsAdapter();
		lv.setAdapter(adapter.getAdapter(this,recipe.getIngredients()));
		
	}
	private void getRecipeFromIntent(){
		recipe = (Recipe)getIntent().getSerializableExtra("NEW_RECIPE");
		
	}

}
