package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends Activity{
	EditText keyword_edittext;
	ListView result_listview;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		}
	public void setUp(){
		
	}
}
