package ca.ualberta.c301w13t12recipes.view;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.RecipeAdapter;
import ca.ualberta.c301w13t12recipes.model.Ingredient;
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
	}

	/**
	 * To initial the ingredient list from intent
	 */
	@SuppressWarnings("unchecked")
	private void getIngredientListFromIntent() {
		ar = (ArrayList<Ingredient>) getIntent().getSerializableExtra(
				StrResource.INTENT_INGREDIENT_LIST_KEY);
	}
}