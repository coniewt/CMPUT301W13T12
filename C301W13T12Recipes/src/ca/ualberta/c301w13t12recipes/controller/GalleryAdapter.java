package ca.ualberta.c301w13t12recipes.controller;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;

import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.model.Image;
public class GalleryAdapter extends ImageAdapter{
	private static final int ITEM_WIDTH = 136;
	private static final int ITEM_HEIGHT = 88;
	private float density;
	
	
	public GalleryAdapter(Context context,ArrayList<Image> imageList){
		super(context, imageList);
		//density = context.getResources().getDisplayMetrics().density;
		
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(context);
		
		
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		/*imageView.setLayoutParams(new Gallery.LayoutParams(
				(int) (ITEM_WIDTH * density + 0.5f),
				(int) (ITEM_HEIGHT * density + 0.5f)));*/
		File image = new File(gallery.get(position).getTN_Path());
		imageView.setImageURI(Uri.fromFile(image));
		return imageView;
	}
	
	
}
