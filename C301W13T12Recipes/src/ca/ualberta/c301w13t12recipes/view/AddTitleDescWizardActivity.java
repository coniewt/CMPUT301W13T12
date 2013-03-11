package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTitleDescWizardActivity extends Activity {
	
	private EditText descEditText; //description widget
	private EditText nameEditText; //name widget
	
	private Button cancelButton; // cancel button
	private Button nextButton;// next button	
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.activity_add_title_desc_wizard);
	    
	    setupButton();// Initialize all the buttons
	    setupEditText();//Initialize all the EditText widgets
	    
	    
	    
	    nextButton.setOnClickListener(new View.OnClickListener(){
	    	public void onClick(View v){
	    		saveAndJumpToAddIngredWizard();
	    	}
	    });
	    
	    
	    // TODO Auto-generated method stub
	}
	private void setupButton(){
		cancelButton = (Button)findViewById(R.id.add_step1_cancel_button);
		nextButton = (Button)findViewById(R.id.add_step1_next_button);
		
	}
	private void setupEditText(){
		descEditText =(EditText)findViewById(R.id.add_editText_description);
		nameEditText =(EditText)findViewById(R.id.add_editText_recipe_name);
		
	}
	
	private void saveAndJumpToAddIngredWizard(){
		
		Recipe recipe = new Recipe("","","","");
		recipe.setDirections(descEditText.getText().toString());//get description from descEditText Widget
		recipe.setName(nameEditText.getText().toString());// get nameEditText from nameEditText Widget
		Toast.makeText(AddTitleDescWizardActivity.this, "Name and directions are saved !", 3).show();
		Intent intent = new Intent(AddTitleDescWizardActivity.this,AddIngredWizardActivity.class);
		Bundle bundle = new Bundle();
		//recipe.addIngredient("frank", "list");
		bundle.putParcelable("NEW_RECIPE", recipe);
	    intent.putExtras(bundle);
	    startActivity(intent);
	    
		
	}
}
