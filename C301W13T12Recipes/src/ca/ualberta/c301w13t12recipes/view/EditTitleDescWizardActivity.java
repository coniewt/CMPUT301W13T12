package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class EditTitleDescWizardActivity extends AddTitleDescWizardActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_add_title_desc_wizard);

		setupButton();// Initialize all the buttons
		setupWidgets();// Initialize all the EditText widgets
		getRecipeFromIntent();
		fillInfoBackTofields();

		nextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saveAndJumpToAddIngredWizard();
			}
		});
		clearButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				clearAllFields();
				Toast toast = Toast.makeText(getApplicationContext(),
						"Operation Complete", 1);
				toast.show();
			}
		});
		lock.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked == true) {
					DialogFragment newFragment = new setPasswordFragment();
					newFragment.show(getFragmentManager(), "CREATE_PASSWORD");
				} else {
					recipe.setPassword("");
				}
			}
		});
	}

	/**
	 * receive the recipe object from ViewDetailedRecipeActivity
	 */
	private void getRecipeFromIntent() {
		recipe = (Recipe) getIntent().getSerializableExtra("NEW_RECIPE");

	}

	private void fillInfoBackTofields() {
		if (recipe.getPassword().equals("")) {
			lock.setChecked(false);
		} else {
			lock.setChecked(true);
		}
		nameEditText.setText(recipe.getName());
		descEditText.setText(recipe.getDirections());

	}
	protected void saveAndJumpToAddIngredWizard(){
		recipe.setDirections(descEditText.getText().toString());//get description from descEditText Widget
		recipe.setName(nameEditText.getText().toString());// get nameEditText from nameEditText Widget
		Toast.makeText(EditTitleDescWizardActivity.this, "Name and directions are saved !", 3).show();
		Intent intent = new Intent(EditTitleDescWizardActivity.this,AddIngredWizardActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("NEW_RECIPE",recipe);
	    intent.putExtras(bundle);
	    startActivity(intent);
	    finish();
		
	}

}
