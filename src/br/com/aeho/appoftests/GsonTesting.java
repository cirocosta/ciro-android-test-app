package br.com.aeho.appoftests;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class GsonTesting extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final TextView tvTexto = (TextView) findViewById(R.id.gson_testing_tvTexto);
		tvTexto.setText(get_text_from_json());
	}

	private CharSequence get_text_from_json() {
		return null;
	}

}
