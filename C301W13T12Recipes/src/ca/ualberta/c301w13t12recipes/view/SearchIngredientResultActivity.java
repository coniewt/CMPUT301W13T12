package ca.ualberta.c301w13t12recipes.view;

import java.util.ArrayList;
import android.app.ListActivity;
import android.os.Bundle;
import ca.ualberta.c301w13t12recipes.controller.RecipeAdapter;
import ca.ualberta.c301w13t12recipes.model.Ingredient;
import ca.ualberta.c301w13t12recipes.model.StrResource;
import ca.ualberta.c301w13t12recipes.R;;
/**
 * @author YUWEI DUAN
 *
 */
public class SearchIngredientResultActivity extends ListActivity {
	private ArrayList<Ingredient> ar = new ArrayList<Ingredient>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_search_ingre_result);
        getIngredientListFromIntent();
        // Use our own list adapter
        setListAdapter(new RecipeAdapter().getAdapter(this, "INGREDIENT_",ar));
    }
    /**
     * To initial the ingredient list from intent
     */
    @SuppressWarnings("unchecked")
	private void getIngredientListFromIntent(){
    	ar = (ArrayList<Ingredient>) getIntent().getSerializableExtra(StrResource.INTENT_INGREDIENT_LIST_KEY);
    }
}