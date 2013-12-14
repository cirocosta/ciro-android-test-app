package br.com.aeho.appoftests;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.adapters.NumericWheelAdapter;
import eu.inmite.android.lib.dialogs.BaseDialogFragment;
import eu.inmite.android.lib.dialogs.SimpleDialogFragment;

public class ListWithPickers extends ActionBarActivity implements
		View.OnClickListener {

	private static final String[] data = { "Ciro", "Mateus", "Matheus",
			"Vitxor" };
	private static final int ACTV_FOR_RESULT_CODE = 111;
	final FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listwithpickers);
		final ListView lista = (ListView) findViewById(R.id.listwithpickers_list);
		lista.setAdapter(new ListWithPickersAdapter(ListWithPickers.this,
				R.layout.listwithpickers_row, data));
		((Button) findViewById(R.id.listwithpickers_button))
				.setOnClickListener(this);
	}

	private OnClickListener buttonClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			mostraDialog(fm);
		}
	};

	private static void mostraDialog(FragmentManager fm) {
		final PesoDialog dfrag = PesoDialog.newInstance();
		dfrag.show(fm, "dialog");
	}

	private class ListWithPickersAdapter extends ArrayAdapter<String> {

		private final Context context;
		private final int resource;
		private final String[] data;

		public ListWithPickersAdapter(Context context, int resource,
				String[] objects) {
			super(context, resource, objects);
			this.context = context;
			this.resource = resource;
			this.data = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			ListHolder holder = null;

			if (row == null) {
				LayoutInflater inflater = ((Activity) context)
						.getLayoutInflater();
				row = inflater.inflate(resource, parent, false);

				holder = new ListHolder();
				holder.nome = (TextView) row
						.findViewById(R.id.listwithpickers_row_tv);
				holder.botao = (Button) row
						.findViewById(R.id.listwithpickers_row_button);

				row.setTag(holder);
			} else {
				holder = (ListHolder) row.getTag();
			}

			holder.nome.setText(data[position]);
			holder.botao.setOnClickListener(buttonClickListener);
			return row;
		}
	}

	
	
	private class ListHolder {
		TextView nome;
		Button botao;
	}

	public static class PesoDialog extends SimpleDialogFragment {
		static PesoDialog newInstance() {
			PesoDialog d = new PesoDialog();
			return d;
		}
		
		@Override
		public BaseDialogFragment.Builder build(
				BaseDialogFragment.Builder builder) {
			final View view = LayoutInflater.from(getActivity()).inflate(
					R.layout.listwithpickers_dialog, null);
			final AbstractWheel hours = (AbstractWheel) view
					.findViewById(R.id.listwithpickers_hour_horizontal);
			final NumericWheelAdapter hourAdapter = new NumericWheelAdapter(
					getActivity(), 0, 100);
			hourAdapter.setItemResource(R.layout.utils_white_textview);
			hourAdapter.setItemTextResource(R.id.utils_text);
			hours.setViewAdapter(hourAdapter);
			hours.setCurrentItem(10);
			builder.setTitle("Peso");
			builder.setView(view);
			builder.setPositiveButton("Ok", new OnClickListener() {

				@Override
				public void onClick(View v) {
					dismiss();
				}
			});
			return builder;
		}
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.listwithpickers_button:
			startActivityForResult(new Intent(ListWithPickers.this,
					ListWithPickersResult.class), ACTV_FOR_RESULT_CODE);
			break;
		}
	}

}
