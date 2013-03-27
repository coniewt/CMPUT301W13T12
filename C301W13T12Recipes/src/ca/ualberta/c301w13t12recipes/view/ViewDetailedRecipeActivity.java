package ca.ualberta.c301w13t12recipes.view;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import ca.ualberta.c301w13t12recipes.R;
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
	private Gallery gallery;
	;
	private ImageButton editButton;
	private ImageButton shareButton;
	// private Gallery gallery;
	private Recipe recipe;
	private ListView lv_ingre;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_entry);
		setupWidgets();
		getRecipe();
		gallery.setAdapter(new GalleryAdapter(ViewDetailedRecipeActivity.this,
				(ArrayList<Image>) recipe.getImage()));
		lv_ingre.setAdapter(new IngredientsAdapter().getAdapter(this, recipe.getIngredients()));
		editButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			/*
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PopupMenu ppm = new PopupMenu(v.getContext(), ppm_im);
				ppm.getMenuInflater().inflate(R.menu.view_detail_popup_menu, ppm.getMenu());
				ppm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(ViewDetailedRecipeActivity.this,
								"Clicked popup menu item " + item.getTitle(),
								Toast.LENGTH_SHORT).show();
						return true;
					}
				});
				ppm.show();
			}
			*/
		});
	}

	/**
	 * Set up the new component
	 */
	private void setupWidgets() {
		gallery = (Gallery) findViewById(R.id.view_entry_gallery);
		editButton= (ImageButton) findViewById(R.id.view_edit_imageButton);
		shareButton = (ImageButton)findViewById(R.id.view_share_imageButton);
	}

	private void getRecipe() {
		recipe = (Recipe) getIntent().getSerializableExtra("LOCAL_RECIPE");
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.view_detail_popup_menu, menu);
		return true;
	}

}
