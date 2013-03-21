package ca.ualberta.c301w13t12recipes.view;

/**
 *  @author GUANQI HUANG
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.ImageAdapter;
import ca.ualberta.c301w13t12recipes.controller.ImageManager;
import ca.ualberta.c301w13t12recipes.model.Image;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import ca.ualberta.c301w13t12recipes.model.StrResource;

/**
 * Activity class for adding picture wizard
 * 
 */
public class AddPicWizardActivity extends Activity {
	private Button addButton;
	private Button nextButton;
	private GridView gridView;
	private Recipe recipe;
	private ImageManager imageManager;
	private Uri uriImgHD;
	private Uri uriImgTN;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getRecipe();
		setContentView(R.layout.activity_add_img_wizard);
		setupWidgets();

		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO adding photos
				takePhoto();

			}
		});

		nextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO jump to next activity
				jumpToAddCompleteActivity();
			}
		});
		
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> gridView, View view, int pos,
					long id) {
				// TODO long click to remove selected item
				recipe
				
			}

		});
	}

	/**
	 * TODO initialize all buttons and imageview widgets
	 */
	private void setupWidgets() {
		addButton = (Button) findViewById(R.id.add_button_complete);
		nextButton = (Button) findViewById(R.id.add_photo_button_next);
		gridView = (GridView) findViewById(R.id.add_photo_gridView);
		imageManager = new ImageManager();
	}

	/**
	 * Get the recipe from intent, which is sent by other activity TODO create a
	 * new intent that allow
	 */
	private void getRecipe() {
		recipe = (Recipe) getIntent().getSerializableExtra("NEW_RECIPE");
	}

	private void jumpToAddCompleteActivity() {
		Intent intent = new Intent(AddPicWizardActivity.this,
				AddCompleteWizardActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("NEW_RECIPE", recipe);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	/**
	 * This is method, which is mainly responsible for taking photo
	 * 
	 */

	public void takePhoto() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		String folderPath = imageManager.createFolder("/tmp");// hd image folder
		String subFolderPath = imageManager.createSubfolder("/tmp",
				"/thumbnail");// thumbnail folder

		String imgPathHD = imageManager.genImgPath(folderPath);
		String imgPathTN = imageManager.genImgPath(subFolderPath);

		uriImgHD = imageManager.createImage(imgPathHD);
		uriImgTN = imageManager.createImage(imgPathTN);

		intent.putExtra(MediaStore.EXTRA_OUTPUT, uriImgHD);
		startActivityForResult(intent,
				StrResource.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == StrResource.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {

				try {
					imageManager.compressBMP(uriImgHD, uriImgTN);
				} catch (FileNotFoundException e) {
					// TODO file not found
					Toast.makeText(this, "Image not found", Toast.LENGTH_LONG)
							.show();
					e.printStackTrace();
				} catch (IOException e) {
					// TODO FATAL ERROR : EXCEPITON
					Toast.makeText(this, "IOExceptions cannot be resolved",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

				recipe.addImage(uriImgTN.getPath());
				refreshView();
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Operation cancelled", Toast.LENGTH_LONG)
						.show();
			} else {

			}
		}
	}

	public void refreshView() {
		gridView.setAdapter(new ImageAdapter(AddPicWizardActivity.this,
				(ArrayList<Image>) recipe.getImage()));
	}
}
