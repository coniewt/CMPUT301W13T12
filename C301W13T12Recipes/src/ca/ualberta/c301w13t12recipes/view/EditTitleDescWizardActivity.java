package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import ca.ualberta.c301w13t12recipes.view.AddTitleDescWizardActivity.setPasswordFragment;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
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
	
	private void fillInfoBackTofields(){
		if(recipe.getPassword().equals("")){
			lock.setChecked(false);
		}else{
			lock.setChecked(true);
		}
		nameEditText.setText(recipe.getName());
		descEditText.setText(recipe.getDirections());
		
	}

}
