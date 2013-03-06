package ca.ualberta.c301w13t12recipes.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Recipe {
	private String id;
	private String user;
	private String name;
	private ArrayList<Ingredient> ingredients;
	private String directions;

	public Recipe(){
		
	}

	public Recipe(String id, String user, String name, ArrayList<Ingredient> ingredients,
			String directions) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.ingredients = ingredients;
		this.directions = directions;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", user=" + user + ", name=" + name + ", ingredients="
				+ ingredients + ", directions=" + directions + "]";
	}

	public Object toJson() {
		JSONObject jsonObject = new JSONObject();
		/*jsonObject.put("name", getName());
		jsonObject.put("user", getUser());
		//jsonObject.put("description", getDescription());
		jsonObject.put("id", getId());*/
		//List<imagin> responses = getResponses();
		JSONArray arr = new JSONArray();
		/*for (Response response : responses)
		{
			JSONObject jo = new JSONObject();
			jo.put("annotation", response.getAnnotation());
			jo.put("content", response.getSaveable());
			jo.put("timestamp", response.getTimestamp());
			arr.put(jo);
		}*/
		try {
			jsonObject.put("responses", arr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
	
}

