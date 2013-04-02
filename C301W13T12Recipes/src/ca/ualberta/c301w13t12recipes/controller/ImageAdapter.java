package ca.ualberta.c301w13t12recipes.controller;

import java.io.File;
import java.util.ArrayList;

import ca.ualberta.c301w13t12recipes.model.Image;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Convert an ArrayList of images to a grid view adapter for ListView
 * 
 * @author GUANQI HUANG
 */
public class ImageAdapter extends BaseAdapter{
	protected Context context;
	private DatabaseController controller;
	protected ArrayList<Image> gallery;

	/**
	 * Constructor
	 * @param Context
	 * @param ArrayList<Image>
	 */
	public ImageAdapter(Context newContext,ArrayList<Image> gallery){
		// TODO constructor
		this.context = newContext;	
		this.controller = new DatabaseController(newContext);
		this.gallery = gallery;
	}
	
	/**
	 * Return size of gallery ArrayList
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
	// TODO return size of gallery arraylist
		return gallery.size();
	}
	
	/**
	 * Place holder, not implemented at this moment.
	 */
	public void refreshImageLib(){	
	}

	/**
	 * Get image at a position from gallery
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO get image at a position from gallery
		return gallery.get(position);
	}

	/**
	 * Auto-generated method stub
	 * @param int Position of the image in the adapter
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Acquire a image adapter to display a set of image
	 * @param int Position of the image in the adapter
	 * @param View Visual indicator of progress in some operation
	 * @param ViewGroup Multiple-exclusion scope for a set of radio button
	 * @return View - ImageView
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(context);
		File image = new File(gallery.get(position).getTN_Path());
		imageView.setImageURI(Uri.fromFile(image));
		return imageView;
	}

}
