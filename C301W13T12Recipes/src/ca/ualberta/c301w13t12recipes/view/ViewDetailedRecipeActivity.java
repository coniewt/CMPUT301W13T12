package ca.ualberta.c301w13t12recipes.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
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
	private GridView gridview;
	//private Gallery gallery;
	private Recipe recipe;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_entry);
		setupWidgets();
		getRecipe();
		Log.v("test", "********"+recipe.getImage(0).getTN_Path()+"**************");
		gridview.setAdapter(new ImageAdapter(ViewDetailedRecipeActivity.this,(ArrayList<Image>)recipe.getImage()));
		
	}

	/**
	 * Set up the new component
	 */
	private void setupWidgets() {
		//gallery = (Gallery)findViewById(R.id.gallery1);
		gridview = (GridView)findViewById(R.id.test_gridView);
	}

	private void getRecipe(){
		recipe = (Recipe) getIntent().getSerializableExtra("LOCAL_RECIPE");
	}

}
