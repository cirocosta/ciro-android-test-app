package br.com.aeho.appoftests;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

public class MyService extends IntentService implements
		OnChronometerTickListener {

	private static final String TAG = "MyService";
	private Handler mHandler;
	private static Chronometer cCronometro;
	private static final int ID_CRONOMETRO = 1111;
	private static final Intent chronometerIntent = new Intent(
			Constants.LOCAL_BROADCAST_CHRONOMETER);
	private static long CHRONEMETER_BASE;
	private static long CHRONEMETER_ELAPSED = 0;

	/*
	 * INTENT SERVICE IMPLEM.
	 */

	public MyService() {
		super(null);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Intent localIntent = new Intent(Constants.LOCAL_BROADCAST_TIMER);
		localIntent.putExtra("status", 1);
		localIntent.putExtra("set_of_elements", 1);
		if (cCronometro == null) {
			cCronometro = new Chronometer(getApplicationContext());
			cCronometro.setOnChronometerTickListener(this);
			cCronometro.setId(ID_CRONOMETRO);
		} else {
			cCronometro.stop();
		}
		CHRONEMETER_BASE = SystemClock.elapsedRealtime();
		cCronometro.setBase(CHRONEMETER_BASE);
		cCronometro.start();
		LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
	}

	private void sendNotification() {
		Notification notification = new Notification(R.drawable.ic_launcher,
				"Ticker texts", System.currentTimeMillis());
		Intent notificationIntent = new Intent(this, Services.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(this, "Titulo", "Mensagem",
				pendingIntent);
		startForeground(1, notification);
	}

	@Override
	public void onChronometerTick(Chronometer chronometer) {
		switch (chronometer.getId()) {
		case ID_CRONOMETRO:
			// LocalBroadcastManager.getInstance(this).sendBroadcast(
			// chronometerIntent);
			if (CHRONEMETER_ELAPSED < 5000) {
				CHRONEMETER_ELAPSED += SystemClock.elapsedRealtime()
						- CHRONEMETER_BASE;
				Log.v("lol", String.valueOf(CHRONEMETER_ELAPSED));

			} else {
				Log.v("lol", "TERMINOU");
				chronometer.stop();
			}
		}
	}
}
