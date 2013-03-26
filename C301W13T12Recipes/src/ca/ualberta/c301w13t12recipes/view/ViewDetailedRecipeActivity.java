package ca.ualberta.c301w13t12recipes.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.ImageAdapter;
import ca.ualberta.c301w13t12recipes.model.Image;

/**
 * This is the activity, which is provided a view of each entry includes the
 * image , name of user , description
 * 
 * @author YUWEI DUAN
 */
public class ViewDetailedRecipeActivity extends Activity {
	private Gallery gallery;
	private ArrayList<Image> imageList;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_entry);
		getImageList();
		setUp();
		//registerForContextMenu(gallery);
		// TODO Auto-generated method stub
		gallery.setAdapter(new ImageAdapter(this));
		// DatabaseController(this)).getDB().get));
		gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onNothingSelected(AdapterView<?> arg0) {

			}

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
			}
		});
	}

	/**
	 * Set up the new component
	 */
	private void setUp() {
		gallery = (Gallery)findViewById(R.id.gallery1);
	}

	@SuppressWarnings("unchecked")
	/**
	 * To get the list of image from intend 
	 * from addpicwizardact
	 */
	private void getImageList() {
		imageList = (ArrayList<Image>) getIntent()
				.getSerializableExtra("IMAGE");
	}

	class ImageAdapter extends BaseAdapter {
		private static final int ITEM_WIDTH = 136;
		private static final int ITEM_HEIGHT = 88;

		private final int mGalleryItemBackground;
		private final Context mContext;

		private final Integer[] mImageIds = { R.drawable.gallery_photo_1,
				R.drawable.gallery_photo_2, R.drawable.gallery_photo_3,
				R.drawable.gallery_photo_4, R.drawable.gallery_photo_5,
				R.drawable.gallery_photo_6, R.drawable.gallery_photo_7,
				R.drawable.gallery_photo_8 };

		private final float mDensity;

		public ImageAdapter(Context c) {
			mContext = c;
			// See res/values/attrs.xml for the <declare-styleable> that defines
			// Gallery1.
			TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
			 mGalleryItemBackground = a.getResourceId(
			 R.styleable.Gallery1_android_galleryItemBackground, 0);
			a.recycle();

			mDensity = c.getResources().getDisplayMetrics().density;
		}

		public int getCount() {
			return mImageIds.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {
				convertView = new ImageView(mContext);

				imageView = (ImageView) convertView;
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				imageView.setLayoutParams(new Gallery.LayoutParams(
						(int) (ITEM_WIDTH * mDensity + 0.5f),
						(int) (ITEM_HEIGHT * mDensity + 0.5f)));

				// The preferred Gallery item background
				imageView.setBackgroundResource(mGalleryItemBackground);
			} else {
				imageView = (ImageView) convertView;
			}

			imageView.setImageResource(mImageIds[position]);

			return imageView;
		}
	}
}
