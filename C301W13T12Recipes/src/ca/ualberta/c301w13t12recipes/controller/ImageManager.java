package ca.ualberta.c301w13t12recipes.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import ca.ualberta.c301w13t12recipes.model.Image;
/**
 * Handling the pictures taken by camera
 * 
 * Include:
 * 	1. Compressing BMP
 * 	2. Image path generator
 * 	3. Creating parent folder & sub folder 
 * 
 * @author YUWEI DUAN & GUANQI HUANG
 * 
 */
public class ImageManager {

	/**
	 * Take pictures from camera
	 * @param URI Uniform resource identifier for picture in high definition
	 * @param URI Uniform resource identifier for picture thumb nail
	 */
	public void compressBMP(Uri uriImageHD,Uri uriImageTN ) throws IOException,
			FileNotFoundException {
		File thumbnailFile = new File(uriImageTN.getPath());
		Bitmap bitmap = decodeBitmapFromFile(uriImageHD,500,500);
		OutputStream out = new FileOutputStream(thumbnailFile);
		bitmap.compress(Bitmap.CompressFormat.JPEG, 75, out);
		out.close();
	}

	/**
	 * Automatically generate a file name and path string for pictures
	 * @param String Path for the folder to store pictures
	 * @return String - Complete path for the image including path and file name
	 */
	public String genImgPath(String folder) {
		return folder + "/" + String.valueOf(System.currentTimeMillis())
				+ ".jpg";
	}

	/**
	 * Generate a parent folder to store HD images and corresponding thumb nail folder
	 * @param String Folder name
	 * @return String - Complete path for the folder
	 */
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

	/**
	 * Creating the sub folder for corresponding thumb nail pictures, if the parent folder
	 * is not existed, create that folder too
	 * @param String Folder name for HD images
	 * @param String Sub folder name for thumb nails
	 * @return String - Complete path of sub folder for storing the thumb nail pictures
	 */
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

	/**
	 * Create image file
	 * @param String Path of the image file
	 * @return URI - URI of the image file
	 */
	public Uri createImage(String imageFilePath) {
		File imageFile = new File(imageFilePath);
		return Uri.fromFile(imageFile);
	}

	/**
	 * Calculate images size and return a modified scale of the image
	 * @param BitmapFactory.Options Options object of the original image
	 * @param int Preferred width of the scaled image
	 * @param int Preferred height of the scaled image
	 * @return int - New size of the image
	 */
	private int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		
		// TODO Calculate Raw height and width of image
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

	/**
	 * Decode a image from the file
	 * @param URI Unified resource identifier for image file
	 * @param int Preferred width of the scaled image
	 * @param int Preferred height of the scaled image
	 * @return Bitmap - Bitmap of the image
	 */
	public Bitmap decodeBitmapFromFile(Uri imageUri,
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
	
	/**
	 * Remove a image from local storage
	 * @param Image Image object of the image wish to remove
	 */
	public void removeImageFilesFromLocal(Image image){
		File imageHD = new File(image.getHD_Path());
		if(imageHD.exists()){
			imageHD.delete();
		}
		File imageTN = new File(image.getTN_Path());
		if(imageTN.exists()){
			imageTN.delete();
		}
	}

	/**
	 * Convert bitmap to file and store it locally
	 * @param Bitmap Bitmap of the image wish to store
	 * @param String Name of the file
	 * @return String - Path of the image
	 */
	public String convertFromBitmapToFilePath(Bitmap bitmap ,String name){
		createFolder("/tmp");
		createSubfolder("/tmp", "/thumbnail");
		String path =Environment.getExternalStorageDirectory().getAbsolutePath()+"/tmp/thumbnail/"+name+".jpg";
		File image_file = new File(path);
		if(image_file.exists()){
			image_file.delete();
		}
		OutputStream out;
		try {
			out = new FileOutputStream(path);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}
}
