package ca.ualberta.c301w13t12recipes.view;


import java.util.Map;

import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.R.layout;
import ca.ualberta.c301w13t12recipes.R.menu;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.controller.IngredientsAdapter;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Activity class for adding ingredient wizard
 *
 */
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
				Toast.makeText(AddIngredWizardActivity.this,recipe.getIngredientName(pos) + " is removed", 1).show();
				refreshList();
				return false;
			}
	    	
	    });
	    
	    addIngredButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				DialogFragment newFragment = new AddIngredDiaglogFragment();
			    newFragment.show(getFragmentManager(), "NEW_INGREDIENT");
			    
				
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
class AddIngredDiaglogFragment extends DialogFragment {

	public Dialog onCreateDialog(Bundle savedInstanceState) {
	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	LayoutInflater inflater = getActivity().getLayoutInflater();
	builder.setTitle("New Ingredient");
	builder.setView(inflater.inflate(R.layout.dialog_add_ingredient,null)).setPositiveButton("Done", new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int id) {
			// TODO Auto-generated method stub
			
		}
	}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			AddIngredDiaglogFragment.this.getDialog().cancel();
			
		}
	});
	return builder.create();
	}
}

