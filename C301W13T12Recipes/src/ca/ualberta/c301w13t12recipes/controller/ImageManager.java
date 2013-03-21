package ca.ualberta.c301w13t12recipes.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

/**
 * Handling taking pictures from camera
 * 
 * @author YUWEI DUAN
 * 
 */
public class ImageManager {
	/**
	 * Take Pictures from camera
	 */

	// call this to accept

	public void saveBMP(File intentPicture, Bitmap bitmap) throws IOException,
			FileNotFoundException {
		OutputStream out = new FileOutputStream(intentPicture);
		bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
		out.close();
	}

	public String genImgPath(String folder) {
		return folder + "/" + String.valueOf(System.currentTimeMillis())
				+ ".jpg";
	}

	public String createFolder(String folder) {
		//TODO create a parent folder for HD images
		// path of temporary folder
		String tmpFolderPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + folder + "";
		// create an object for temp images folder
		File tmpFolder = new File(tmpFolderPath);
		if (!tmpFolder.exists()) {
			tmpFolder.mkdir();
		}
		return tmpFolderPath;
	}
	
	public String createSubfolder(String folder, String subFolder){
		//TODO create a subfolder for thumbnails ( create its parent folder if doesn't exist)
		String tmpFolderPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + folder + "";
		// path of thumbnail folder
		String thumbnailFolderPath = tmpFolderPath + subFolder + "";
		File tmpFolder = new File(tmpFolderPath);
		// create an object for thumbnail images folder
		File thumbnailFolder = new File(thumbnailFolderPath);
		if (!tmpFolder.exists()) {
			tmpFolder.mkdir();
			if (!thumbnailFolder.exists()) {
				thumbnailFolder.mkdir();
			}
		}else{
			if (!thumbnailFolder.exists()) {
				thumbnailFolder.mkdir();
			}
		}
		return thumbnailFolderPath;
	}
	public Uri createImage(String imageFilePath) {
		File imageFile = new File(imageFilePath);
		return Uri.fromFile(imageFile);
	}
}
