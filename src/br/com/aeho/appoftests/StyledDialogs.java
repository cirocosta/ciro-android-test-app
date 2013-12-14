package br.com.aeho.appoftests;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import eu.inmite.android.lib.dialogs.BaseDialogFragment;
import eu.inmite.android.lib.dialogs.ISimpleDialogListener;
import eu.inmite.android.lib.dialogs.SimpleDialogFragment;

@SuppressLint("ValidFragment")
public class StyledDialogs extends FragmentActivity implements
		View.OnClickListener, ISimpleDialogListener {

	static int FECHAR_DIALOG = 0;
	static int DIALOG_ID = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.styleddialog);
		((Button) findViewById(R.id.styleddialog_quickuse))
				.setOnClickListener(this);
		((Button) findViewById(R.id.styleddialog_custom))
				.setOnClickListener(this);
		((Button) findViewById(R.id.styleddialog_bTimePicker))
				.setOnClickListener(this);
		if (savedInstanceState != null) {

		}
	}

	public static class Dialogo extends SimpleDialogFragment {

		static Dialogo newInstance() {
			Dialogo d = new Dialogo();
			return d;
		}

		@Override
		public void onStart() {
			super.onStart();
			getPositiveButton().setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (FECHAR_DIALOG == 0) {
						FECHAR_DIALOG = 1;
						Crouton.makeText(getActivity(),
								"Clique novamente para fechar", Style.INFO,
								(ViewGroup) getView()).show();
					} else {
						FECHAR_DIALOG = 0;
						dismiss();
					}
				}
			});
		}

		@Override
		public BaseDialogFragment.Builder build(
				BaseDialogFragment.Builder builder) {

			View view = LayoutInflater.from(getActivity()).inflate(
					R.layout.styleddialogs_dialog, null);
			final RatingBar rbRating = (RatingBar) view
					.findViewById(R.id.dialog_rbRating);
			rbRating.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

				@Override
				public void onRatingChanged(RatingBar ratingBar, float rating,
						boolean fromUser) {
					Log.v("StyledDialogs", "RATING: " + String.valueOf(rating));
				}
			});
			builder.setTitle("Jayne's hat")
					.setView(view)
					.setPositiveButton("I want one",
							new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									ISimpleDialogListener listener = getDialogListener();
									if (listener != null) {
										listener.onPositiveButtonClicked(0);
									}
									dismiss();
								}
							});
			return builder;
		}
	}

	public static class TimePickerDialog extends DialogFragment {

		static TimePickerDialog newInstance() {
			TimePickerDialog d = new TimePickerDialog();
			return d;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			LayoutInflater inflater = getActivity().getLayoutInflater();
			View view = inflater.inflate(R.layout.styleddialogs_timepicker,
					null);
			builder.setView(view).setTitle("TimePicker");
			TimePicker tp = (TimePicker) view
					.findViewById(R.id.styleddialogs_timepicker_tp);
			tp.setIs24HourView(true);
			tp.setOnTimeChangedListener(new OnTimeChangedListener() {

				@Override
				public void onTimeChanged(TimePicker view, int hourOfDay,
						int minute) {
					Log.v("TimePicker", String.valueOf(hourOfDay) + " : "
							+ String.valueOf(minute));
				}
			});
			return builder.create();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.styleddialog_quickuse:
			SimpleDialogFragment
					.createBuilder(this, getSupportFragmentManager())
					.setTitle("Meu Titulo").setMessage("Mensagem feliz")
					.setPositiveButtonText("positive").setRequestCode(10)
					.setNegativeButtonText("negative").show();
			break;

		case R.id.styleddialog_custom:
			Dialogo dfrag = Dialogo.newInstance();
			Log.v("Custom dialog", String.valueOf(dfrag.getId()));
			dfrag.show(getSupportFragmentManager(), "dialog");
			break;

		case R.id.styleddialog_bTimePicker:
			TimePickerDialog tpDialog = TimePickerDialog.newInstance();
			Log.v("TImePickerdialog", String.valueOf(tpDialog.getId()));
			tpDialog.show(getSupportFragmentManager(), "timepicker");
			break;
		}
	}

	@Override
	public void onPositiveButtonClicked(int requestCode) {
		Log.v("StyledDialog", String.valueOf(requestCode));
	}

	@Override
	public void onNegativeButtonClicked(int requestCode) {
		Log.v("StyledDialog", String.valueOf(requestCode));
	}
}
