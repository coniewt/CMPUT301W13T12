package ca.ualberta.c301w13t12recipes.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.graphics.Bitmap;

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

	public void saveBMP(File intentPicture, Bitmap ourBMP) throws IOException,
			FileNotFoundException {
		OutputStream out = new FileOutputStream(intentPicture);
		ourBMP.compress(Bitmap.CompressFormat.JPEG, 75, out);
		out.close();
	}
}
