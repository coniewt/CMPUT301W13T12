package ca.ualberta.c301w13t12recipes.view;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.controller.RecipeAdapter;
import ca.ualberta.c301w13t12recipes.controller.WebController;
import ca.ualberta.c301w13t12recipes.controller.WebStream;
import ca.ualberta.c301w13t12recipes.model.Recipe;

public class SearchActivity extends Activity {
	//declare the view components
	MultiAutoCompleteTextView keyword_edittext;
	ImageButton search_imagebutton;
	ListView result_listview;
	String keyword;
	CheckBox checkbox;
	Button testButton;
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		//initial the view components
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		//set thread policy
		setUp();
		prepareAutoCompleteText();
		testWeb();
		
		search_imagebutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				keyword = keyword_edittext.getText().toString();
				if (keyword.length() > 0) {
					if(checkbox.isChecked())
						refreshListview("web_"+keyword);
					else 
						refreshListview(keyword);
					/*result_listview = (ListView) findViewById(R.id.searchResult_listView);
					RecipeAdapter adapter = new RecipeAdapter();
					ListAdapter la = adapter.getAdapter(arg0.getContext(),
							keyword);
					result_listview.setAdapter(la);*/
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
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if(arg1==true){
					System.out.println((new WebController()).isNetworkAvailable(arg0.getContext()));
					if((new WebController()).isNetworkAvailable(arg0.getContext())){
						
						
						Toast.makeText(arg0.getContext(), "Successfuls to connect the Internet", Toast.LENGTH_SHORT).show();
					}
					else{
						Toast.makeText(arg0.getContext(), "Fail to connect the Internet", Toast.LENGTH_LONG).show();
						checkbox.setChecked(false);
					}
				}
		}});
		
	}

	/**
	 * Set up the view
	 */
	public void setUp() {
		keyword_edittext = (MultiAutoCompleteTextView) findViewById(R.id.keyword_autoCompleteTextView1);
		result_listview = (ListView) findViewById(R.id.searchResult_listView);
		search_imagebutton = (ImageButton) findViewById(R.id.search_imageButton1);
		checkbox = (CheckBox)findViewById(R.id.search_web_recipe_checkbox);
	}
	/**
	 * Jump to the class and send the recipe by intent
	 * @param the index from listview index
	 */
	private void jumpToAddViewDetailRecipeActivity(int index) {
		Intent intent = new Intent(SearchActivity.this,
		ViewDetailedRecipeActivity.class);
		//Log.v("Test+++",(String) ((HashMap)result_listview.getItemAtPosition(index)).get("name"));
		@SuppressWarnings("unchecked")
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
	/**
	 * To prepare the multiCompleteText
	 */
	private void prepareAutoCompleteText(){
		List<String> autoCompleteList = (new DatabaseController(this)).getNameList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		     android.R.layout.simple_dropdown_item_1line, autoCompleteList);
		keyword_edittext.setAdapter(adapter);
		keyword_edittext.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}
	private void refreshListview(String key){
		result_listview = (ListView) findViewById(R.id.searchResult_listView);
		RecipeAdapter adapter = new RecipeAdapter();
		ListAdapter la = adapter.getAdapter(this,
				key);
		result_listview.setAdapter(la);
	}
	private void testWeb(){
		testButton = (Button)findViewById(R.id.testbutton);
		testButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Thread thread = new Thread()
				{
				    @Override
				    public void run() {
				    	Recipe testRecipe = new Recipe("Admin2", "Test_name2", "Test_direction2");
						try {
							Log.v("Recipe", testRecipe.toString());
							(new WebStream()).deleteRecipe();
								(new WebStream()).insertRecipe(testRecipe);
						}
						catch (Exception e){
							e.printStackTrace();	
						}
						}
				};
				thread.run();
				// TODO Auto-generated method stub
				
			}
		});
	}


	
}
