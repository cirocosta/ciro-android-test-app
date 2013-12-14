package br.com.aeho.appoftests;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class NormalService extends Service {

	private static final String TAG = "NormalService";
	private Looper mServiceLooper;
	private ServiceHandler mServiceHandler;
	private Intent localIntent;
	private Context context;

	private final class ServiceHandler extends Handler {
		public ServiceHandler(Looper looper) {
			super(looper);
		}

		
		@Override
		public void handleMessage(Message msg) {
			long endTime = System.currentTimeMillis() + 5000;
			while (System.currentTimeMillis() < endTime) {
				synchronized (this) {
					try {
						wait(endTime - System.currentTimeMillis());
						localIntent.putExtra("status", 0);
						localIntent.putExtra("set_of_elements", 0);
						LocalBroadcastManager.getInstance(context)
								.sendBroadcast(localIntent);
						Log.d(TAG, "Terminnou");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			// Stop the service using StartID so that we don't stop the
			// service in the middle of handling another job
			stopSelf(msg.arg1);
			Log.d(TAG, "Matou o service");
		}
	}

	@Override
	public void onCreate() {
		// one-time setup procedures before it calls startCommand
		HandlerThread thread = new HandlerThread("ServiceStartsArgument",
				Process.THREAD_PRIORITY_BACKGROUND);
		thread.start();
		context = this;

		localIntent = new Intent(Constants.LOCAL_BROADCAST_TIMER);
		localIntent.putExtra("status", 1);
		localIntent.putExtra("set_of_elements", 0);
		LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

		mServiceLooper = thread.getLooper();
		mServiceHandler = new ServiceHandler(mServiceLooper);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// For each start request, send a message to start a job and deliver the
		// start ID so we know which request we're stopping when we finish the
		// job
		Message msg = mServiceHandler.obtainMessage();
		msg.arg1 = startId;
		Log.d(TAG, "ONSTARTCOMMAND: startId = " + String.valueOf(startId));
		mServiceHandler.sendMessage(msg);

		// If we get killed, after returning from here, restart
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
