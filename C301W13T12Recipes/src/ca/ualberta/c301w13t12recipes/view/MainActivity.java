package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.R.layout;
import ca.ualberta.c301w13t12recipes.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	ImageButton main_addButton;// Declare add button
	ImageButton main_viewButton;// Declare view button
	ImageButton main_searchButton;// Declare search button
	ImageButton main_myFridgeButton;// Declare my fridge button

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		main_addButton = (ImageButton)findViewById(R.id.main_add_button);
		main_viewButton = (ImageButton)findViewById(R.id.main_view_button);
		main_searchButton= (ImageButton)findViewById(R.id.main_search_button);
		main_myFridgeButton =(ImageButton)findViewById(R.id.main_fridge_button);
		//initialize all the buttons via R.id
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
