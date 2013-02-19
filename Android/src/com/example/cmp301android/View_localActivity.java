package com.example.cmp301android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class View_localActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_local);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_view_local, menu);
		return true;
	}

}
