package ca.ualberta.c301w13t12recipes.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.IngredientsAdapter;
import ca.ualberta.c301w13t12recipes.model.Recipe;


/**
 * Activity class for adding ingredient wizard
 * @author GUANQI HUANG
 */
public class AddIngredWizardActivity extends Activity {
	private ImageButton addIngredButton;
	private Recipe recipe = new Recipe();
	private IngredientsAdapter adapter;
	private Button clearButton, nextButton;
	protected ListView ingredientsListView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_ingred_wizard);
		this.setupWidgets();
		this.getRecipeFromIntent();
		this.refreshList();

		ingredientsListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> listView, View view,
					int pos, long id) {
				// TODO long click to delete selected item and then remove
				Toast.makeText(AddIngredWizardActivity.this,
						recipe.getIngredientName(pos) + " is removed", 3)
						.show();
				recipe.removeIngredientByIndex(pos);
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
		clearButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				AlertDialog.Builder builder = new Builder(AddIngredWizardActivity.this); 
				builder.setTitle("Notification");
				builder.setPositiveButton("Continue",new android.content.DialogInterface.OnClickListener(){
					public void onClick(DialogInterface arg0, int arg1) {
						recipe.removeAllIngredient();
						refreshList();
						}
				}); 
				builder.setNegativeButton("Cancel", null);
					builder.setIcon(android.R.drawable.ic_dialog_info); 
					builder.setMessage("Are you sure to delete?"); 
				builder.show(); 
				}
		});
		// TODO Auto-generated method stub
		nextButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.v("Hello",""+recipe.getIngredients().size());
				if(recipe.getIngredients().size()==0)
					displayEmptyIngredientDialog();
				else
					saveAndJumpToAddImageWizard();
					
			}
		});
	}

	protected void setupWidgets() {
		addIngredButton = (ImageButton) findViewById(R.id.imgBtn_add_ingredient_button);
		nextButton = (Button) findViewById(R.id.add_step1_next_button);
		clearButton = (Button) findViewById(R.id.add_step1_Clear_button);
		ingredientsListView = (ListView) findViewById(R.id.listView_ingredients_list);
	}

	private void refreshList() {
		adapter = new IngredientsAdapter();
		ingredientsListView.setAdapter(adapter.getAdapter(this, recipe.getIngredients()));

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
			builder.setTitle("New Ingredient");
			inflater.inflate(R.layout.dialog_add_ingredient, null);
			builder.setPositiveButton("Done",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int id) {
							name = nameEditText.getText().toString();
							amount = amountEditText.getText().toString();
							recipe.addIngredient(name, amount);
							Toast.makeText(AddIngredWizardActivity.this,
									"Successfully adding a new ingredient", 2).show();
							refreshList();
						}
					}).setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							AddIngredDiaglogFragment.this.getDialog().cancel();

						}
					});
			return builder.create();
		}

	}

	private void saveAndJumpToAddImageWizard() {
		Toast.makeText(AddIngredWizardActivity.this, "Ingredients are saved !",
				3).show();
		Intent intent = new Intent(AddIngredWizardActivity.this,
				AddPicWizardActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("NEW_RECIPE", recipe);
		intent.putExtras(bundle);
		startActivity(intent);
		finish();
	}
	/**
	 * TODO display a dialog to notify user the empty ingredient list
	 */
	public void displayEmptyIngredientDialog(){
		AlertDialog.Builder builder = new Builder(AddIngredWizardActivity.this); 
		builder.setTitle("Warning"); 
		builder.setNegativeButton("Continue",new android.content.DialogInterface.OnClickListener(){
		public void onClick(DialogInterface arg0, int arg1) {
			saveAndJumpToAddImageWizard();
		}}); 
		builder.setPositiveButton("Back",new android.content.DialogInterface.OnClickListener(){
		public void onClick(DialogInterface arg0, int arg1) {
			
		}
		}); 
		builder.setIcon(android.R.drawable.ic_dialog_info); 
		builder.setMessage("No Ingredient ? "); 
	builder.show(); 
	}
}
