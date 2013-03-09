package ca.ualberta.c301w13t12recipes.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author YUWEI DUAN
 *
 */
public class Recipe {
	private String id;
	private String user;
	private String name;
	private List<Ingredient> ingredients;
	private List<Image> ImageCollection;
	private String directions;

	/**
	 * 
	 */
	public Recipe() {

	}

	/**
	 * @param id
	 * @param user
	 * @param name
	 * @param list
	 * @param directions
	 */
	public Recipe(String id, String user, String name,
			List<Ingredient> list, String directions) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.ImageCollection = new ArrayList<Image>();
		this.ingredients = list;
		this.directions = directions;
	}
	/**
	 * @param path
	 */
	public void addImage(String path){
		this.ImageCollection.add(new Image(path));
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return list of image
	 */
	public List<Image> getImage() {
		return this.ImageCollection;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return a list of ingredients
	 */
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * @return directions
	 */
	public String getDirections() {
		return directions;
	}

	/**
	 * @param directions
	 */
	public void setDirections(String directions) {
		this.directions = directions;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", user=" + user + ", name=" + name
				+ ", ingredients=" + ingredients + ", directions=" + directions
				+ "]";
	}

	/**
	 * @return Json object
	 */
	public Object toJson() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("name", getName());
			jsonObject.put("user", getUser());
			jsonObject.put("directions", getDirections());
			jsonObject.put("id", getId());
			
			JSONArray arr = new JSONArray();
			JSONArray image_arr = new JSONArray();
			if (getImage().size()>1){
				for(Image im:getImage()){
					image_arr.put(im.toJson());
				}
			}
			jsonObject.put("image",image_arr );
			for (Ingredient in : getIngredients()) {
				JSONObject jo = new JSONObject();
				jo.put("name", in.getNmae());
				jo.put("amount", in.getAcount());
				arr.put(jo);
			}
			jsonObject.put("Ingredients", arr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

}
