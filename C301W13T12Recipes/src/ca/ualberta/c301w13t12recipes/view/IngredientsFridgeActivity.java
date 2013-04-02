package ca.ualberta.c301w13t12recipes.view;

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;

import ca.ualberta.c301w13t12recipes.controller.IngredientsFridgeAdapter;
import ca.ualberta.c301w13t12recipes.model.Ingredient;

import ca.ualberta.c301w13t12recipes.model.StrResource;

/**
 * 
 *  Finding online recipes by using ingredients that user provided.
 *  
 * @author HUANG GUANQI
 * 
 */
public class IngredientsFridgeActivity extends Activity {

	private DatabaseController controller;
	private ListView ingredientsListView;
	private Button addButton;
	private Button clearButton;
	private Button searchButton;
	private EditText nameEditText;
	private EditText amountEditText;
	private IngredientsFridgeAdapter adapter;
	private ArrayList<Ingredient> li = new ArrayList<Ingredient>();
	private ArrayList<Ingredient> selected_list = new ArrayList<Ingredient>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fridge);
		controller = new DatabaseController(getApplicationContext());
		this.setupWidgets();
		this.refreshList();

		ingredientsListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long arg3) {
				// TODO Auto-generated method stub

			}

		});

		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogFragment newFragment = new AddIngredDiaglogFragment();
				newFragment.show(getFragmentManager(), "NEW_INGREDIENT");

			}
		});
		searchButton.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unchecked")
			public void onClick(View v) {
				Map<String, Object> map = null;
				boolean isChecked;
				int count = ingredientsListView.getCount();
				for (int i = 0; i < count; i++) {
					map = (Map<String, Object>) ingredientsListView
							.getItemAtPosition(i);
					isChecked = (Boolean) map.get("checked");
					if (isChecked) {
						selected_list.add(controller
								.getIngredListFromIngredDB().get(i));
					}
				}
				if (selected_list.size() == 0) {
					Toast.makeText(getApplicationContext(), "You must select at least one ingredient", Toast.LENGTH_SHORT).show();
				} else {
					jumpToSearchIngredientResultActivity(selected_list);
				}
			}

		});
		clearButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {	
				displayClearFieldsWarningDialog();
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void removeSelectedItems() {
		Map<String, Object> map = null;
		boolean isChecked;
		for (int i = ingredientsListView.getCount() - 1; i >= 0; i--) {
			map = (Map<String, Object>) ingredientsListView
					.getItemAtPosition(i);
			isChecked = (Boolean) map.get("checked");
			if (isChecked) {

				Ingredient ingredient = controller.getIngredListFromIngredDB()
						.get(i);
				Toast.makeText(IngredientsFridgeActivity.this,
						ingredient.getName() + "is removed", Toast.LENGTH_SHORT)
						.show();
				controller.removeIngredFromIngredDB(ingredient);

			}
		}
		refreshList();

	}
	/**
	 *  Initialize all the widget objects
	 */
	protected void setupWidgets() {
		ingredientsListView = (ListView) findViewById(R.id.fridge_listView);
		addButton = (Button) findViewById(R.id.fridge_add_button);
		clearButton = (Button) findViewById(R.id.fridge_clear_button);
		searchButton = (Button) findViewById(R.id.fridge_search_button);
	}
	/**
	 * Generate an adapter from an arrayList of ingredients, then set it to our listView
	 * 
	 */
	protected void refreshList() {
		adapter = new IngredientsFridgeAdapter();
		li = controller.getIngredListFromIngredDB();
		ingredientsListView.setAdapter(adapter.getAdapter(this, li));
	}
	/**
	 *  Use AlerDialog builder to create a dialog that is able to
	 *  warn the user while they are attempting to delete any selected items
	 *  from the listView
	 */
	public void displayClearFieldsWarningDialog() {
		AlertDialog.Builder builder = new Builder(
				IngredientsFridgeActivity.this);
		builder.setTitle("Warning");
		builder.setNegativeButton("Cancel",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {

					}
				});
		builder.setPositiveButton("Continue",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						removeSelectedItems();
						Toast toast = Toast.makeText(getApplicationContext(),
								"Operation Complete", 1);
						toast.show();
					}
				});
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setMessage("Are you sure you want to delete selected items?");
		builder.show();
	}
	/**
	 * Jump to SearchIngredientResultActivity
	 * 
	 * @param index
	 */
	private void jumpToSearchIngredientResultActivity(ArrayList<Ingredient> ar) {
		Intent intent = new Intent(IngredientsFridgeActivity.this,
				SearchIngredientResultActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(StrResource.INTENT_INGREDIENT_LIST_KEY, ar);
		intent.putExtras(bundle);
		startActivity(intent);
		// finish();
	}
	/**
	 *  A Class for dialog fragment that can construct a 
	 * @author GUANQI HUANG
	 *
	 */
	class AddIngredDiaglogFragment extends DialogFragment {
		
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
							checkAllFields(name, amount);
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
	/**
	 * Check both name EditText and amount EditText, if user add string into both
	 * widgets, the method will grant the user to add a new ingredient
	 * @param name
	 * @param amount
	 */
	private void checkAllFields(String name,String amount) {
		if (amount.equals("") || name.equals("")) {
			Toast.makeText(
					getApplicationContext(),
					"Sorry! You must fill both name and direction input field.",
					Toast.LENGTH_SHORT).show();
		}else{
			controller.addIngredFromIngredDB(new Ingredient(
					name, amount));
			Toast.makeText(IngredientsFridgeActivity.this,
					"Successfully adding a new ingredient", 2)
					.show();
		}
	}
}
