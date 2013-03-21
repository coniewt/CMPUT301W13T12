package ca.ualberta.c301w13t12recipes.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.ImageAdapter;
import ca.ualberta.c301w13t12recipes.model.Image;
import ca.ualberta.c301w13t12recipes.model.Recipe;

/**
 * This is the activity, which is provided a view of each entry includes the
 * image , name of user , description
 * 
 * @author YUWEI DUAN
 */
public class ViewDetailedRecipeActivity extends Activity {
	private Gallery gallery;
	private ArrayList<Image> imageList;
	// private Gallery gallery1;
	private Integer[] mps = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e, R.drawable.f };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_entry);
		getImageList();
		setUp();
		registerForContextMenu(gallery);
		// TODO Auto-generated method stub
		 gallery.setAdapter(new ImageAdapter(this, lim));
		// DatabaseController(this)).getDB().get));
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
	/**
	 * Set up the new component
	 */
	private void setUp(){
		gallery= new Gallery(this);
	}
	@SuppressWarnings("unchecked")
	/**
	 * To get the list of image from intend 
	 * from addpicwizardact
	 */
	private void getImageList() {
		imageList = (ArrayList<Image>) getIntent().getSerializableExtra("IMAGE");
	}

}
