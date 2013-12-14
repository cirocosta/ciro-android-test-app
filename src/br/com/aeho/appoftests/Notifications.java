package br.com.aeho.appoftests;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Notifications extends Activity implements View.OnClickListener {

	private Button bLaunch, bAddValor, bKill;
	private int valor_notif = 0;
	public NotificationManager mNotifManager;
	public NotificationCompat.Builder mNotifBuilder;
	private static final int notifId = 1;
	PendingIntent pendingResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notifications);
		intialize();
		setListeners();

		Intent dataIntent = new Intent(Constants.NOTIF_INTENT);
		dataIntent.putExtra("data", "Button 1 was clicked");
		PendingIntent dataPIntent = PendingIntent.getBroadcast(this, 0,
				dataIntent, PendingIntent.FLAG_CANCEL_CURRENT);

		pendingResult = PendingIntent.getActivity(this, 1, new Intent(this,
				Notifications.class), 0);

		mNotifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotifBuilder = new NotificationCompat.Builder(this)
				.setContentTitle("Titulo da notificação")
				.setContentText("Texto do content")
				.setContentIntent(pendingResult).setAutoCancel(true)
				.addAction(R.drawable.ic_launcher, "Botao", dataPIntent)
				.addAction(R.drawable.ic_launcher, "Botao 2", pendingResult)
				.setSmallIcon(R.drawable.ic_launcher).setNumber(valor_notif);
	}

	@Override
	protected void onResume() {
		super.onResume();
		LocalBroadcastManager.getInstance(this).registerReceiver(
				mMessageReceiver, new IntentFilter(Constants.NOTIF_INTENT));
	}

	@Override
	protected void onPause() {
		super.onPause();
		LocalBroadcastManager.getInstance(this).unregisterReceiver(
				mMessageReceiver);
	}

	private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.v("Notifications", "received");
			Log.v("Notifications", intent.getStringExtra("data"));
		}
	};

	public void sendNotification() {
		mNotifManager.notify(notifId, mNotifBuilder.build());
	}

	public void updateNotification() {
		mNotifBuilder.setContentTitle(
				"Titulo da notificação " + String.valueOf(valor_notif))
				.setNumber(valor_notif);
		sendNotification();
	}

	public void killNotification() {
		mNotifManager.cancel(notifId);
	}

	private void setListeners() {
		bLaunch.setOnClickListener(this);
		bAddValor.setOnClickListener(this);
		bKill.setOnClickListener(this);
	}

	private void intialize() {
		bLaunch = (Button) findViewById(R.id.notifications_bLaunch);
		bAddValor = (Button) findViewById(R.id.notifications_bAddValor);
		bKill = (Button) findViewById(R.id.notifications_bKill);
	}

	private void toggle_visibility(View v, int status) {
		if (status == 1) {
			v.setVisibility(View.VISIBLE);
		} else {
			v.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.notifications_bAddValor:
			valor_notif += 1;
			bAddValor.setText("AddValue (" + String.valueOf(valor_notif) + ")");
			updateNotification();
			break;
		case R.id.notifications_bKill:
			toggle_visibility(v, 0);
			valor_notif = 0;
			bAddValor.setText("AddValue");
			toggle_visibility(bAddValor, 0);
			toggle_visibility(bLaunch, 1);
			killNotification();
			break;
		case R.id.notifications_bLaunch:
			sendNotification();
			toggle_visibility(v, 0);
			toggle_visibility(bAddValor, 1);
			toggle_visibility(bKill, 1);
			break;
		}
	}
}
