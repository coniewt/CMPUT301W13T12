package ca.ualberta.c301w13t12testproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.ualberta.c301w13t12recipes.model.Recipe;

public class TestLocal {
	@Test
	public void testEquals() {
		/* Fixture */
		Recipe book1 = new Recipe("12345", "Title1", "Desc 1","123");
		Recipe book1m = new Recipe("12345", "Title1a", "Desc 1a","dsdf");
		Recipe book2 = new Recipe("98765", "Title2", "Desc 2","sdasd");
		
		
		/* Assertions */
		// A book must be equal to itself
		assertTrue(book1.equals(book1));

		// Books with the same isbn are consedered equal, even if their titles
		// and descriptions are different
		assertTrue(book1.equals(book1m));

		// Books with different isbn are not equal
		assertFalse(book1.equals(book2));
	}
}
