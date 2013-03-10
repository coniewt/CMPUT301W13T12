package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
	private Recipe createRecipe(){
		return new Recipe(null,null,null,null,null);
	}
	
}
