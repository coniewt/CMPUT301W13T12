package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import ca.ualberta.c301w13t12recipes.view.AddIngredWizardActivity.AddIngredDiaglogFragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
	
	private EditText descEditText; //description widget
	private EditText nameEditText; //name widget
	
	private Button cancelButton; // cancel button
	private Button nextButton;// next button	
	
	private Switch lock;// password switch
	
	Recipe recipe = new Recipe("","","");
	
	
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
	    lock.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked == true){
					DialogFragment newFragment = new setPasswordFragment();
					newFragment.show(getFragmentManager(), "CREATE_PASSWORD");
				}else{
					recipe.setPassWord("");
				}
			}
		});
	    
	    // TODO Auto-generated method stub
	}
	private void setupButton(){
		cancelButton = (Button)findViewById(R.id.add_step1_Clear_button);
		nextButton = (Button)findViewById(R.id.add_step1_next_button);
		lock =(Switch)findViewById(R.id.add_switch_password);
		
	}
	private void setupEditText(){
		descEditText =(EditText)findViewById(R.id.add_editText_description);
		nameEditText =(EditText)findViewById(R.id.add_editText_recipe_name);
		
	}
	
	private void saveAndJumpToAddIngredWizard(){
		
		
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
							recipe.setPassWord(password);
							Toast.makeText(AddTitleDescWizardActivity.this,
									"Password has been set", 2).show();
						}
					}).setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							setPasswordFragment.this.getDialog().cancel();
							lock.setEnabled(false);

						}
					});
			return builder.create();
		}

	}
}
