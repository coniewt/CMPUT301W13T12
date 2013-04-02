package ca.ualberta.c301w13t12recipes.view;

import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.model.StrResource;
import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
/**
 * 
 * This activity shows the help document
 *
 */
public class HelpManualActivity extends Activity {
	private TextView manualTextView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_help_manual);
	    setupWidgets();
	    setText();
	    // TODO Auto-generated method stub
	}
	private void setupWidgets(){
		manualTextView = (TextView)findViewById(R.id.main_help_text);
		
	}
	private void setText(){
		manualTextView.setText((new StrResource()).MAIN_HELP_CONTENT);
		manualTextView.setMovementMethod(new ScrollingMovementMethod());


	}
	
}
