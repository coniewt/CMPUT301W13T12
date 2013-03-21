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
 * @author YWUEI DUAN
 */
public class ImageAdapter extends BaseAdapter{
	private Context context;
	private DatabaseController controller;
	private ArrayList<Image> gallery;

	/**
	 * 
	 * @param newContext
	 * @param gallery a list of Image
	 */
	public ImageAdapter(Context newContext,ArrayList<Image> gallery){
		// TODO constructor
		this.context = newContext;	
		this.controller = new DatabaseController(newContext);
		this.gallery = gallery;
	}
	
	@Override
	public int getCount() {
	// TODO return size of gallery arraylist
		return gallery.size();
	}
	
	public void refreshImageLib(){	
	}

	@Override
	public Object getItem(int position) {
		// TODO get image at a position from gallery
		return gallery.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(context);
		File image = new File(gallery.get(position).getPath());
		imageView.setImageURI(Uri.fromFile(image));
		return imageView;
	}

}
