package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

/**
 * Main activity class for main menu
 * @author GUANQI HUANG
 *
 */



public class MainActivity extends Activity {

	private ImageButton main_addButton;// Declare add button
	private ImageButton main_viewButton;// Declare view button
	private ImageButton main_searchButton;// Declare search button
	private ImageButton main_myFridgeButton;// Declare my fridge button

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setupButtons();// initialize buttons
		
		
		main_addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Jump to AddIngredActivity 
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, AddTitleDescWizardActivity.class);
				startActivity(intent);
			}
		});
		main_viewButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Jump to ViewListActivity
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ViewListActivity.class);
				startActivity(intent);
			}
		});
		main_searchButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SearchActivity.class);
				startActivity(intent);
			}
		});
		main_myFridgeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, FridgeActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void setupButtons(){
		//TODO initialize all the buttons via R.id
		main_addButton = (ImageButton)findViewById(R.id.main_add_button);
		main_viewButton = (ImageButton)findViewById(R.id.main_view_button);
		main_searchButton= (ImageButton)findViewById(R.id.main_search_button);
		main_myFridgeButton =(ImageButton)findViewById(R.id.main_fridge_button);
	}
}
