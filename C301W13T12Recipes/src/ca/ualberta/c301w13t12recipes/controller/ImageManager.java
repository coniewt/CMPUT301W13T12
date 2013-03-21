package ca.ualberta.c301w13t12recipes.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ca.ualberta.c301w13t12recipes.model.Image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

/**
 * Handling the pictures taken by camera
 * 	Include:
 * 		1. Compressing BMP
 * 		2. Image path generator
 * 		3. Creating parent folder & sub folder 
 * 
 * @author YUWEI DUAN & GUANQI HUANG
 * 
 */
public class ImageManager {
	/**
	 * Take Pictures from camera
	 */

	// call this to accept

	public void compressBMP(Uri uriImageHD,Uri uriImageTN ) throws IOException,
			FileNotFoundException {
		File thumbnailFile = new File(uriImageTN.getPath());
		Bitmap bitmap = decodeBitmapFromFile(uriImageHD,500,500);
		OutputStream out = new FileOutputStream(thumbnailFile);
		bitmap.compress(Bitmap.CompressFormat.JPEG, 75, out);
		out.close();
	}

	public String genImgPath(String folder) {
		return folder + "/" + String.valueOf(System.currentTimeMillis())
				+ ".jpg";
	}

	public String createFolder(String folder) {
		// TODO create a parent folder for HD images
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

	public String createSubfolder(String folder, String subFolder) {
		// TODO create a subfolder for thumbnails ( create its parent folder if
		// doesn't exist)
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
		} else {
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

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}

	public static Bitmap decodeBitmapFromFile(Uri imageUri,
			int reqWidth, int reqHeight) {
		//TODO decode the bitmap from the file
		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile( imageUri.getPath(), options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile( imageUri.getPath(), options);
	}
}
