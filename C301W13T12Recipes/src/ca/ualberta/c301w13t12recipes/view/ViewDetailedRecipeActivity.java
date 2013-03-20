package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;

/**
 * @author YUWEI DUAN
 * 
 */
public class ViewDetailedRecipeActivity extends Activity {
	private Gallery gallery;
	// private Gallery gallery1;
	private Integer[] mps = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e, R.drawable.f };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_entry);
		registerForContextMenu(gallery);
		// TODO Auto-generated method stub
		gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onNothingSelected(AdapterView<?> arg0) {
			}

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
			}
		});
	}

}
