package ca.ualberta.c301w13t12recipes.controller;

import java.io.File;
import java.util.ArrayList;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import ca.ualberta.c301w13t12recipes.model.Image;

/**
 * Adapter for Gallery for ListView, shows images in a center-locked, horizontally scrolling list
 */
public class GalleryAdapter extends ImageAdapter{
	
	/**
	 * Constructor
	 * 
	 * @param Context
	 * @param ArrayList<Image>
	 */
	public GalleryAdapter(Context context,ArrayList<Image> imageList){
		super(context, imageList);
		//density = context.getResources().getDisplayMetrics().density;
		
	}
	
	/**
	 * Acquire a Gallery Adapter to display a set of image
	 * @param int Position of the image in the adapter
	 * @param View Visual indicator of progress in some operation
	 * @param ViewGroup Multiple-exclusion scope for a set of radio button
	 * @return View - ImageView
	 * @see ca.ualberta.c301w13t12recipes.controller.ImageAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(context);
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		File image = new File(gallery.get(position).getTN_Path());
		imageView.setImageURI(Uri.fromFile(image));
		return imageView;
	}
	
	
}
