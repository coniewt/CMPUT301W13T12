package ca.ualberta.c301w13t12recipes.controller;
import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

/**
 * Handling taking pictures from camera
 * @author YUWEI DUAN
 * 
 */
public class ImageManager {
	/**
	 * Take Pictures from camera
	 */
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
	    //startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
}
