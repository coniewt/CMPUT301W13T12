package ca.ualberta.c301w13t12testproject;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.model.Image;
import ca.ualberta.c301w13t12recipes.model.Ingredient;
import ca.ualberta.c301w13t12recipes.model.Recipe;

public class TestLocal {
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

	@Test
	public void testAddLocal_Recipe_Table() {
		dbc.addRecipe(re);
	}

	@Test
	public void testGetLocal_Recipe_List() {
		Recipe recipe_return = dbc.getDB().getLocal_Recipe_List().get(0);
		String name = recipe_return.getName();
		assertEquals(name, re.getName());

	}

	@Test
	public void testDelete_Local_Recipe() {
		fail("Not yet implemented");
	}

}
