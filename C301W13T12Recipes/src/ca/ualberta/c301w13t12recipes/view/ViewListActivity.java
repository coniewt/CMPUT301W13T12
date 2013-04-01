package ca.ualberta.c301w13t12recipes.view;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.controller.RecipeAdapter;
import ca.ualberta.c301w13t12recipes.model.Recipe;

/**
 * Activity class for listview
 * @author GUANQI HUANG & YUWEI DUAN
 */
public class ViewListActivity extends Activity {

	/** Called when the activity is first created. */
	private ListView listView;
	private RecipeAdapter adapter;
	private DatabaseController controller;
	private Button deleteAllButton;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.tesing_list_view);
	    controller = new DatabaseController(this);
	    this.setupWidgets();
	    this.refreshList();
	    listView.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				jumpToAddViewDetailRecipeActivity(pos);
				
			}
		});
	    
	    deleteAllButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO delete all the items from listView
				controller.removeAll();
				refreshList();
			}
		});
	    
	    
	    
	    // TODO Auto-generated method stub
	}
	private void setupWidgets() {
		listView= (ListView) findViewById(R.id.testing_view_listview);
		deleteAllButton = (Button)findViewById(R.id.testing_view_button_delete_all);
	}
	private void refreshList() {
		adapter = new RecipeAdapter();
		ListAdapter la = adapter.getAdapter(ViewListActivity.this,"All",null);
		listView.setAdapter(la);
	}
	/**
	 * Jump to AddViewDetailRecipeActivity 
	 * @param index
	 */
	private void jumpToAddViewDetailRecipeActivity(int index) {
		Intent intent = new Intent(ViewListActivity.this,
		ViewDetailedRecipeActivity.class);
		
		List<Recipe> recipeList =(new DatabaseController(this)).getDB().getLocal_Recipe_List();
		Recipe recipe = recipeList.get(index);
		
		Bundle bundle = new Bundle();
		bundle.putSerializable("LOCAL_RECIPE",recipe);
		intent.putExtras(bundle);
		startActivity(intent);
		//finish();
	}
}
