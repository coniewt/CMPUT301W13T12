package ca.ualberta.c301w13t12recipes.view;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.ImageManager;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import ca.ualberta.c301w13t12recipes.model.StrResource;
import es.softwareprocess.bogopicgen.BogoPicGen;

/**
 * Activity class for adding picture wizard
 * 
 */
public class AddPicWizardActivity extends Activity {
	private Button addButton;
	private Button nextButton;
	private ImageView view_phtot;
	private Recipe recipe;
	private ImageManager im;

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
	}

	/**
	 * TODO initialize all buttons and imageview widgets
	 */
	private void setupWidgets() {
		addButton = (Button) findViewById(R.id.add_button_complete);
		nextButton = (Button) findViewById(R.id.add_photo_button_next);
		view_phtot = (ImageView) findViewById(R.id.add_image_view_camera_sign);
		im = new ImageManager();
	}

	/**
	 * TODO display the sign when the album is empty
	 */
	private void showNoImageSign() {

	}

	/**
	 * TODO hide no image sign when the album has at least one photo
	 */
	private void hideNoImageSign() {

	}

	/**
	 * TODO create a new intent that allow
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
	public void takePhoto(){
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmp";
	    File folderF = new File(folder);
	    if (!folderF.exists()) {
	    	folderF.mkdir();
	    }
	    String imageFilePath = folder + "/" + String.valueOf(System.currentTimeMillis()) + "jpg";
	    File imageFile = new File(imageFilePath);
	    Uri imageFileUri = Uri.fromFile(imageFile);
	    
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
	    startActivityForResult(intent, StrResource.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	private Bitmap ourBMP;

	private void setBogoPic() {
		Toast.makeText(this, "Generating Photo", Toast.LENGTH_LONG).show();
		//ourBMP = BogoPicGen.generateBitmap(400, 400);
		view_phtot.setImageBitmap(ourBMP);
	}
	
}
