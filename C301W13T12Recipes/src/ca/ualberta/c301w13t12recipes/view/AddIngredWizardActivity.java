package ca.ualberta.c301w13t12recipes.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.IngredientsAdapter;
import ca.ualberta.c301w13t12recipes.model.Recipe;

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
		this.setupWidgets();
		this.getRecipeFromIntent();
		this.refreshList();

		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> listView, View view,
					int pos, long id) {
				// TODO long click to delete selected item and then remove
				Toast.makeText(AddIngredWizardActivity.this,
						recipe.getIngredientName(pos) + " is removed", 3)
						.show();
				recipe.removeIngredient(pos);
				refreshList();

				return false;
			}

		});

		addIngredButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				DialogFragment newFragment = new AddIngredDiaglogFragment();
				newFragment.show(getFragmentManager(), "NEW_INGREDIENT");

			}

		});

		// TODO Auto-generated method stub
	}

	private void setupWidgets() {
		addIngredButton = (ImageButton) findViewById(R.id.imgBtn_add_ingredient_button);

	}

	private void setupListView() {

		lv = (ListView) findViewById(R.id.listView_ingredients_list);
	}

	private void refreshList() {
		adapter = new IngredientsAdapter();
		lv.setAdapter(adapter.getAdapter(this, recipe.getIngredients()));

	}

	private void getRecipeFromIntent() {
		recipe = (Recipe) getIntent().getSerializableExtra("NEW_RECIPE");

	}

	class AddIngredDiaglogFragment extends DialogFragment {
		private EditText nameEditText;
		private EditText amountEditText;
		String name, amount;

		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			LayoutInflater inflater = getActivity().getLayoutInflater();
			final View v = inflater.inflate(R.layout.dialog_add_ingredient,
					null);
			builder.setView(v);
			nameEditText = (EditText) v
					.findViewById(R.id.dialog_add_editText_name);
			amountEditText = (EditText) v
					.findViewById(R.id.dialog_editText_add_amount);
			restore();
			builder.setTitle("New Ingredient");
			inflater.inflate(R.layout.dialog_add_ingredient, null);
			builder.setPositiveButton("Done",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									name = nameEditText.getText().toString();
									amount = amountEditText.getText().toString();
									recipe.addIngredient(name, amount);
									saveOnDialog();
									Toast.makeText(
											AddIngredWizardActivity.this,
											name + " is added", 1).show();
									refreshList();
								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									AddIngredDiaglogFragment.this.getDialog()
											.cancel();

								}
							});
			return builder.create();
		}
		 public void onPause(){
		    	Log.v("AddingActivity", "onpause!!!");
		    	saveOnDialog();
		    	super.onPause();
		    }
		    public void onStop(){
		    	Log.v("AddingAcitivityActivity", "onStop!!!");
		    	saveOnDialog();
		    	super.onStop();
		    }
		public void saveOnDialog(){
	    	SharedPreferences settings = getSharedPreferences("Setting", 0);
	        SharedPreferences.Editor editor = settings.edit();
	        editor.putString("edt", nameEditText.getText().toString());
	        editor.putString("ctc", amountEditText.getText().toString());
	        editor.commit();
	    }
		
		 public void restore(){
		    	SharedPreferences settings = getSharedPreferences("Setting", 0);
		        String edtS = settings.getString("edt","null");
		        String ctcS = settings.getString("ctc","null");
		        if(edtS.compareTo("null")!=0){
		        	nameEditText.setText(edtS);
		    	}
		    	if(ctcS.compareTo("null")!=0){
		    		amountEditText.setText(ctcS);
		    	}
		    	}
	}
	

}
