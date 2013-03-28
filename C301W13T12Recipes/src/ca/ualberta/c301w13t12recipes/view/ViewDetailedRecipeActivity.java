package ca.ualberta.c301w13t12recipes.view;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.c301w13t12recipes.R;
import ca.ualberta.c301w13t12recipes.controller.ShareController;
import ca.ualberta.c301w13t12recipes.controller.GalleryAdapter;
import ca.ualberta.c301w13t12recipes.controller.IngredientsAdapter;
import ca.ualberta.c301w13t12recipes.model.Image;
import ca.ualberta.c301w13t12recipes.model.Recipe;
import ca.ualberta.c301w13t12recipes.view.AddIngredWizardActivity.AddIngredDiaglogFragment;

/**
 * This is the activity, which is provided a view of each entry includes the
 * image , name of user , description
 * 
 * @author YUWEI DUAN
 */
public class ViewDetailedRecipeActivity extends Activity {
	private Gallery gallery;;
	private ImageButton editButton;
	private ImageButton shareButton;
	private IngredientsAdapter adapter;
	// private Gallery gallery;
	private Recipe recipe;
	private TextView titleTextView;
	private TextView descTextView;
	private ListView ingredListView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_entry);
		setupWidgets();
		getRecipe();

		refreshGallery();
		showName();
		showDescription();
		refreshList();

		editButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!recipe.getPassWord().equals("")) {
					DialogFragment newFragment = new AuthenticationDiaglogFragment();
					newFragment.show(getFragmentManager(), "AUTHENTICATION");
				}else{
					jumpToAddTitleDescWizardActivity();
				}
			}
		});
		shareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(ShareController.SendEmail(recipe));
			}

		});
	}

	/**
	 * Set up the new component
	 */
	private void setupWidgets() {
		gallery = (Gallery) findViewById(R.id.view_entry_gallery);
		editButton = (ImageButton) findViewById(R.id.view_edit_imageButton);
		shareButton = (ImageButton) findViewById(R.id.view_share_imageButton);
		titleTextView = (TextView) findViewById(R.id.view_textView_title);
		descTextView = (TextView) findViewById(R.id.view_textView_description);
		ingredListView = (ListView) findViewById(R.id.view_ingredients_listView);
	}

	private void getRecipe() {
		recipe = (Recipe) getIntent().getSerializableExtra("LOCAL_RECIPE");
	}

	private void refreshList() {
		adapter = new IngredientsAdapter();
		ingredListView.setAdapter(adapter.getAdapter(this,
				recipe.getIngredients()));

	}

	private void refreshGallery() {
		gallery.setAdapter(new GalleryAdapter(ViewDetailedRecipeActivity.this,
				(ArrayList<Image>) recipe.getImage()));
	}

	private void showDescription() {
		descTextView.setText(new String(recipe.getDirections()));
	}

	private void showName() {
		titleTextView.setText(new String(recipe.getName()));
	}

	class AuthenticationDiaglogFragment extends DialogFragment {
		private EditText passwordEditText;
		String password;

		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			LayoutInflater inflater = getActivity().getLayoutInflater();
			final View v = inflater.inflate(
					R.layout.dialog_edit_authentication, null);
			builder.setView(v);
			passwordEditText = (EditText) v
					.findViewById(R.id.view_authentication_textEdit);
			inflater.inflate(R.layout.dialog_edit_authentication, null);
			builder.setPositiveButton("Confirm",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int id) {
							checkPassword(passwordEditText.getText().toString());
						}
					}).setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							AuthenticationDiaglogFragment.this.getDialog()
									.cancel();

						}
					});
			return builder.create();
		}

	}

	private void checkPassword(String prompt) {
		if (recipe.getPassWord().equals(prompt)) {
			jumpToAddTitleDescWizardActivity();
		} else {
			Toast warning = Toast.makeText(getApplicationContext(),
					"Your password is incorrect.Please try again", 2);
			warning.show();
		}
	}

	private void jumpToAddTitleDescWizardActivity() {

		Intent intent = new Intent();
		intent.setClass(ViewDetailedRecipeActivity.this,
				AddTitleDescWizardActivity.class);
		startActivity(intent);
	}
}
