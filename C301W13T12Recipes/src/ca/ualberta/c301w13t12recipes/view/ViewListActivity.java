package ca.ualberta.c301w13t12recipes.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.DatabaseController;
import ca.ualberta.c301w13t12recipes.model.Recipe;
public class ViewListActivity extends Activity {

	/** Called when the activity is first created. */
	ListView lv; 
	private ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.activity_view_listview);
	    lv= (ListView) findViewById(R.id.view_listview);
	    refresh();
	    // TODO Auto-generated method stub
	}
    @Override
    public void onRestart() {
    	super.onRestart();
    	//refresh();
    }
    @Override
    public void onResume() {
    	super.onResume();
    	//refresh();
    }
	public void refresh()
	{
		//List<Recipe> re_list;
		//re_list=(new DatabaseController(this)).getDB().getLocal_Recipe_List();
		try{
			HashMap<String, Object> map = new HashMap<String, Object>();
		/*for(Recipe re:re_list){
			map.put("id", re.getId());
			map.put("name", re.getName());
		}*/
		map.put("id", "123");
		map.put("name", "adasdsa");
		String[] from ={"id","name"};
		int[] to ={R.id.view_id,R.id.view_name};
		list.add(map);
		lv.setAdapter(new SimpleAdapter(this,list,R.layout.item_recipe,from,to));
		}
		catch (Exception e){
			e.printStackTrace()
		}
	}
}
