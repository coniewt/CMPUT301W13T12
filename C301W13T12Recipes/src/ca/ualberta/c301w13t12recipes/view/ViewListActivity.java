package ca.ualberta.c301w13t12recipes.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.controller.RecipeAdapter;
import ca.ualberta.c301w13t12recipes.model.Image;
import ca.ualberta.c301w13t12recipes.model.Recipe;

/**
 * Activity class for listview
 * 
 */
public class ViewListActivity extends Activity {

	/** Called when the activity is first created. */
	private ListView lv;
	private RecipeAdapter adapter;
	private DatabaseController controller;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.activity_view_list);
	    lv= (ListView) findViewById(R.id.view_listview);
	    lv.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				jumpToAddViewDetailRecipeActivity(arg2);
			}
		});
	    refreshList();
	    // TODO Auto-generated method stub
	}
	
	private void refreshList() {
		adapter = new RecipeAdapter();
		ListAdapter la = adapter.getAdapter(this);
		lv.setAdapter(la);
	}
	/**
	 * Jump to AddViewDetailRecipeActivity 
	 * @param index
	 */
	private void jumpToAddViewDetailRecipeActivity(int index) {
		Intent intent = new Intent(ViewListActivity.this,
		ViewDetailedRecipeActivity.class);
		List<Recipe> li =(new DatabaseController(this)).getDB().getLocal_Recipe_List();
		Bundle bundle = new Bundle();
		if(li.get(index).getImage().size()>0)
			bundle.putSerializable("IMAGE", (Serializable) li.get(index).getImage());
		else 
			bundle.putSerializable("IMAGE", (Serializable) new ArrayList<Image>());
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
