package br.com.aeho.appoftests;

import org.json.JSONArray;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

public class VolleyActivity extends ActionBarActivity {
	private static final String url = "http://www.json-generator.com/j/clQcnKVEvC?indent=4";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.volleyactivity);
		final TextView tvResultado = (TextView) findViewById(R.id.volleyactivity_tvResultado);
		final ImageView ivImagem = (ImageView) findViewById(R.id.volleyactivity_ivImagem);
		final Response.Listener respostaListener = new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				tvResultado.setText(response.toString());
			}
		};

		final Response.ErrorListener errorListener = new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("VolleyActivity", error.getMessage());
				error.printStackTrace();
			}
		};
		final JsonArrayRequest jsObjReq = new JsonArrayRequest(url,
				respostaListener, errorListener);
		ApplicationController.getInstance().addToRequestQueue(jsObjReq);

	}

}
