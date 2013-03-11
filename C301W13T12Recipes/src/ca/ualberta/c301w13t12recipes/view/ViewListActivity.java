package ca.ualberta.c301w13t12recipes.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import ca.ualberta.c301w13t12recipes.R;
public class ViewListActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.activity_view_listview);
	    ListView lv = (ListView) findViewById(R.id.view_listview);
	    // TODO Auto-generated method stub
	}

}
