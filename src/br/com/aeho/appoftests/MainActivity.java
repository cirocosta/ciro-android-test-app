package br.com.aeho.appoftests;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class MainActivity extends Activity {

	private Class classe;
	private ListView lvTestes;
	private ArrayAdapter arrayAdapter;
	ImageLoader imageLoader;
	ImageView ivBackground;
	final static String backgroundUrl = "http://gymup.com.br/media/acadciro/img/background.jpg";
	boolean TRANSITION_DONE = false;
	static BitmapDrawable gymBackgroundDrawable;

	private static final String[] atividades = { "GsonTesting","ActivityForResult",
			"Intents", "Services", "Notifications", "StyledDialogs",
			"UniversalImageLoader", "Maps", "PagerSliding", "ListWithPickers",
			"VolleyActivity" };
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
		teste_gson();

		set_adapters();
		prepare_image_loader();
		final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View croutonView = inflater
				.inflate(R.layout.cruoton_custom, null);

		Crouton.makeText(MainActivity.this, "cruoton", Style.INFO).show();

		lvTestes.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent i = new Intent();
				i.setClassName("br.com.aeho.appoftests",
						"br.com.aeho.appoftests." + atividades[position]);
				startActivity(i);
			}
		});

		if (savedInstanceState != null) {
			if (savedInstanceState.getBoolean("TRANSITION_DONE")) {
				if (gymBackgroundDrawable != null) {
					ivBackground.setImageDrawable(gymBackgroundDrawable);
					TRANSITION_DONE = true;
					return;
				}
			}
		}
		imageLoader.loadImage(backgroundUrl, new ImageLoadingListener() {

			@Override
			public void onLoadingStarted(String imageUri, View view) {

			}

			@Override
			public void onLoadingFailed(String imageUri, View view,
					FailReason failReason) {

			}

			@Override
			public void onLoadingComplete(String imageUri, View view,
					Bitmap loadedImage) {

				gymBackgroundDrawable = new BitmapDrawable(loadedImage);
				change_background_image(ivBackground.getDrawable(),
						gymBackgroundDrawable, ivBackground);
			}

			@Override
			public void onLoadingCancelled(String imageUri, View view) {

			}
		});

	}

	
	private void teste_gson() {
		final String sJson = "{\"pessoa\":[{\"nome\":\"ciro\",\"sobrenome\":\"costa\"}]}";
		Gson gson = new Gson();
		Class tipo = GsonObjects.Tribo.class;
		GsonObjects.Tribo tribo = gson.fromJson(sJson, tipo);
		for (GsonObjects.Pessoa pessoa : tribo.pessoa) {
			Log.v("GSON", pessoa.toString());
		}
	}

	private void change_background_image(Drawable drawable0,
			Drawable drawable1, ImageView iv) {
		final TransitionDrawable td = new TransitionDrawable(new Drawable[] {
				drawable0, drawable1 });
		iv.setImageDrawable(td);
		td.setCrossFadeEnabled(true);
		td.startTransition(500);
		TRANSITION_DONE = true;
	}

	private void prepare_image_loader() {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisc(true).build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).defaultDisplayImageOptions(
				defaultOptions).build();
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(config);
	}

	private void set_adapters() {
		arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, atividades);
		lvTestes.setAdapter(arrayAdapter);
	}

	private void initialize() {
		ivBackground = (ImageView) findViewById(R.id.main_ivBackground);
		lvTestes = (ListView) findViewById(R.id.main_lvTestes);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("TRANSITION_DONE", TRANSITION_DONE);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Crouton.cancelAllCroutons();
	}

}
