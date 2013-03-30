package ca.ualberta.c301w13t12recipes.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.model.Recipe;

/**
 * 
 * @author GUANQI HUANG
 * 
 */
public class AddCompleteWizardActivity extends Activity {
	Button doneButton;
	DatabaseController controller;
	Recipe recipe;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_complete_wizard);

		setupWidgets();
		doneButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				saveRecipe();
				jumpToMainActivity();

			}

		});

		// TODO Auto-generated method stub
	}

	private void setupWidgets() {
		doneButton = (Button) findViewById(R.id.add_button_complete);
	}

	private void getRecipe() {
		recipe = (Recipe) getIntent().getSerializableExtra("NEW_RECIPE");

	}

	private void saveRecipe() {
		getRecipe();
		controller = new DatabaseController(AddCompleteWizardActivity.this);

		if (controller.isLocalRecipeExists(recipe)) {
			controller.delete(recipe);
			controller.addRecipe(recipe);
		} else {
			controller.addRecipe(recipe);
		}

	}

	private void jumpToMainActivity() {
		Intent intent = new Intent(AddCompleteWizardActivity.this,
				MainActivity.class);
		startActivity(intent);

	}

}
