package ca.ualberta.c301w13t12testproject;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.model.Image;
import ca.ualberta.c301w13t12recipes.model.Ingredient;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import static org.junit.Assert.*;

/**
 * @author dw
 * 
 */
public class TestLocalDB{

	DatabaseController dbc = new DatabaseController(null);
	Recipe re ;
	@Before
	public void setUp() throws Exception {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		List<Image> ImageCollection = new ArrayList<Image>();
		re = new Recipe("123", "dw", "test recipe", "Direction");
		re.addIngredient("First test name", "First test amount");
		re.addIngredient("Second test name", "Second test amount");
		re.addImage("/photoPath");
		re.addImage("/phtotPath");
		
	}

/*	@Test
	public void testWriteLocalRecipe() {
		
	}*/

	@Test
	public void testReadLocalRecipe() {
		dbc.addRecipe(re);
		Recipe recipe_return = dbc.getDB().getLocal_Recipe_List().get(0);
		String name = recipe_return.getName();
		assertEquals(name, re.getName());
	}
}
