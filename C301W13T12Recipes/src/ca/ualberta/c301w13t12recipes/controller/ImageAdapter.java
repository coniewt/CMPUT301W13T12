package ca.ualberta.c301w13t12recipes.controller;

import java.util.ArrayList;

import ca.ualberta.c301w13t12recipes.model.Image;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{
	private Context context;
	private DatabaseController controller;
	private ArrayList<Image> gallary;

	public ImageAdapter(Context newContext,ArrayList<Image> gallary){
		// TODO constructor
		this.context = newContext;	
		this.controller = new DatabaseController(newContext);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void refreshImageLib(){
		
		
		
		
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
