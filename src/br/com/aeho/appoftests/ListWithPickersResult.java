package br.com.aeho.appoftests;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListWithPickersResult extends ActionBarActivity implements
		View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final LayoutInflater inflater = (LayoutInflater) getSupportActionBar()
				.getThemedContext().getSystemService(LAYOUT_INFLATER_SERVICE);

		final View customActionBarView = inflater.inflate(
				R.layout.actionbar_custom_view_done_cancel, null);
		customActionBarView.findViewById(R.id.actionbar_done)
				.setOnClickListener(this);
		customActionBarView.findViewById(R.id.actionbar_cancel)
				.setOnClickListener(this);

		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM,
				ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME
						| ActionBar.DISPLAY_SHOW_TITLE);
		actionBar.setCustomView(customActionBarView,
				new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.MATCH_PARENT));
		setContentView(R.layout.listwithpickers_result);

		// setResult(resultCode, data);
	}

	@Override
	public void onClick(View v) {
		finish();
	}
}
