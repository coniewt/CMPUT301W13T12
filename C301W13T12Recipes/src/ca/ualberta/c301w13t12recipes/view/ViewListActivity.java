package ca.ualberta.c301w13t12recipes.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.fluent.Content;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.controller.IngredientsAdapter;
import ca.ualberta.c301w13t12recipes.controller.RecipeAdapter;
import ca.ualberta.c301w13t12recipes.model.Recipe;

/**
 * Activity class for listview
 * 
 */
public class ViewListActivity extends Activity {

	/** Called when the activity is first created. */
	private ListView lv;
	private RecipeAdapter adapter;

	private ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.activity_view_list);
	    lv= (ListView) findViewById(R.id.view_listview);
	    lv.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.v("hello",""+arg2);
				jumpToAddViewDetailRecipeActivity(arg2);
			}
		});
	    refreshList();
	    // TODO Auto-generated method stub
	}

	@Override
	public void onRestart() {
		super.onRestart();
		// refresh();
	}

	@Override
	public void onResume() {
		super.onResume();
		// refresh();
	}

	/*
	 * public void refresh() { try{ HashMap<String, Object> map = new
	 * HashMap<String, Object>(); for(Recipe re:re_list){ map.put("id",
	 * re.getId()); map.put("name", re.getName()); } map.put("id", "123");
	 * map.put("name", "adasdsa"); String[] from ={"id","name"}; int[] to
	 * ={R.id.view_id,R.id.view_name}; list.add(map); lv.setAdapter(new
	 * SimpleAdapter(this,list,R.layout.item_recipe,from,to)); } catch
	 * (Exception e){ e.printStackTrace(); } }
	 */
	private void refreshList() {
		adapter = new RecipeAdapter();
		ListAdapter la = adapter.getAdapter(this);
		lv.setAdapter(la);
	}
	private void jumpToAddViewDetailRecipeActivity(int index) {
		Intent intent = new Intent(ViewListActivity.this,
		ViewDetailedRecipeActivity.class);
		List<Recipe> li =(new DatabaseController(this)).getDB().getLocal_Recipe_List();
		Bundle bundle = new Bundle();
		bundle.putSerializable("IMAGE", (Serializable) li.get(index).getImage());
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
