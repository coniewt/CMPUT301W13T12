package com.example.cmp301android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AddActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		String[] ingredience_list = {"2 tablespoons butter",
				"1 Spanish onion, chopped"," bay leaf ","8 sprigs parsley"};
		ListView lv =(ListView)findViewById(R.id.search_outcome_viewlist);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add, menu);
		return true;
	}

}
