package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.RecipeAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SearchActivity extends Activity{
	EditText keyword_edittext;
	ImageButton search_imagebutton;
	ListView result_listview;
	String keyword;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setUp();
		search_imagebutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				keyword = keyword_edittext.getText().toString();
				if(keyword.length()>0){
					result_listview = (ListView)findViewById(R.id.searchResult_listView);
					RecipeAdapter adapter = new RecipeAdapter();
					ListAdapter la = adapter.getAdapter(arg0.getContext(),keyword);
					result_listview.setAdapter(la);
				}
			}
		});
	}
	/**
	 * Set up the view
	 */
	public void setUp(){
		keyword_edittext = (EditText)findViewById(R.id.keyword_editText1);
		result_listview = (ListView)findViewById(R.id.searchResult_listView);
		search_imagebutton = (ImageButton)findViewById(R.id.search_imageButton1);
	}
}
