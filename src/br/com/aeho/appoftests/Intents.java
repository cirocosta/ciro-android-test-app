package br.com.aeho.appoftests;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Intents extends Activity {

	private ListView lista;
	private static final String[] intents = {"Contacts","Calendar","Geolocation","Email","Webpage","Share"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intents);
		initialize();
		set_adapters();
	}

	private void set_adapters() {
		lista.setAdapter(new ArrayAdapter<String>(Intents.this,
				android.R.layout.simple_list_item_1, intents));
	}

	private void initialize() {
		lista = (ListView) findViewById(R.id.intents_lista);
	}

}
