package ca.ualberta.c301w13t12recipes.test;

import android.test.AndroidTestCase;

/**
 * @author dw
 *
 */
public class TestWebservice  extends AndroidTestCase{
	Recipe book1, book1a, book1m, book2,recipe1;
	public void setUp() {
		book1 = new Recipe("Title1", "Desc 1", "123");
		book1a = new Recipe("Title1", "Desc 1", "123");
		book1m = new Recipe("Title1a", "Desc 1a", "dsdf");
		book2 = new Recipe("Title2", "Desc 2", "sdasd");
		
	}
	/**
	 * To test equal
	 */
	public void testEquals() {
		/* Fixture */

		/*
		 * Recipe book1 = new Recipe( "Title1", "Desc 1","123"); Recipe book1a =
		 * new Recipe( "Title1", "Desc 1","123"); Recipe book1m = new Recipe(
		 * "Title1a", "Desc 1a","dsdf"); Recipe book2 = new Recipe( "Title2",
		 * "Desc 2","sdasd");
		 */

		/* Assertions */
		// A book must be equal to itself
		assertTrue(book1.equals(book1));

		// Books with the same isbn are consedered equal, even if their titles
		// and descriptions are different
		// assertTrue(book1.equals(book1m));
		// Books with different isbn are not equal
		assertFalse(book1.getId().equals(book2.getId()));
	}
	/**
	 * 
	 */
	public void testAddrecipe(){
		//localdb.addLocal_Recipe_Table(book1);
		//recipe1 = localdb.get_Local_Recipe(book1.getId());
		assertSame(book1, book1);
	}
}
