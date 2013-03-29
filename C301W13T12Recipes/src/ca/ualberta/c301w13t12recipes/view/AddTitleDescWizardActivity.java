package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Activity class for adding details of the recipe
 *
 */
public class AddTitleDescWizardActivity extends Activity {
	
	protected EditText descEditText; //description widget
	protected EditText nameEditText; //name widget
	
	protected Button clearButton; // cancel button
	protected Button nextButton;// next button	
	
	protected Switch lock;// password switch
	
	Recipe recipe = new Recipe("","","");
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.activity_add_title_desc_wizard);
	    
	    setupButton();// Initialize all the buttons
	    setupWidgets();//Initialize all the EditText widgets
	    
	    
	    
	    nextButton.setOnClickListener(new View.OnClickListener(){
	    	public void onClick(View v){
	    		saveAndJumpToAddIngredWizard();
	    	}
	    });
	    clearButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				clearAllFields();
				Toast toast = Toast.makeText(getApplicationContext(), "Operation Complete", 1);
				toast.show();
			}
		});
	    lock.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked == true){
					DialogFragment newFragment = new setPasswordFragment();
					newFragment.show(getFragmentManager(), "CREATE_PASSWORD");
				}else{
					recipe.setPassword("");
				}
			}
		});
	    
	    // TODO Auto-generated method stub
	}
	protected void setupButton(){
		clearButton = (Button)findViewById(R.id.add_step1_Clear_button);
		nextButton = (Button)findViewById(R.id.add_step1_next_button);
		lock =(Switch)findViewById(R.id.add_switch_password);
		
	}
	protected void setupWidgets(){
		descEditText =(EditText)findViewById(R.id.add_editText_description);
		nameEditText =(EditText)findViewById(R.id.add_editText_recipe_name);
		
	}
	protected void clearAllFields(){
		descEditText.setText("");
		nameEditText.setText("");
	}
	
	void saveAndJumpToAddIngredWizard(){
		
		
		recipe.setDirections(descEditText.getText().toString());//get description from descEditText Widget
		recipe.setName(nameEditText.getText().toString());// get nameEditText from nameEditText Widget
		Toast.makeText(AddTitleDescWizardActivity.this, "Name and directions are saved !", 3).show();
		Intent intent = new Intent(AddTitleDescWizardActivity.this,AddIngredWizardActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("NEW_RECIPE",recipe);
	    intent.putExtras(bundle);
	    startActivity(intent);
	    
		
	}
	class setPasswordFragment extends DialogFragment {
		private EditText passwordEditText;
		String password;

		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			LayoutInflater inflater = getActivity().getLayoutInflater();
			final View v = inflater.inflate(R.layout.dialog_edit_password_enabler,
					null);
			builder.setView(v);
			
			passwordEditText = (EditText) v
					.findViewById(R.id.add_password_textEdit);
			inflater.inflate(R.layout.dialog_add_ingredient, null);
			builder.setPositiveButton("Done",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int id) {
							password = passwordEditText.getText().toString();
							recipe.setPassword(password);
							Toast.makeText(AddTitleDescWizardActivity.this,
									"Password has been set", 2).show();
						}
					}).setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							setPasswordFragment.this.getDialog().cancel();
							lock.setChecked(false);
							Toast.makeText(AddTitleDescWizardActivity.this,
									"Operation is cancelled", 2).show();

						}
					});
			return builder.create();
		}

	}
}
