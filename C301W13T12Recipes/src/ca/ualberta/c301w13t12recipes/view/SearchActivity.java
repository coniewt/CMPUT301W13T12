package ca.ualberta.c301w13t12recipes.view;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.controller.RecipeAdapter;
import ca.ualberta.c301w13t12recipes.model.Recipe;

public class SearchActivity extends Activity {
	MultiAutoCompleteTextView keyword_edittext;
	ImageButton search_imagebutton;
	ListView result_listview;
	String keyword;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setUp();
		List<String> autoCompleteList = (new DatabaseController(this)).getNameList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		     android.R.layout.simple_dropdown_item_1line, autoCompleteList);
		keyword_edittext.setAdapter(adapter);
		keyword_edittext.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		search_imagebutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				keyword = keyword_edittext.getText().toString();
				if (keyword.length() > 0) {
					result_listview = (ListView) findViewById(R.id.searchResult_listView);
					RecipeAdapter adapter = new RecipeAdapter();
					ListAdapter la = adapter.getAdapter(arg0.getContext(),
							keyword);
					result_listview.setAdapter(la);
				}
			}
		});
		result_listview.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				jumpToAddViewDetailRecipeActivity(pos);
				
			}
		});
	}

	/**
	 * Set up the view
	 */
	public void setUp() {
		keyword_edittext = (MultiAutoCompleteTextView) findViewById(R.id.keyword_autoCompleteTextView1);
		result_listview = (ListView) findViewById(R.id.searchResult_listView);
		search_imagebutton = (ImageButton) findViewById(R.id.search_imageButton1);
	}
	private void jumpToAddViewDetailRecipeActivity(int index) {
		Intent intent = new Intent(SearchActivity.this,
		ViewDetailedRecipeActivity.class);
		Log.v("Test+++",(String) ((HashMap)result_listview.getItemAtPosition(index)).get("name"));
		String title = (String)((HashMap<String,String>)result_listview.getItemAtPosition(index)).get("name");
		List<Recipe> recipeList =(new DatabaseController(this)).getDB().getLocal_Recipe_List();
		Recipe recipe =null;
		for(int i=0;i<recipeList.size();i++){
			recipe= recipeList.get(i);
			if(recipe.getName().compareTo(title)==0){
				break;
				}
		}
		Bundle bundle = new Bundle();
		bundle.putSerializable("LOCAL_RECIPE",recipe);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
