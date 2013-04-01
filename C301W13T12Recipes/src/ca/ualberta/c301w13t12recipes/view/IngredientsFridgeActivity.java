package ca.ualberta.c301w13t12recipes.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.controller.IngredientsAdapter;
import ca.ualberta.c301w13t12recipes.controller.IngredientsFridgeAdapter;
import ca.ualberta.c301w13t12recipes.model.Ingredient;
import ca.ualberta.c301w13t12recipes.view.AddIngredWizardActivity.AddIngredDiaglogFragment;

/**
 * @author HUANG GUANQI
 * 
 */
public class IngredientsFridgeActivity extends Activity {

	private DatabaseController controller;
	private ListView ingredientsListView;
	private Button addButton;
	private Button clearButton;
	private HashMap<String, Object> map;
	private IngredientsFridgeAdapter adapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fridge);
		controller = new DatabaseController(getApplicationContext());
		this.setupWidgets();
		this.refreshList();

		/*
		 * ingredientsListView.setOnItemLongClickListener(new
		 * OnItemLongClickListener() {
		 * 
		 * @Override public boolean onItemLongClick(AdapterView<?> listView,
		 * View view, int pos, long id) { // TODO long click to delete selected
		 * item and then remove Ingredient ingredient =
		 * controller.getIngredListFromIngredDB().get(pos);
		 * Toast.makeText(IngredientsFridgeActivity.this, ingredient.getName()
		 * +" is removed", 3) .show();
		 * controller.removeIngredFromIngredDB(ingredient); refreshList();
		 * return false; }
		 * 
		 * });
		 */
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
		clearButton.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				Map<String, Object> map = null;
				boolean isChecked;
				for (int i = 0; i < ingredientsListView.getCount(); i++) {
					map = (Map<String, Object>) ingredientsListView
							.getItemAtPosition(i);
					isChecked = (Boolean) map.get("checked");
					if (isChecked) {
						Toast.makeText(getApplicationContext(),
								(CharSequence) map.get("name"), 1).show();
					}
				}
			}

		});
	}

	protected void setupWidgets() {
		ingredientsListView = (ListView) findViewById(R.id.fridge_listView);
		addButton = (Button) findViewById(R.id.fridge_add_button);
		clearButton = (Button) findViewById(R.id.fridge_clear_button);
	}

	protected void refreshList() {
		adapter = new IngredientsFridgeAdapter();
		ArrayList<Ingredient> li = controller.getIngredListFromIngredDB();
		ingredientsListView.setAdapter(adapter.getAdapter(this, li));
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
							controller.addIngredFromIngredDB(new Ingredient(
									name, amount));
							Toast.makeText(IngredientsFridgeActivity.this,
									"Successfully adding a new ingredient", 2)
									.show();
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
}
