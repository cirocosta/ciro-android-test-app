package br.com.aeho.appoftests;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

public class ActivityForResultTextit extends Activity {

	private EditText etTexto;
	private ActionBar actionbar;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		actionbar = getActionBar();
		// actionbar.setDisplayShowHomeEnabled(false);
		// actionbar.setDisplayShowTitleEnabled(false);
		// actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		// actionbar.setCustomView(R.layout.textit_actionbar);
		setContentView(R.layout.activity_for_result_textit);
		initialize();

//		LayoutInflater inflater = (LayoutInflater) getActionBar()
//				.getThemedContext().getSystemService(LAYOUT_INFLATER_SERVICE);
		// final View customActionbarView = inflater.inflate(
		// R.layout.actionbar_custom_view_done_discard, null);

	}

	private void initialize() {
		etTexto = (EditText) findViewById(R.id.actv_for_result_textit_etTexto);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.textit_menu, menu);
		return true;
	}
	/*
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * item selection Intent result = new Intent(); switch (item.getItemId()) {
	 * case R.id.textit_menu_cancelar: result.putExtra("text", "");
	 * setResult(Activity.RESULT_CANCELED, result); finish(); return true; case
	 * R.id.textit_menu_salvar: result.putExtra("text",
	 * etTexto.getText().toString()); setResult(Activity.RESULT_OK, result);
	 * finish(); return true; default: return super.onOptionsItemSelected(item);
	 * } }
	 */

}
