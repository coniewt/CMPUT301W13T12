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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class AddIngredWizardActivity extends Activity {
	

	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_add_ingred_wizard);
	    
	    ListView lv = (ListView)findViewById(R.id.listView_ingredients_list);
	    
	    Recipe recipe =(Recipe)getIntent().getSerializableExtra("NEW_RECIPE");
	    IngredientsAdapter adapter = new IngredientsAdapter();
	    lv.setAdapter(adapter.getAdapter(this,recipe.getIngredients()));
	    
	    lv.setOnClickListener( new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Cursor cursor = (Cursor)lv.getItemAtPosition(position);
				
				
				
				
			}
	    	
	    });
	    
	    
	    // TODO Auto-generated method stub
	}

}
