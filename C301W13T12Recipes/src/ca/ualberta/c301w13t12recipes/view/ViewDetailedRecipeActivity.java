package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Gallery;

public class ViewDetailedRecipeActivity extends Activity {
	private Gallery gallery;
	private Gallery gallery1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_view_entry);
	    registerForContextMenu(gallery);
	    // TODO Auto-generated method stub
	}

}
