package ca.ualberta.c301w13t12recipes.view;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.ShareController;
import ca.ualberta.c301w13t12recipes.controller.GalleryAdapter;
import ca.ualberta.c301w13t12recipes.controller.IngredientsAdapter;
import ca.ualberta.c301w13t12recipes.model.Image;
import ca.ualberta.c301w13t12recipes.model.Recipe;

/**
 * This is the activity, which is provided a view of each entry includes the
 * image , name of user , description
 * 
 * @author YUWEI DUAN
 */
public class ViewDetailedRecipeActivity extends Activity {
	private Gallery gallery;;
	private ImageButton editButton;
	private ImageButton shareButton;
	private IngredientsAdapter adapter;
	// private Gallery gallery;
	private Recipe recipe;
	private TextView titleTextView;
	private TextView descTextView;
	private ListView ingredListView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_entry);
		setupWidgets();
		getRecipe();
		
		refreshGallery();
		showName();
		showDescription();
		refreshList();
		
		editButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		shareButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(ShareController.SendEmail(recipe));
			}
			
		});
	}

	/**
	 * Set up the new component
	 */
	private void setupWidgets() {
		gallery = (Gallery) findViewById(R.id.view_entry_gallery);
		editButton = (ImageButton) findViewById(R.id.view_edit_imageButton);
		shareButton = (ImageButton) findViewById(R.id.view_share_imageButton);
		titleTextView = (TextView) findViewById(R.id.view_textView_title);
		descTextView = (TextView) findViewById(R.id.view_textView_description);
		ingredListView = (ListView) findViewById(R.id.view_ingredients_listView);
	}

	private void getRecipe() {
		recipe = (Recipe) getIntent().getSerializableExtra("LOCAL_RECIPE");
	}

	

	private void refreshList() {
		adapter = new IngredientsAdapter();
		ingredListView.setAdapter(adapter.getAdapter(this,
				recipe.getIngredients()));

	}

	private void refreshGallery() {
		gallery.setAdapter(new GalleryAdapter(ViewDetailedRecipeActivity.this,
				(ArrayList<Image>) recipe.getImage()));
	}
	
	private void showDescription(){
		descTextView.setText(new String(recipe.getDirections()));
	}
	
	private void showName(){
		titleTextView.setText(new String(recipe.getName()));
	}

}
