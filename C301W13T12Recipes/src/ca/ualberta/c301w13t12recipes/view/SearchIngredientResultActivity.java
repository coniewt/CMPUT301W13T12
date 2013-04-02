package ca.ualberta.c301w13t12recipes.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.RecipeAdapter;
import ca.ualberta.c301w13t12recipes.controller.WebSearch;
import ca.ualberta.c301w13t12recipes.model.Ingredient;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import ca.ualberta.c301w13t12recipes.model.StrResource;

;
/**
 * @author YUWEI DUAN
 * 
 */
public class SearchIngredientResultActivity extends Activity {
	private ArrayList<Ingredient> ar = new ArrayList<Ingredient>();
	private ListView lv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_search_ingre_result);
		lv = (ListView)findViewById(R.id.view_ingre_result_listview);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		getIngredientListFromIntent();
		// Use our own list adapter
		Log.v("Adpter",""+(new RecipeAdapter().getAdapter(this, "INGREDIENT_", ar)==null));
		Log.v("list:",""+(ar.size()));
		lv.setAdapter(new RecipeAdapter().getAdapter(this, "INGREDIENT_", ar));
		lv.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
					//jumpToAddViewDetailRecipeActivity(pos,0);
			}

			
		});
	}

	/**
	 * To initial the ingredient list from intent
	 */
	@SuppressWarnings("unchecked")
	private void getIngredientListFromIntent() {
		ar = (ArrayList<Ingredient>) getIntent().getSerializableExtra(
				StrResource.INTENT_INGREDIENT_LIST_KEY);
	}
	private void jumpToAddViewDetailRecipeActivity(int pos, int i) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(SearchIngredientResultActivity.this,
				ViewDetailedRecipeActivity.class);
				ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
					Recipe recipe = new Recipe();
					try {
						//recipe = (new WebSearch()).searchRecipes(key, this).get(index);
					} catch (Exception e) {
					e.printStackTrace();
					}
					//System.out.println(recipe==null);
					Bundle bundle = new Bundle();
					bundle.putSerializable("WEB_RECIPE",recipe);
					intent.putExtras(bundle);
					startActivity(intent);
					return;
				}
}