package ca.ualberta.c301w13t12recipes.controller;

import java.io.File;
import java.util.ArrayList;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import ca.ualberta.c301w13t12recipes.model.Image;

public class GalleryAdapter extends ImageAdapter{
	
	
	public GalleryAdapter(Context context,ArrayList<Image> imageList){
		super(context, imageList);
		//density = context.getResources().getDisplayMetrics().density;
		
	}
	
	
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
