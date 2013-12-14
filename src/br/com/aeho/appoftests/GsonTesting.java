package br.com.aeho.appoftests;

import com.google.gson.Gson;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

public class GsonTesting extends ActionBarActivity {

	private static final String TAG = "GsonTesting";
	private static final String sJson = "{\"pessoa\":[{\"nome\":\"ciro\",\"sobrenome\":\"costa\"},{\"nome\":\"joao\",\"sobrenome\":\"felizardo\"},{\"nome\":\"maria\",\"sobrenome\":\"joaquina\"}],\"individuo\":[{\"alma\":\"feliz\"}]}";
	private static final String sJson2 = "[{\"operacao\":\"INSERT\",\"valores\":[\"gosto\",\"felicidade\",\"lol\"],\"colunas\":[\"tipo\",\"idade\",\"alegria\"]},{\"operacao\":\"UPDATE\",\"valores\":[\"das\",\"asa\",\"sda\"],\"colunas\":[\"12312\",\"53412\",\"21312\"]}]";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gson_testing);
		Log.v(TAG, sJson);

		((TextView) findViewById(R.id.gson_testing_tvTextoJson)).setText(sJson);
		((TextView) findViewById(R.id.gson_testing_tvTexto))
				.setText(get_text_from_json(sJson));

		((TextView) findViewById(R.id.gson_testing_tvTextoJson2))
				.setText(sJson2);
		((TextView) findViewById(R.id.gson_testing_tvTexto2))
				.setText(get_text_from_json2(sJson2));

	}

	private CharSequence get_text_from_json(String jsonString) {
		final StringBuilder sb = new StringBuilder();
		final Gson gson = new Gson();
		final Class tipo = GsonObjects.Tribo.class;

		try {
			GsonObjects.Tribo tribo = gson.fromJson(jsonString, tipo);
			sb.append("Pessoas\n------\n");
			for (GsonObjects.Pessoa pessoa : tribo.pessoa) {
				sb.append(pessoa.toString() + "\n");
				Log.v(TAG, "pessoa: " + pessoa.toString());
			}
			sb.append("\nIndividuos\n------\n");
			for (GsonObjects.Individuo individuo : tribo.individuo) {
				sb.append(individuo.toString() + "\n");
				Log.v(TAG, "individuo: " + individuo.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
		return sb.toString();
	}

	private CharSequence get_text_from_json2(String jsonString) {
		final StringBuilder sb = new StringBuilder();
		final Gson gson = new Gson();
		final Class tipo = GsonObjects.AuditObject[].class;

		try {
			GsonObjects.AuditObject[] auditObject = gson.fromJson(jsonString,
					tipo);
			sb.append("Audit\n-------\n");
			for (GsonObjects.AuditObject audit : auditObject) {
				sb.append(audit.toString() + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
		return sb.toString();
	}

}
